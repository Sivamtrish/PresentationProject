package com.example.presentationproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.presentationproject.R;
import com.example.presentationproject.model.Movies;
import com.example.presentationproject.network.ApiClient;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<Movies> movieList;
    private Context context;

    public MovieListAdapter(List<Movies> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_movie_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movies movies = movieList.get(position);
        holder.tv_title.setText(movies.getTitle());
        holder.tv_release_date_movie.setText(movies.getReleaseDate());
        holder.tv_overView_movie.setText(movies.getOverview());

        Glide.with(context).load(ApiClient.IMAGE_BASE_URL+ movies.getPosterPath()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title, tv_release_date_movie, tv_overView_movie;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title  = itemView.findViewById(R.id.tvTitleMovie);
            tv_release_date_movie =  itemView.findViewById(R.id.tvReleaseDateMovie);
            tv_overView_movie =  itemView.findViewById(R.id.tvOverViewMovie);
            imageView =  itemView.findViewById(R.id.imageView);
        }
    }


}
