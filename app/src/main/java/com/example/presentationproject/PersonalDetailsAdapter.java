package com.example.presentationproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PersonalDetailsAdapter extends RecyclerView.Adapter<PersonalDetailsAdapter.ViewHolder> {

    Context context;
    ArrayList<PersonalDetailsModal> personalDetailsModals;
    private String TAG;

    public PersonalDetailsAdapter(Context context, ArrayList<PersonalDetailsModal> personalDetailsModals) {
        this.context = context;
        this.personalDetailsModals = personalDetailsModals;
    }

    @NonNull
    @Override
    public PersonalDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_xml, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalDetailsAdapter.ViewHolder holder, int position) {
        PersonalDetailsModal personalDetailsModal = personalDetailsModals.get(position);

        Log.d(TAG, "onBindViewHolder: " + personalDetailsModal.getAge().toString()+ personalDetailsModal.getFirstname().toString());
        holder.tvAge.setText(personalDetailsModal.getAge());
        holder.tv_first_name.setText(personalDetailsModal.getFirstname());
        holder.tv_last_name.setText(personalDetailsModal.getLastname());
        holder.iv_personal_img.setImageResource(personalDetailsModal.getImg());
    }

    @Override
    public int getItemCount() {
        return personalDetailsModals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_personal_img;
        TextView tv_first_name;
        TextView tv_last_name;
        TextView tvAge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_personal_img = (ImageView) itemView.findViewById(R.id.iv_personal_img);
            tv_first_name = (TextView) itemView.findViewById(R.id.textViewFirstName);
            tv_last_name = (TextView) itemView.findViewById(R.id.textViewLastName);
            tvAge = (TextView) itemView.findViewById(R.id.textViewAge);

        }
    }
}




