package com.example.wikway1.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wikway1.App;
import com.example.wikway1.JobAd;
import com.example.wikway1.R;
import com.example.wikway1.ui.VacancyAdapter;
import com.example.wikway1.ui.home.GalleryActivity;
import com.example.wikway1.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.function.Consumer;


public class SearchFragment extends Fragment {

    private EditText searchEditText;
    private Spinner spinner;
    private Button button;
    private RecyclerView rv;
    private VacancyAdapter vacancyAdapter;
    private App.OnDataReadyListener onDataReadyListener;

    private ArrayAdapter<String> bundeslandAdapter;

    private ArrayList<String> listOfBundesland;
    private ArrayList<JobAd> allJobs;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       View v = inflater.inflate(R.layout.fragment_search, container, false);
        initViews(v);
        getJobs();
       return v;
    }

    private void initViews(View v) {
        searchEditText = v.findViewById(R.id.search_input);
        spinner = v.findViewById(R.id.category_spinner);
        button = v.findViewById(R.id.search_button);
        rv = v.findViewById(R.id.rv);
        listOfBundesland = new ArrayList<>();
        bundeslandAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listOfBundesland);
        spinner.setAdapter(bundeslandAdapter);
        initRV();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchEditText.getText().toString();
                if (spinner.getSelectedItem() != null)
                search(searchText, listOfBundesland.get(spinner.getSelectedItemPosition()));
                else {
                    search(searchText, "");
                }
            }
        });
    }

    private void initRV() {
        vacancyAdapter = new VacancyAdapter(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(vacancyAdapter);
        vacancyAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Intent intent = new Intent(getContext(), GalleryActivity.class);
                intent.putExtra("image_url", vacancyAdapter.jobs.get(pos).getImageLink());
                intent.putExtra("image_name", vacancyAdapter.jobs.get(pos).getTitle());
                startActivity(intent);
            }

            @Override
            public void onItemLongCLick(int pos) {
                Toast.makeText(getContext(), "on long click: " + vacancyAdapter.jobs.get(pos).getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getJobs() {
        onDataReadyListener = new App.OnDataReadyListener() {
            @Override
            public void setData(ArrayList<JobAd> jobs) {
                allJobs = jobs;
                listOfBundesland.addAll(App.listOfBundesland);
                bundeslandAdapter.notifyDataSetChanged();
            }
        };
        App.setOnDataReadyListener(onDataReadyListener);
        App.parseJobs();
    }

    private void search(@NonNull String searchTerm, @NonNull String bundesland) {
        ArrayList<JobAd> filteredJobs = new ArrayList<>();
        if (!searchTerm.isEmpty() && !bundesland.isEmpty()){
            for (JobAd job : allJobs) {
                if (job.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) && job.getBundesland().equals(bundesland)) {
                    filteredJobs.add(job);
                }
            }
        }else if (searchTerm.isEmpty()) {
            for (JobAd job : allJobs) {
                if (job.getBundesland().equals(bundesland)) {
                    filteredJobs.add(job);
                }
            }
        } else {
            for (JobAd job : allJobs) {
                if (job.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                    filteredJobs.add(job);
                }
            }
        }

        vacancyAdapter.setJobs(filteredJobs);
        }
}