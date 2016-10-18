package br.com.example.android.popularmovies.model.networking;

import br.com.example.android.popularmovies.movies.MainScreenViewModel;
import dagger.Component;

@Component(modules = MovieFetcherProvider.class)
public interface MovieFetcherComponent {
    void injectMovieFetcher(MainScreenViewModel movieViewModel);
}
