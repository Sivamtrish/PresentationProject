package com.example.presentationproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.presentationproject.R;
import com.example.presentationproject.contract.MovieListContract;
import com.example.presentationproject.model.Movies;
import com.example.presentationproject.presenter.MoviePresenter;
import com.example.presentationproject.adapter.MovieListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity implements MovieListContract.View {
    private static final String TAG = "MovieActivity";
    private MoviePresenter moviePresenter;
    private RecyclerView rvMovieList;
    private List<Movies> movieList;
    private MovieListAdapter movieListAdapter;
    private ProgressBar pbLoading;
    private int pageNo = 1;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        rvMovieList = findViewById(R.id.rvMovieList);
        pbLoading = findViewById(R.id.pbLoading);

        movieList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        rvMovieList.setLayoutManager(layoutManager);
        rvMovieList.setHasFixedSize(true);

        moviePresenter = new MoviePresenter(this);
        moviePresenter.requestDataFromServer();
//        moviePresenter.getMoreData(2);
    }

    @Override
    public void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<Movies> movieListArray) {
        movieList.addAll(movieListArray);
        movieListAdapter = new MovieListAdapter(movieList, MovieActivity.this);
        rvMovieList.setAdapter(movieListAdapter);

        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, "ERROR:" + throwable.getMessage(), throwable);
        Toast.makeText(MovieActivity.this, "Error in getting data: " + throwable.getMessage(), Toast.LENGTH_SHORT);
    }
}