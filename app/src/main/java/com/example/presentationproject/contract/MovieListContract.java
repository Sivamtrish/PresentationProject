package com.example.presentationproject.contract;

import com.example.presentationproject.model.Movies;

import java.util.List;

public interface MovieListContract {

    /**
     * Here we have the model
     **/
    interface Model {
        interface OnFinishedListener {
            void onFinished(List<Movies> movieArrayList);

            void onFailure(Throwable t);
        }

        void getMovieList(OnFinishedListener onFinishedListener, int pageNo);
    }

    /**
     * Here we have the presenter
     **/
    interface View {
        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Movies> movieListArray);

        void onResponseFailure(Throwable throwable);
    }

    /**
     * Here we have the presenter
     **/
    interface Presenter {
        void onDestroy();

        void getMoreData(int pageNo);

        void requestDataFromServer();
    }


}
