package com.example.filmsapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.filmsapp.R;
import com.example.filmsapp.adapters.TVShowsAdapter;
import com.example.filmsapp.databinding.ActivityMainBinding;
import com.example.filmsapp.models.TVShow;
import com.example.filmsapp.viewmodels.MostPopularTVShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private MostPopularTVShowsViewModel viewModel;
    private List<TVShow> tvShows = new ArrayList<>();
    private TVShowsAdapter tvShowsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doInitialization();
    }

    private void doInitialization() {
        activityMainBinding.tvShowRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        tvShowsAdapter = new TVShowsAdapter(tvShows);
        activityMainBinding.tvShowRecyclerView.setAdapter(tvShowsAdapter);
        getMostPopularTVShows();
    }

    private void getMostPopularTVShows () {
        activityMainBinding.setIsLoading(true);
        viewModel.getMostPopularTVShows(0).observe(this, mostPopularTVShowsResponse -> {
            activityMainBinding.setIsLoading(false);
            if(mostPopularTVShowsResponse != null) {
                if(mostPopularTVShowsResponse.getTvShows() != null) {
                    tvShows.addAll(mostPopularTVShowsResponse.getTvShows());
                    tvShowsAdapter.notifyDataSetChanged();
                }
            }
        });
    }

}