package br.com.example.android.popularmovies.model.data;

import java.util.List;

public interface OnMoviesFetchedListener {
        void onMoviesFetched(List<Movie> movies);

        void onError();
    }