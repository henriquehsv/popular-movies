package br.com.example.android.popularmovies.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.example.android.popularmovies.R;
import br.com.example.android.popularmovies.model.data.Movie;
import br.com.example.android.popularmovies.model.networking.MoviesDBInfoFetcher;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviesFragment extends Fragment {
    private MoviePosterAdapter moviePosterAdapter;

    @BindView(R.id.movieGrid) RecyclerView movieGrid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moviePosterAdapter = new MoviePosterAdapter(getContext(), new ArrayList<Movie>());

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        int columnAmount = getResources().getInteger(R.integer.column_amount);
        StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(columnAmount, StaggeredGridLayoutManager.VERTICAL);

        movieGrid.setLayoutManager(layout);
        movieGrid.setAdapter(moviePosterAdapter);

        MoviesDBInfoFetcher.getInstance().getPopularMovies(new MoviesDBInfoFetcher.OnMoviesFetchedListener() {
            @Override
            public void onMoviesFetched(List<Movie> movies) {
                moviePosterAdapter.setMovies(movies);
                moviePosterAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError() {
                if (getActivity() == null) {
                    return;
                }

                Toast.makeText(getContext(), getString(R.string.unable_to_recover_movies), Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }
}
