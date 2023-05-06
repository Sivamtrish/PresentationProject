package com.example.presentationproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv_personal_data;
    PersonalDetailsAdapter pAdapter;

    /**
     * This caters for the SearchView
     **/
    SearchView searchView_bar;
    ListView listView;
    ArrayList<String> courses;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    public void initViews() {
        rv_personal_data = findViewById(R.id.rv_personal_details);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_personal_data.setLayoutManager(layoutManager);
        rv_personal_data.setHasFixedSize(true);
        searchView_bar = (SearchView) findViewById(R.id.search_view_bar);
        listView = findViewById(R.id.search_list_view);

        /**method 1**/
//        ArrayList<PersonalDetailsModal> Modal = new ArrayList<>();
//        PersonalDetailsModal personalDetailsModal1 = new PersonalDetailsModal("Mavis", "Emiewo", "24", R.drawable.baseline_brightness_7_24);
//        PersonalDetailsModal personalDetailsModal2 = new PersonalDetailsModal("Sarah", "Emiewo", "35", R.drawable.baseline_brightness_7_24);
//        PersonalDetailsModal personalDetailsModal3 = new PersonalDetailsModal("Mavis", "Emiewo", "24", R.drawable.baseline_brightness_7_24);
//        PersonalDetailsModal personalDetailsModal4 = new PersonalDetailsModal("Mavis", "Emiewo", "24", R.drawable.baseline_brightness_7_24);
//        PersonalDetailsModal personalDetailsModal5 = new PersonalDetailsModal("Mavis", "Emiewo", "24", R.drawable.baseline_brightness_7_24);
//        PersonalDetailsModal personalDetailsModal6 = new PersonalDetailsModal("Mavis", "Emiewo", "24", R.drawable.baseline_brightness_7_24);
//
//        Modal.add(personalDetailsModal1);
//        Modal.add(personalDetailsModal2);
//        Modal.add(personalDetailsModal3);
//        Modal.add(personalDetailsModal4);
//        Modal.add(personalDetailsModal5);
//        Modal.add(personalDetailsModal6);
//
//        pAdapter = new PersonalDetailsAdapter(this, Modal);
//        rv_personal_data.setAdapter(pAdapter);

        /**Method 2**/
        ArrayList<PersonalDetailsModal> modalSet = new ArrayList<>();
        modalSet.add(new PersonalDetailsModal("Mavis", "Emiewo", "24", R.drawable.baseline_brightness_7_24));
        modalSet.add(new PersonalDetailsModal("Mavis", "Emiewo", "53", R.drawable.baseline_brightness_7_24));
        modalSet.add(new PersonalDetailsModal("Mavis", "Emiewo", "42", R.drawable.baseline_brightness_7_24));
        modalSet.add(new PersonalDetailsModal("Mavis", "Emiewo", "38", R.drawable.baseline_brightness_7_24));
        modalSet.add(new PersonalDetailsModal("Mavis", "Emiewo", "91", R.drawable.baseline_brightness_7_24));
        modalSet.add(new PersonalDetailsModal("Mavis", "Emiewo", "16", R.drawable.baseline_brightness_7_24));
        pAdapter = new PersonalDetailsAdapter(this, modalSet);
        rv_personal_data.setAdapter(pAdapter);


        /**Here we get a list of courses **/
        courses = new ArrayList<>();
        courses.add("English");
        courses.add("Mathematics");
        courses.add("Social Studies");
        courses.add("French");
        courses.add("Integrated Studies");
        courses.add("Literature");
        courses.add("Biology");
        courses.add("CRK");
        courses.add("physics");
        courses.add("Chemistry");
        courses.add("C++");
        courses.add("Agriculture");
        courses.add("French");
        courses.add("Phonics");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courses);
        listView.setAdapter(adapter);

        searchView_bar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (courses.contains(query)) {
                    adapter.getFilter().filter(query);
                } else {
                    Toast.makeText(MainActivity.this, "No Match found", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}

/**
 * initialize your recyclerView
 * initialize your linearLayout manager
 * set the type of layout on the recyclerView
 * initialize your adapter which will take two parameters, the context and list of data (arrayList)
 * initialize your ArrayList and adda data to the list
 * pass the context and and darta into the adapter
 * set the adapter object on the recyclerView
 */




