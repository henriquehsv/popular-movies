package br.com.example.android.popularmovies.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.example.android.popularmovies.R;
import br.com.example.android.popularmovies.databinding.ActivityMovieDetailsBinding;
import br.com.example.android.popularmovies.model.data.Movie;
import br.com.example.android.popularmovies.viewmodel.MovieLayoutViewModel;
import br.com.example.android.popularmovies.viewmodel.MovieViewModel;

public class MovieDetailsActivity extends AppCompatActivity {
    public static final String MOVIE_EXTRA = "MOVIE_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMovieDetailsBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        Toolbar toolbar = viewDataBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Movie movie = getIntent().getParcelableExtra(MOVIE_EXTRA);
        MovieViewModel movieViewModel = new MovieViewModel();
        movieViewModel.setMovie(movie);

        MovieLayoutViewModel movieLayoutViewModel = new MovieLayoutViewModel();

        int textColor = getResources().getColor(R.color.movieBackgroundColor);
        movieLayoutViewModel.setTextColor(textColor);

        int backgroundColor = getResources().getColor(R.color.colorPrimary);
        movieLayoutViewModel.setBackgroundColor(backgroundColor);

        movieLayoutViewModel.setNumberOfLines(10);

        viewDataBinding.setViewModel(movieViewModel);
        viewDataBinding.setLayoutViewModel(movieLayoutViewModel);
    }
}
