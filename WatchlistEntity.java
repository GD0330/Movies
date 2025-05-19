package database_layer;


import jakarta.persistence.*;

@Entity
@Table(name = "watchlist")
public class WatchlistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private AdministratorEntity user;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieEntity movie;

    public WatchlistEntity() {}

    public WatchlistEntity(AdministratorEntity user, MovieEntity movie) {
        this.user = user;
        this.movie = movie;
    }

    public int getId() {
        return id;
    }

    public AdministratorEntity getUser() {
        return user;
    }

    public void setUser(AdministratorEntity user) {
        this.user = user;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }
}
