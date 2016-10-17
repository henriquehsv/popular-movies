package br.com.example.android.popularmovies.movies;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.example.android.popularmovies.model.data.Movie;

public class MovieViewModel extends BaseObservable {
    private Movie movie;
    private MovieViewModelCallback movieViewModelCallback;

    public void setMovieViewModelCallback(MovieViewModelCallback movieViewModelCallback) {
        this.movieViewModelCallback = movieViewModelCallback;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getPosterUrl() {
        return movie.getPosterUrl();
    }

    public String getTitle() {
        return movie.getTitle();
    }

    public String getRating() {
        return movie.getRating().toPlainString();
    }

    public String getDescription() {
        return movie.getDescription();
    }

    public void onMovieClick(View view) {
        if (movieViewModelCallback != null) {
            movieViewModelCallback.openMovieDetails(movie);
        }
    }

    /**
     * Loads an imageUrl inside an image view.
     * <p/>
     * The annotation {@link @BindingAdapter} will provide this method as a XML tag.
     *
     * @param imageView the view element that will receive an image.
     * @param imageUrl  an url representing an image path.
     */
    @BindingAdapter({"bind:loadImage"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        Picasso.with(imageView.getContext()).load(imageUrl).into(imageView);
    }

    public interface MovieViewModelCallback {
        void openMovieDetails(Movie movie);
    }
}
