package com.example.wikway1;

import android.app.Application;
import android.os.AsyncTask;

import com.example.wikway1.utils.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class App extends Application {
    private static final String API_WIKWAY = "https://www.wikway.de/companies/offers-json?password=ain1018";

    public static ArrayList<JobAd> jobAds;
    public static InitImageBitmaps initialization;

    private static boolean loading = false;
    public static OnDataReadyListener onDataReadyListener;

    public static ArrayList<String> listOfBundesland = new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static void setOnDataReadyListener(OnDataReadyListener listener) {
        onDataReadyListener = listener;
    }


    public static void parseJobs(){
        if (jobAds == null || jobAds.size() == 0) {
            jobAds = new ArrayList<>();
            if (initialization == null) {
                initialization = new InitImageBitmaps();
            }
            if (!loading) {
                loading = true;
                initialization.execute();
            }

        } else {
            onDataReadyListener.setData(jobAds);
        }
    }
    public static class InitImageBitmaps extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... arg0) {

            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response

            String jsonStr = sh.makeServiceCall(API_WIKWAY);
            if (jsonStr != null) {

                try {
                    JSONArray companies = new JSONArray(jsonStr);
                    for (int i = 0; i < companies.length(); i++) {
                        JSONObject c = companies.getJSONObject(i);
                        String jobName = c.getString("Bezeichnung der Stelle");
                        String logo = c.getString("Logo");
                        String bundesland = c.getString("Bundesland");
                        if (bundesland != null && !listOfBundesland.contains(bundesland)) {
                            listOfBundesland.add(bundesland);
                        }
                        jobAds.add(new JobAd(jobName,logo, bundesland));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (onDataReadyListener != null) onDataReadyListener.setData(jobAds);
            loading = false;
        }
    }

    public interface OnDataReadyListener {
        void setData (ArrayList<JobAd> jobs);
    }
}
