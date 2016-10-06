package br.com.example.android.popularmovies.viewmodel;

import java.util.ArrayList;
import java.util.List;

import br.com.example.android.popularmovies.model.data.Movie;
import br.com.example.android.popularmovies.model.networking.MovieFetcher;
import rx.Observer;

public class MainScreenViewModel {
    private final DataListener listener;
    private List<Movie> movies;

    public MainScreenViewModel(DataListener listener) {
        this.listener = listener;
    }

    public void onCreate() {
        movies = new ArrayList<>();
        /**
         * We use flavors to determine which movie provider to use.
         * mockData flavor will return a preset list of movies, whereas realData will recover
         * movies from the movieDB API.
         */
        MovieFetcher.getInstance().fetchMovies(new Observer<List<Movie>>() {
            @Override
            public void onCompleted() {
                listener.updateMovies(movies);
            }

            @Override
            public void onError(Throwable e) {
                listener.showErrorMessage();
            }

            @Override
            public void onNext(List<Movie> movieList) {
                movies.addAll(movieList);
            }
        });
    }

    public interface DataListener {
        void updateMovies(List<Movie> movies);

        void showErrorMessage();
    }
}
