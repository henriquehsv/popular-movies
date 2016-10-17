package br.com.example.android.popularmovies.moviedetails;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.example.android.popularmovies.R;
import br.com.example.android.popularmovies.databinding.ActivityMovieDetailsBinding;
import br.com.example.android.popularmovies.model.data.Movie;
import br.com.example.android.popularmovies.layout.MovieLayoutViewModel;
import br.com.example.android.popularmovies.movies.MovieViewModel;

public class MovieDetailsActivity extends AppCompatActivity {
    public static final String MOVIE_EXTRA = "MOVIE_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityMovieDetailsBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        final Toolbar toolbar = viewDataBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        Movie movie = getIntent().getParcelableExtra(MOVIE_EXTRA);
        MovieViewModel movieViewModel = new MovieViewModel();
        movieViewModel.setMovie(movie);

        MovieLayoutViewModel movieLayoutViewModel = createLayoutViewModel();

        viewDataBinding.setViewModel(movieViewModel);
        viewDataBinding.setLayoutViewModel(movieLayoutViewModel);
    }

    private MovieLayoutViewModel createLayoutViewModel() {
        MovieLayoutViewModel movieLayoutViewModel = new MovieLayoutViewModel();
        int textColor = getResources().getColor(R.color.movieBackgroundColor);
        movieLayoutViewModel.setTextColor(textColor);

        int backgroundColor = getResources().getColor(R.color.colorPrimary);
        movieLayoutViewModel.setBackgroundColor(backgroundColor);

        movieLayoutViewModel.setNumberOfLines(10);
        return movieLayoutViewModel;
    }
}
