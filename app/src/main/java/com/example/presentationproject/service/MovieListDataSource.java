package com.example.presentationproject.service;

import android.util.Log;

import com.example.presentationproject.contract.MovieListContract;
import com.example.presentationproject.model.MovieListResponse;
import com.example.presentationproject.model.Movies;
import com.example.presentationproject.network.ApiClient;
import com.example.presentationproject.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListDataSource implements MovieListContract.Model {

    private static final String TAG = "MovieListModel";
    private int pageNo = 1;


    @Override
    public void getMovieList(OnFinishedListener onFinishedListener, int pageNo) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieListResponse> call = apiService.getPopularMovies(ApiClient.API_KEY, pageNo);

        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                if (response.body() != null) {
                    List<Movies> movies = response.body().getResults();
                    Log.e(TAG, "Number of movies received: " + movies.size());

                    onFinishedListener.onFinished(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }

}
