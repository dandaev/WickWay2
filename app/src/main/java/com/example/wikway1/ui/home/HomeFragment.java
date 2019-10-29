package com.example.wikway1.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wikway1.App;
import com.example.wikway1.JobAd;
import com.example.wikway1.R;
import com.example.wikway1.ui.VacancyAdapter;
import com.example.wikway1.utils.HttpHandler;
import com.example.wikway1.utils.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    public static final String TAG = "JobListView";
    private static HomeFragment instance;

    private ProgressBar progressBar;

    private RecyclerView recyclerView;
    private VacancyAdapter adapter;
    private App.OnDataReadyListener onDataReadyListener;
    public static HomeFragment getInstance(){
        if(instance==null)
            instance = new HomeFragment();
        return instance;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        progressBar = view.findViewById(R.id.progress_bar);
        initRecyclerView(view);
        getJobs();
        return view;

    }

    private void initRecyclerView(View view){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        recyclerView = view.findViewById(R.id.recyclerv_view);
        adapter = new VacancyAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void getJobs() {
        onDataReadyListener = new App.OnDataReadyListener() {
            @Override
            public void setData(ArrayList<JobAd> jobs) {
                adapter.setJobs(App.jobAds);
                progressBar.setVisibility(View.GONE);
            }
        };
        App.setOnDataReadyListener(onDataReadyListener);
        App.parseJobs();
    }
}