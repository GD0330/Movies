package com.example.moviecatalog;

import database_layer.*;
import enums.Language;
import enums.Genre;
import enums.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.CurrentUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ExploreController {

    private static final Logger logger = LogManager.getLogger(ExploreController.class);


    @FXML
    private TableView<MovieEntity> movieTableView;

    private List<MovieEntity> movieList;



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

    @FXML
    private TableColumn<MovieEntity, Integer> numberOfWatchlistsColumn;





//    @FXML
//    private Label tableText;

    @FXML
    private ComboBox<Genre> genreComboBox;

    @FXML
    private ComboBox<Platform> platformComboBox;

    @FXML
    private ComboBox<Language> languageComboBox;







    @FXML
    public void initialize() {


        //idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        platformColumn.setCellValueFactory(new PropertyValueFactory<>("platform"));
        numberOfWatchlistsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfWatchlists"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));




        genreComboBox.getItems().setAll(Genre.values());
        platformComboBox.getItems().setAll(Platform.values());
        languageComboBox.getItems().setAll(Language.values());

        genreComboBox.getItems().add(null);
        platformComboBox.getItems().add(null);
        languageComboBox.getItems().add(null);

        genreComboBox.setPromptText("Choose a Genre");
        platformComboBox.setPromptText("Choose a Platform");
        languageComboBox.setPromptText("Choose a Language");

        Session session = HibernateSetup.getSessionFactory().openSession();
        try {


            Query<MovieEntity> query1 = session.createQuery("FROM MovieEntity" , MovieEntity.class);
            movieList = query1.getResultList();


            //System.out.println(movieList);
            logger.info(movieList);


            ObservableList<MovieEntity> notRentedcarsObservableList = FXCollections.observableArrayList(movieList);
            movieTableView.setItems(notRentedcarsObservableList);




        } catch (Exception   e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void onOrderClientsButtonClick(){
        Session session = HibernateSetup.getSessionFactory().openSession();
        try {

            Query<MovieEntity> query2 = session.createQuery("FROM MovieEntity ORDER BY numberOfWatchlists DESC" , MovieEntity.class);
            movieList = query2.getResultList();


            //logger.info(movieList);
            //System.out.println(movieList);


            ObservableList<MovieEntity> moviesObservableList = FXCollections.observableArrayList(movieList);
            movieTableView.setItems(moviesObservableList);

        } catch (Exception   e) {
            e.printStackTrace();
        } finally {
            session.close();
        }


    }

    public void onSearchCarButtonClick(){
        Genre selectedGenre = genreComboBox.getValue();
        Platform selectedPlatform = platformComboBox.getValue();
        Language selectedLanguage = languageComboBox.getValue();


        Session session = HibernateSetup.getSessionFactory().openSession();
        try {
            StringBuilder sql = new StringBuilder("FROM MovieEntity WHERE true ");


            if(selectedGenre != null){
                sql.append("AND genre = :selectedGenre ");
            }
            if(selectedPlatform != null){
                sql.append("AND platform = :selectedPlatform ");
            }
            if(selectedLanguage != null){
                sql.append("AND language = :selectedLanguage ");
            }


            Query<MovieEntity> query1 = session.createQuery(sql.toString(), MovieEntity.class);

            if(selectedGenre != null){
                query1.setParameter("selectedGenre", selectedGenre);
            }
            if(selectedPlatform != null){
                query1.setParameter("selectedPlatform", selectedPlatform);
            }
            if(selectedLanguage != null){
                query1.setParameter("selectedLanguage", selectedLanguage);
            }

            movieList = query1.getResultList();


            //System.out.println(movieList);
            logger.info(movieList);


            ObservableList<MovieEntity> carsObservableList = FXCollections.observableArrayList(movieList);
            movieTableView.setItems(carsObservableList);

            genreComboBox.setValue(null);
            platformComboBox.setValue(null);
            languageComboBox.setValue(null);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public void addToWishlistButtonClick() {
        MovieEntity selectedMovie = movieTableView.getSelectionModel().getSelectedItem();


        Session session = HibernateSetup.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();


            String hql = "FROM WatchlistEntity w WHERE w.user = :user AND w.movie = :movie";
            Query<WatchlistEntity> query = session.createQuery(hql, WatchlistEntity.class);
            query.setParameter("user", CurrentUser.getUser());
            query.setParameter("movie", selectedMovie);

            WatchlistEntity existingWishlistItem = query.uniqueResult();

            if (existingWishlistItem != null) {
                System.out.println("This movie is already in your watchlist.");
                return;
            }



            WatchlistEntity watchlistEntity = new WatchlistEntity(CurrentUser.getUser(),selectedMovie);
            session.persist(watchlistEntity);


            //selectedMovie.setRentStatus(RentStatus.RENTED);
            selectedMovie.updateWatchlistNumber();

            session.update(selectedMovie);
            transaction.commit();


            movieTableView.refresh();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
