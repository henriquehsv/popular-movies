package br.com.example.android.popularmovies.model.networking;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.example.android.popularmovies.model.data.Movie;

public class MockMovieData {
    /**
     * This list represents the top user rated movies at IMDB
     */
    private static List<Movie> movies;

    static {
        movies = new ArrayList<>();

        Movie shawshank = new Movie();
        shawshank.setTitle("The Shawshank Redemption");
        shawshank.setRating(new BigDecimal("9.3"));
        shawshank.setDescription(
                "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.");

        movies.add(shawshank);

        Movie godfather = new Movie();
        godfather.setTitle("The Godfather");
        godfather.setRating(new BigDecimal("9.2"));
        godfather.setDescription("The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.");

        movies.add(godfather);

        Movie godfather2 = new Movie();
        godfather2.setTitle("The Godfather: Part II");
        godfather2.setRating(new BigDecimal("9.0"));
        godfather2.setDescription(
                "The early life and career of Vito Corleone in 1920s New York is portrayed while his son, Michael, expands and tightens his grip on his crime syndicate stretching from Lake Tahoe, Nevada to pre-revolution 1958 Cuba.");

        movies.add(godfather2);

        Movie batman = new Movie();
        batman.setTitle("The Dark Knight");
        batman.setRating(new BigDecimal("9.0"));
        batman.setDescription(
                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, the caped crusader must come to terms with one of the greatest psychological tests of his ability to fight injustice.");

        movies.add(batman);

        Movie schindler = new Movie();
        schindler.setTitle("Schindler's List");
        schindler.setRating(new BigDecimal("8.9"));
        schindler.setDescription(
                "In German-occupied Poland during World War II, Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazi Germans.");

        movies.add(schindler);

        Movie angryMen = new Movie();
        angryMen.setTitle("12 Angry Men");
        angryMen.setRating(new BigDecimal("8.9"));
        angryMen.setDescription(
                "A jury holdout attempts to prevent a miscarriage of justice by forcing his colleagues to reconsider the evidence.");

        movies.add(angryMen);

        Movie pulpFiction = new Movie();
        pulpFiction.setTitle("Pulp Fiction");
        pulpFiction.setRating(new BigDecimal("8.9"));
        pulpFiction.setDescription(
                "The lives of two mob hit men, a boxer, a gangster's wife, and a pair of diner bandits intertwine in four tales of violence and redemption.");

        movies.add(pulpFiction);

        Movie lordOfTheRings = new Movie();
        lordOfTheRings.setTitle("The Lord of the Rings: The Return of the King");
        lordOfTheRings.setRating(new BigDecimal("8.9"));
        lordOfTheRings.setDescription(
                "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.");

        movies.add(lordOfTheRings);
    }

    public static List<Movie> getMovies() {
        return movies;
    }
}
