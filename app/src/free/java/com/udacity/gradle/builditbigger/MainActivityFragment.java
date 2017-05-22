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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.magdy.joke.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private boolean mAdsOnScreen;

    public MainActivityFragment() {
    }
    ProgressBar progressBar ;
    public String loadJoke;
	public boolean testFlag = false;
    InterstitialAd mInterstitialAd;
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
                if (mInterstitialAd.isLoaded()) {
                    mAdsOnScreen = true;
                    mInterstitialAd.show();
                    progressBar.setVisibility(View.GONE);
                } else {
                    mAdsOnScreen = false;
                    getJoke();
                }
            }
        });

        // Instantiate the InterstitialAd object
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                mAdsOnScreen = false;
                getJoke();
            }
        });
        requestNewInterstitial();

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);

    }

    private void getJoke() {
        new EndpointsAsyncTask().execute(this);
    }

    void makeAJoke()
    {
		if (!testFlag) {
            if(!mAdsOnScreen) {
                Context context = getActivity();
                Intent intent = new Intent(context, JokeActivity.class);
                intent.putExtra(JokeActivity.JOKE_KEY, loadJoke);
                context.startActivity(intent);
                progressBar.setVisibility(View.GONE);
            }
            else
            {
                progressBar.setVisibility(View.VISIBLE);
            }
		}
    }
}
