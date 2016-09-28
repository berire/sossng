package com.mygdx.game;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class AndroidLauncher extends AndroidApplication implements IActivityRequestHandler  {

	protected AdView adView;

	private final int SHOW_ADS = 1;
	private final int HIDE_ADS = 0;
	private InterstitialAd mInterstitialAd;
	private static int ad=0;

	private Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
				case SHOW_ADS:
				{
					break;
				}
				case HIDE_ADS:
				{
					break;
				}
			}
		}
	};

	@Override
	public void onBackPressed() {
		///
	}
	@Override public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		// Create the layout
		RelativeLayout layout = new RelativeLayout(this);



		// Do the stuff that initialize() would do for you
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		SOSGame GAME = new SOSGame(this);
		// Create the libgdx View
		View gameView = initializeForView(GAME,config);

		// Create and setup the AdMob view
		final com.google.android.gms.ads.AdRequest adRequest = new com.google.android.gms.ads.AdRequest.Builder()
				//.build();
				// Add a test device to show Test Ads
				.addTestDevice(com.google.android.gms.ads.AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("abc")
				.build();

		//////7INTER

		mInterstitialAd = new InterstitialAd(this);
		// set the ad unit ID
		mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
		mInterstitialAd.loadAd(adRequest);
		mInterstitialAd.setAdListener(new AdListener() {
			public void onAdLoaded() {
			}
			@Override
			public void onAdClosed() {
				mInterstitialAd.loadAd(adRequest);
			}
		});


		adView = new AdView(this);
		adView.setAdUnitId("xxxxxxx");
		adView.setAdSize(AdSize.FULL_BANNER);
		adView.loadAd(adRequest);

		// Add the libgdx view
		layout.addView(gameView);

		// Add the AdMob view
		RelativeLayout.LayoutParams adParams =
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

		layout.addView(adView, adParams);

		// Hook it all up
		setContentView(layout);
	}

	// This is the callback that posts a message for the handler
	@Override
	public void showAds( ) {
		try {
			runOnUiThread(new Runnable() {
				public void run() {
					if (mInterstitialAd.isLoaded()) {
						mInterstitialAd.show();
						Toast.makeText(getApplicationContext(), "Showing Interstitial", Toast.LENGTH_SHORT).show();
					}
					else {
						AdRequest interstitialRequest = new AdRequest.Builder().addTestDevice(com.google.android.gms.ads.AdRequest.DEVICE_ID_EMULATOR)
								.addTestDevice("abc")
								.build();

						mInterstitialAd.loadAd(interstitialRequest);
						Toast.makeText(getApplicationContext(), "Loading Interstitial", Toast.LENGTH_SHORT).show();
					}
				}
			});
		} catch (Exception e) {
		}

	}
}