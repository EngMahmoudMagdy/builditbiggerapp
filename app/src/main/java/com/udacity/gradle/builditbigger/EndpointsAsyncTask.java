package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.example.magdy.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;


import java.io.IOException;

/**
 * Created by engma on 5/17/2017.
 */
public class EndpointsAsyncTask extends AsyncTask<MainActivityFragment, Void, String> {
    private static MyApi myApiService = null;
    private Context mContext;
    private MainActivityFragment mainActivityFragment ;


    @Override
    protected String doInBackground(MainActivityFragment... params){
        mainActivityFragment = params[0];
        mContext = mainActivityFragment.getActivity();
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new
                    AndroidJsonFactory(), null)
                    .setRootUrl("https://builditbigger-140415.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }

        try
        {
            return myApiService.getJoke().execute().getData();

        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

        mainActivityFragment.loadJoke = result;
        mainActivityFragment.makeAJoke();

    }
}