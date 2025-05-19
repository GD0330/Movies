package com.example.moviecatalog;

import database_layer.MovieEntity;
import database_layer.HibernateSetup;
import database_layer.WatchlistEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.CurrentUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class WatchlistController {
    private static final Logger logger = LogManager.getLogger(WatchlistController.class);
    @FXML
    private TableView<MovieEntity> movieTableView;

    private List<MovieEntity> movieList;


//    @FXML
//    private TableColumn<MovieEntity, Integer> idColumn;

    @FXML
    private TableColumn<MovieEntity, Integer> ratingColumn;

    @FXML
    private TableColumn<MovieEntity, String> languageColumn;

    @FXML
    private TableColumn<MovieEntity, String> genreColumn;

    @FXML
    private TableColumn<MovieEntity, String> platformColumn;

    @FXML
    private TableColumn<MovieEntity, String> titleColumn;

//    @FXML
//    private TableColumn<MovieEntity, Integer> numberOfWatchlistsColumn;

//    @FXML
//    private Label tableText;

    @FXML
    public void initialize() {

        //idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        platformColumn.setCellValueFactory(new PropertyValueFactory<>("platform"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        //numberOfWatchlistsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfWatchlists"));

        Session session = HibernateSetup.getSessionFactory().openSession();
        try {


            //Query<MovieEntity> query1 = session.createQuery("FROM MovieEntity WHERE rentStatus = RentStatus.RENTED" , MovieEntity.class);
            Query<MovieEntity> query1 = session.createQuery(
                    "SELECT w.movie FROM WatchlistEntity w WHERE w.user = :user",
                    MovieEntity.class
            );
            query1.setParameter("user", CurrentUser.getUser());
            movieList = query1.getResultList();


            //System.out.println(movieList);
            logger.info(movieList);


            ObservableList<MovieEntity> notRentedcarsObservableList = FXCollections.observableArrayList(movieList);
            movieTableView.setItems(notRentedcarsObservableList);
            //tableText.setText("Watchlist");

        } catch (Exception   e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

//    @FXML
//    public void onAvailableCarsButtonClick(ActionEvent actionEvent) {
//
//    }


    @FXML
    public void removeAMovieFromTheWishlistButtonClick() {
        MovieEntity selectedMovie = movieTableView.getSelectionModel().getSelectedItem();

        if (selectedMovie == null) {
            System.out.println("No movie selected to remove from watchlist.");
            return;
        }

        Session session = HibernateSetup.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            MovieEntity attachedMovie = session.get(MovieEntity.class, selectedMovie.getId());

            String hql = "FROM WatchlistEntity w WHERE w.user = :user AND w.movie = :movie";
            Query<WatchlistEntity> query = session.createQuery(hql, WatchlistEntity.class);
            query.setParameter("user", CurrentUser.getUser());
            query.setParameter("movie", attachedMovie);

            WatchlistEntity wishlistItem = query.uniqueResult();

            if (wishlistItem != null) {
                session.remove(wishlistItem);

                attachedMovie.updateWatchlistNumberNegative();
                session.update(attachedMovie);
            } else {
                System.out.println("Movie not found in watchlist.");
            }

            transaction.commit();


            movieTableView.getItems().remove(selectedMovie);
            movieTableView.refresh();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


}
