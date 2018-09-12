package com.davidhoja.fallingball.android;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.davidhoja.freakyball.FallingBall;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;


public class AndroidLauncher extends AndroidApplication {
	
	protected void onCreate (Bundle savedInstanceState) {
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		
		super.onCreate(savedInstanceState);
		
		  // Create the layout
        RelativeLayout layout = new RelativeLayout(this);

        // Do the stuff that initialize() would do for you
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        // Create the libgdx View
        View gameView = initializeForView(new FallingBall(), cfg);
        gameView.setKeepScreenOn(true);

        // Create and setup the AdMob view
        AdView adView = new AdView(this);
        adView.setAdUnitId("ca-app-pub-8771472292850506/4001933679");
        adView.setAdSize(AdSize.BANNER);
        
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("D3984DA31DF3AD85D132E1CE87231D").build();

        
        adView.loadAd(adRequest);

        // Add the libgdx view
        layout.addView(gameView);

        // Add the AdMob view
        RelativeLayout.LayoutParams adParams = 
            new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        layout.addView(adView, adParams);

        // Hook it all up
        setContentView(layout);
		
	}
}
