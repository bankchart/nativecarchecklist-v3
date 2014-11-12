package com.example.nativecarchecklist;

import java.util.Locale; 


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

/*import android.widget.ImageView;
 import android.widget.TextView;*/

public class ChangeLanguage extends Activity {
	Locale myLocale;
	ProgressDialog dialog;
	Intent i;
	ImageButton th, us, ch, jp;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_lang);
		i = getIntent();
		th = (ImageButton) findViewById(R.id.th);
		us = (ImageButton) findViewById(R.id.us);
		ch = (ImageButton) findViewById(R.id.ch);
		jp = (ImageButton) findViewById(R.id.jp);

		th.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setLocale("th");
				// i.putExtra("lang", "th");
			}
		});

		us.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setLocale("us");
				// i.putExtra("lang", "us");
			}
		});

		ch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setLocale("ch");
				// i.putExtra("lang", "ch");
			}
		});

		jp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setLocale("jp");
				// i.putExtra("lang", "jp");
			}
		});
	}

	public void setLocale(String lang) {
		//Toast.makeText(getApplicationContext(), lang, Toast.LENGTH_LONG).show();

		myLocale = new Locale(lang);
		Resources res = getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		Configuration conf = res.getConfiguration();
		conf.locale = myLocale;
		res.updateConfiguration(conf, dm);
		
		
		Intent nextScreen = new Intent(getApplicationContext(),
				MainActivity.class);
		nextScreen.putExtra("lang", "lang");
		finish();
		startActivity(nextScreen);
		

	}

}
