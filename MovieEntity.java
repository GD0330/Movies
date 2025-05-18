    package database_layer;

    import enums.*;
    import jakarta.persistence.*;
    import models.UpdateWatchlistNumber;

    @Entity
    @Table(name = "movie")
    public class MovieEntity implements UpdateWatchlistNumber {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String title;
        private double rating;

        @Enumerated(EnumType.STRING)
        private Genre genre;

        @Enumerated(EnumType.STRING)
        private Platform platform;


        @Enumerated(EnumType.STRING)
        private Language language;

        private int numberOfWatchlists;




        public MovieEntity() {
        }

        public MovieEntity(double rating){
            this.rating = rating;
        }

        public MovieEntity(String title, double rating, Genre genre, Platform Platform, Language language) {
            this.title = title;
            this.rating = rating;
            this.language = language;
            this.genre = genre;
            this.platform = Platform;
            this.numberOfWatchlists = 0;
        }




        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }


        public int getNumberOfWatchlists() {
            return numberOfWatchlists;
        }

        public void setNumberOfWatchlists(int numberOfRents) {
            this.numberOfWatchlists = numberOfRents;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        public Genre getGenre() {
            return genre;
        }

        public Platform getPlatform() {
            return platform;
        }

        public Language getLanguage() {
            return language;
        }

        @Override
        public String toString() {
            return
                    "Movie ID: " + id +
                    ", Language: " + language +
                    ", Rating: " + rating +
                    ", Genre: " + genre +
                    ", Platform: " + platform;
        }

        @Override
        public void updateWatchlistNumber() {
            this.numberOfWatchlists++;
        }


        public void updateWatchlistNumberNegative() {
            this.numberOfWatchlists--;
        }


    }
