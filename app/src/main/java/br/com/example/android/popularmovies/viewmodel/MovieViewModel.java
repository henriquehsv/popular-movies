package br.com.example.android.popularmovies.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.example.android.popularmovies.model.data.Movie;
import br.com.example.android.popularmovies.view.MovieDetailsActivity;

public class MovieViewModel extends BaseObservable {
    private Movie movie;

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
        Context context = view.getContext();

        Intent detailsIntent = new Intent();
        detailsIntent.setClass(context, MovieDetailsActivity.class);
        detailsIntent.putExtra(MovieDetailsActivity.MOVIE_EXTRA, movie);

        context.startActivity(detailsIntent);
    }

    /**
     * Loads an imageUrl inside an image view.
     * <p>
     * The annotation {@link @BindingAdapter} will provide this method as a XML tag.
     *
     * @param imageView the view element that will receive an image.
     * @param imageUrl  an url representing an image path.
     */
    @BindingAdapter({"bind:loadImage"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        Picasso.with(imageView.getContext()).load(imageUrl).into(imageView);
    }
}
