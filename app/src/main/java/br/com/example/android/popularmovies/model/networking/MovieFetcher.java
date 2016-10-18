package br.com.example.android.popularmovies.model.networking;

import java.util.List;

import br.com.example.android.popularmovies.model.data.Movie;
import rx.Observer;

public interface MovieFetcher {

    void fetchMovies(Observer<List<Movie>> observer);
}
