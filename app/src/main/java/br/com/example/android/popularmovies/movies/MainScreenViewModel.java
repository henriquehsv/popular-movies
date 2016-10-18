package br.com.example.android.popularmovies.movies;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.example.android.popularmovies.model.data.Movie;
import br.com.example.android.popularmovies.model.networking.DaggerMovieFetcherComponent;
import br.com.example.android.popularmovies.model.networking.MockMovieFetcher;
import br.com.example.android.popularmovies.model.networking.MovieFetcher;
import br.com.example.android.popularmovies.model.networking.MovieFetcherComponent;
import dagger.internal.DaggerCollections;
import rx.Observer;

public class MainScreenViewModel {
    private final DataListener listener;
    private List<Movie> movies;

    @Inject MovieFetcher movieFetcher;

    public MainScreenViewModel(DataListener listener) {
        this.listener = listener;
        DaggerMovieFetcherComponent.create().injectMovieFetcher(this);
    }

    public void onCreate() {
        movies = new ArrayList<>();
        /**
         * We use flavors to determine which movie provider to use.
         * mockData flavor will return a preset list of movies, whereas realData will recover
         * movies from the movieDB API.
         */
        movieFetcher.fetchMovies(new Observer<List<Movie>>() {
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
