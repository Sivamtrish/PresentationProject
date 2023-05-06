package com.example.presentationproject.presenter;

import com.example.presentationproject.contract.MovieListContract;
import com.example.presentationproject.model.Movies;
import com.example.presentationproject.service.MovieListDataSource;

import java.util.List;

/**
 * Here we implement all the interface that we will be needing in the presenter layer
 **/
public class MoviePresenter implements MovieListContract.Presenter, MovieListContract.Model.OnFinishedListener {

    private MovieListContract.View movieListView;

    private MovieListContract.Model movieListModel;

    public MoviePresenter(MovieListContract.View movieListView) {
        this.movieListView = movieListView;
        this.movieListModel = new MovieListDataSource();
    }

    @Override
    public void onDestroy() {
        this.movieListView = null;
    }

    @Override
    public void getMoreData(int pageNo) {
        if (movieListView != null) {
            movieListView.showProgress();
        }
        movieListModel.getMovieList(this, pageNo);
    }

    @Override
    public void requestDataFromServer() {
        if (movieListView != null) {
            movieListView.showProgress();
        }
        movieListModel.getMovieList(this, 1);
    }

    @Override
    public void onFinished(List<Movies> movieArrayList) {
        movieListView.setDataToRecyclerView(movieArrayList);
    }

    @Override
    public void onFailure(Throwable t) {
        movieListView.onResponseFailure(t);
        if (movieListView != null) {
            movieListView.hideProgress();
        }
    }
}
