package br.com.example.android.popularmovies.viewmodel;

import java.util.List;

import br.com.example.android.popularmovies.model.data.Movie;
import br.com.example.android.popularmovies.model.networking.MoviesDBInfoFetcher;

public class MainScreenViewModel {
    private final DataListener listener;

    public MainScreenViewModel(DataListener listener) {
        this.listener = listener;
    }

    public void onCreate() {
        MoviesDBInfoFetcher.getInstance().getPopularMovies(new MoviesDBInfoFetcher.OnMoviesFetchedListener() {
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
