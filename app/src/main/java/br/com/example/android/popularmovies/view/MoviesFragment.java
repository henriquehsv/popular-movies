package br.com.example.android.popularmovies.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
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
import br.com.example.android.popularmovies.databinding.FragmentMainBinding;
import br.com.example.android.popularmovies.model.data.Movie;
import br.com.example.android.popularmovies.viewmodel.MainScreenViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesFragment extends Fragment implements MainScreenViewModel.DataListener {
    private MainScreenViewModel mainScreenViewModel;
    private FragmentMainBinding fragmentMainBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainScreenViewModel = new MainScreenViewModel(this);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        RecyclerView movieGrid = fragmentMainBinding.movieGrid;
        int columnAmount = getResources().getInteger(R.integer.column_amount);
        StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(columnAmount, StaggeredGridLayoutManager.VERTICAL);

        movieGrid.setLayoutManager(layout);
        movieGrid.setAdapter(new MoviePosterAdapter());

        fragmentMainBinding.setViewModel(mainScreenViewModel);

        mainScreenViewModel.onCreate();

        return fragmentMainBinding.getRoot();
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

    @Override
    public void updateMovies(List<Movie> movies) {
        MoviePosterAdapter adapter = (MoviePosterAdapter) fragmentMainBinding.movieGrid.getAdapter();
        adapter.setMovies(movies);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(getContext(), getString(R.string.unable_to_recover_movies), Toast.LENGTH_LONG).show();
    }
}
