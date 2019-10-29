//package com.example.wikway1.ui.home;
//
//import android.app.Activity;
//import android.os.AsyncTask;
//import android.os.Bundle;
//
//import android.util.Log;
//
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.wikway1.R;
//import com.example.wikway1.utils.HttpHandler;
//import com.example.wikway1.utils.RecyclerViewAdapter;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//public class JobListView extends Activity {
//
//    private static final String TAG = "JobListView";
//    private static final String API_WIKWAY = "https://www.wikway.de/companies/offers-json?password=ain1018";
//    //vars
//    private ArrayList<String> mNames;
//    private ArrayList<String> mImageUrls;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_home);
//        Log.d(TAG, "onCreate: started.");
//        mNames = new ArrayList<>();
//        mImageUrls = new ArrayList<>();
//        new InitImageBitmaps().execute();
//
//    }
//
//    private class InitImageBitmaps extends AsyncTask<Void, Void, Void> {
//        protected Void doInBackground(Void... arg0) {
//            HttpHandler sh = new HttpHandler();
//            // Making a request to url and getting response
//
//            String jsonStr = sh.makeServiceCall(API_WIKWAY);
//            if (jsonStr != null) {
//                try {
//                    JSONArray companies = new JSONArray(jsonStr);
//
//                    for (int i = 0; i < companies.length(); i++) {
//                        JSONObject c = companies.getJSONObject(i);
//                        String jobName = c.getString("Bezeichnung der Stelle");
//                        String logo = c.getString("Logo");
//                        Log.d(TAG, "AAAAAAAAAAAAAAAAAAAAAAAAAAA" + jobName);
//                        mNames.add(jobName);
//                        mImageUrls.add(logo);
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            initRecyclerView();
//        }
//    }
//    private void initRecyclerView(){
//        Log.d(TAG, "initRecyclerView: init recyclerview.");
//        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mImageUrls, mNames, this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
