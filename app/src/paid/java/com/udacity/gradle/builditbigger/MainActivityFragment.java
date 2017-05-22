package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.magdy.joke.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }
    ProgressBar progressBar ;
    public String loadJoke;
	public boolean testFlag = false; 

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        Button button = (Button)root.findViewById(R.id.joke_btn);
        progressBar = (ProgressBar)root.findViewById(R.id.joke_progressbar);
        progressBar.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                getJoke();
            }
        });
        return root;
    }

    private void getJoke() {
        new EndpointsAsyncTask().execute(this);
    }

    void makeAJoke()
    {
		if (!testFlag) { 
        Context context = getActivity();
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY,loadJoke);
        context.startActivity(intent);
        progressBar.setVisibility(View.GONE);
		}
    }
}
