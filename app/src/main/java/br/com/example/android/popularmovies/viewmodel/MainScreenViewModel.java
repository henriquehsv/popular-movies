package br.com.example.android.popularmovies.viewmodel;

import java.util.List;

import br.com.example.android.popularmovies.model.data.Movie;
import br.com.example.android.popularmovies.model.networking.MovieFetcher;

public class MainScreenViewModel {
    private final DataListener listener;

    public MainScreenViewModel(DataListener listener) {
        this.listener = listener;
    }

    public void onCreate() {
        /**
         * We use flavors to determine which movie provider to use.
         * mockData flavor will return a preset list of movies, whereas realData will recover
         * movies from the movieDB API.
         */
        MovieFetcher.getInstance().fetchMovies(new MovieFetcher.OnMoviesFetchedListener() {
            @Override
            public void onMoviesFetched(List<Movie> movies) {
                listener.updateMovies(movies);
            }

            @Override
            public void onError() {
                listener.showErrorMessage();
            }
        });
    }

    public interface DataListener {
        void updateMovies(List<Movie> movies);

        void showErrorMessage();
    }
}
