package com.example.nativecarchecklist;

import java.util.Locale;

import android.os.Bundle;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private boolean[] menuIsShow;

	private final int POWER_INDEX = 0;
	private final int ENGINE_INDEX = 1;
	private final int EXTERIOR_INDEX = 2;
	private final int INTERIOR_INDEX = 3;
	private final int DOCUMENT_INDEX = 4;
	private final int SETTING_INDEX = 5;
	private final int POWER_SIZE = 27;
	private final int ENGINE_SIZE = 11;
	private final int EXTERIOR_SIZE = 9;
	private final int INTERIOR_SIZE = 15;
	private final int DOCUMENT_SIZE = 9;
	private final int DEFAULT_PRIORITY = 3;

	private DetailList powerList = new DetailList("power", POWER_SIZE);
	private DetailList engineList = new DetailList("engine", ENGINE_SIZE);
	private DetailList exteriorList = new DetailList("exterior", EXTERIOR_SIZE);
	private DetailList interiorList = new DetailList("interior", INTERIOR_SIZE);
	private DetailList documentList = new DetailList("document", DOCUMENT_SIZE);

	private RatingCheckList rating = new RatingCheckList();

	private int priority[] = new int[] { 3, 3, 3, 3, 3 };

	private TextView engineProgressText;
	private TextView powerProgressText;
	private TextView exteriorProgressText;
	private TextView interiorProgressText;
	private TextView documentProgressText;
	private TextView ratingLabel;
	private TextView ratingPercent;

	private ProgressBar engineProgress;
	private ProgressBar powerProgress;
	private ProgressBar exteriorProgress;
	private ProgressBar interiorProgress;
	private ProgressBar documentProgress;
	private ProgressBar ratingProgress;
	private SeekBar engineVolume;
	private SeekBar powerVolume;
	private SeekBar exteriorVolume;
	private SeekBar interiorVolume;
	private SeekBar documentVolume;
	
	private Locale myLocale;

	private Button saveBtn, resetBtn;

	public void onClickChecked(View v) {
		String yourCheck = (String) v.getTag();
		System.out.println("test");
		if ("power".equals(yourCheck)) {
			double divide = powerList.divideProgress();
			int percentValue;
			double tmp;
			if (((CheckBox) v).isChecked()) {
				powerList.addChecked();
				tmp = powerList.getPercent() + divide;
				percentValue = (int) tmp;
			} else {
				powerList.unChecked();
				tmp = powerList.getPercent() - divide;
				percentValue = (int) tmp;
			}

			if (100 - percentValue < divide || percentValue > 100) {
				percentValue = 100;
				tmp = 100;
			}
			powerList.setPercent(tmp);
			powerProgress.setProgress(percentValue);
			powerProgressText.setText(percentValue + " %");
		} else if ("engine".equals(yourCheck)) {
			double divide = engineList.divideProgress();
			int percentValue;
			double tmp;
			if (((CheckBox) v).isChecked()) {
				engineList.addChecked();
				tmp = engineList.getPercent() + divide;
				percentValue = (int) tmp;
			} else {
				engineList.unChecked();
				tmp = engineList.getPercent() - divide;
				percentValue = (int) tmp;
			}

			if (100 - percentValue < divide || percentValue > 100) {
				percentValue = 100;
				tmp = 100;
			}
			engineList.setPercent(tmp);
			engineProgress.setProgress(percentValue);
			engineProgressText.setText(percentValue + " %");
		} else if ("exterior".equals(yourCheck)) {
			double divide = exteriorList.divideProgress();
			int percentValue;
			double tmp;
			if (((CheckBox) v).isChecked()) {
				exteriorList.addChecked();
				tmp = exteriorList.getPercent() + divide;
				percentValue = (int) tmp;
			} else {
				exteriorList.unChecked();
				tmp = exteriorList.getPercent() - divide;
				percentValue = (int) tmp;
			}

			if (100 - percentValue < divide || percentValue > 100) {
				percentValue = 100;
				tmp = 100;
			}
			exteriorList.setPercent(tmp);
			exteriorProgress.setProgress(percentValue);
			exteriorProgressText.setText(percentValue + " %");
		} else if ("interior".equals(yourCheck)) {
			double divide = interiorList.divideProgress();
			int percentValue;
			double tmp;
			if (((CheckBox) v).isChecked()) {
				interiorList.addChecked();
				tmp = interiorList.getPercent() + divide;
				percentValue = (int) tmp;
			} else {
				interiorList.unChecked();
				tmp = interiorList.getPercent() - divide;
				percentValue = (int) tmp;
			}

			if (100 - percentValue < divide || percentValue > 100) {
				percentValue = 100;
				tmp = 100;
			}
			interiorList.setPercent(tmp);
			interiorProgress.setProgress(percentValue);
			interiorProgressText.setText(percentValue + " %");
		} else { // document
			double divide = documentList.divideProgress();
			int percentValue;
			double tmp;
			if (((CheckBox) v).isChecked()) {
				documentList.addChecked();
				tmp = documentList.getPercent() + divide;
				percentValue = (int) tmp;
			} else {
				documentList.unChecked();
				tmp = documentList.getPercent() - divide;
				percentValue = (int) tmp;
			}

			if (100 - percentValue < divide || percentValue > 100) {
				percentValue = 100;
				tmp = 100;
			}
			documentList.setPercent(tmp);
			documentProgress.setProgress(percentValue);
			documentProgressText.setText(percentValue + " %");
		}
		ratingCheckList();
	}

	public void savePriority() {
		priority[0] = exteriorVolume.getProgress() + 1;
		priority[1] = interiorVolume.getProgress() + 1;
		priority[2] = powerVolume.getProgress() + 1;
		priority[3] = engineVolume.getProgress() + 1;
		priority[4] = documentVolume.getProgress() + 1;
		checkSlide();
		ratingCheckList();
		Toast.makeText(getApplicationContext(), "SAVED", Toast.LENGTH_LONG)
				.show();
	}

	public void resetPriority() {
		priority[0] = DEFAULT_PRIORITY - 1;
		priority[1] = DEFAULT_PRIORITY - 1;
		priority[2] = DEFAULT_PRIORITY - 1;
		priority[3] = DEFAULT_PRIORITY - 1;
		priority[4] = DEFAULT_PRIORITY - 1;
		exteriorVolume.setProgress(priority[0]);
		interiorVolume.setProgress(priority[1]);
		powerVolume.setProgress(priority[2]);
		engineVolume.setProgress(priority[3]);
		documentVolume.setProgress(priority[4]);
		checkSlide();
		ratingCheckList();
		Toast.makeText(getApplicationContext(), "RESETED", Toast.LENGTH_LONG)
				.show();
	}

	public void ratingCheckList() {

		/*
		 * rating.setExterior(exteriorVolume.getProgress());
		 * rating.setInterior(interiorVolume.getProgress());
		 * rating.setPower(powerVolume.getProgress());
		 * rating.setEngine(engineVolume.getProgress());
		 * rating.setDocument(documentVolume.getProgress());
		 */
		rating.setPriority(priority[0], // exterior
				priority[1], // interior
				priority[2], // power
				priority[3], // engine
				priority[4] // document
		);
		double ratingTmp = rating.getRating(exteriorProgress.getProgress(), // exterior
				interiorProgress.getProgress(), // interior
				powerProgress.getProgress(), // power
				engineProgress.getProgress(), // engine
				documentProgress.getProgress() // document
				);
		if (ratingTmp > 0) {
			ratingLabel.setVisibility(View.VISIBLE);
			ratingPercent.setText((int) ratingTmp + " %");
			ratingPercent.setVisibility(View.VISIBLE);
			ratingProgress.setVisibility(View.VISIBLE);
			ratingProgress.setProgress((int) ratingTmp);
		} else {
			ratingLabel.setVisibility(View.INVISIBLE);
			ratingPercent.setVisibility(View.INVISIBLE);
			ratingProgress.setVisibility(View.INVISIBLE);
			ratingProgress.setProgress(0);
			ratingPercent.setText((int) ratingTmp + " %");
		}
	}

	public void leaveChecklist(int motionin, int motionout, int fragment,
			int menuIndex) {
		menuIsShow[menuIndex] = false;
		getPreferences(MODE_PRIVATE).edit().putInt("already", 1).commit();
		FragmentTransaction ft = getFragmentManager().beginTransaction()
				.setCustomAnimations(motionin, motionout);
		FragmentManager fm = getFragmentManager();
		final Fragment fmTarget = fm.findFragmentById(fragment);
		ft.hide(fmTarget);
		ft.commit();
	}

	public void menuToggle(int motionin, int motionout, int fragment,
			int menuIndex) {
		int prefer = getPreferences(MODE_PRIVATE).getInt("already", 1);
		if (prefer == 1) {
			menuIsShow[menuIndex] = true;
			getPreferences(MODE_PRIVATE).edit().putInt("already", 0).commit();

			FragmentTransaction ft = getFragmentManager().beginTransaction()
					.setCustomAnimations(motionin, motionout);
			FragmentManager fm = getFragmentManager();
			Fragment fmTarget = fm.findFragmentById(fragment);
			ft.show(fmTarget);
			ft.commit();
		} else {
			menuIsShow[menuIndex] = false;
			getPreferences(MODE_PRIVATE).edit().putInt("already", 1).commit();
			FragmentTransaction ft = getFragmentManager().beginTransaction()
					.setCustomAnimations(motionin, motionout);
			FragmentManager fm = getFragmentManager();
			final Fragment fmTarget = fm.findFragmentById(fragment);
			ft.hide(fmTarget);
			ft.commit();
		}
	}

	public void checkSlide() {
		for (int i = 0; i < menuIsShow.length; i++) {
			if (menuIsShow[i]) {
				// FragmentTransaction ft;
				switch (i) {
				case 0:			
					
					/*Fragment powerFm = getFragmentManager().findFragmentById(R.id.power_fm);
					FragmentTransaction fts = getFragmentManager().beginTransaction();
					fts.setCustomAnimations(R.animator.power_motion_in, R.animator.power_motion_out);
					fts.replace(R.id.engine_fm, powerFm, null);
					fts.commit();*/
					FragmentTransaction ft_0;
					getPreferences(MODE_PRIVATE).edit().putInt("already", 1)
							.commit();
					ft_0 = getFragmentManager().beginTransaction()
							.setCustomAnimations(R.animator.power_motion_in,
									R.animator.power_motion_out);
					FragmentManager fm_0 = getFragmentManager();
					final Fragment powerFm = fm_0
							.findFragmentById(R.id.power_fm);
					ft_0.hide(powerFm);
					ft_0.commit();
					break;
				case 1:					
					FragmentTransaction ft_1;
					getPreferences(MODE_PRIVATE).edit().putInt("already", 1)
							.commit();
					ft_1 = getFragmentManager().beginTransaction()
							.setCustomAnimations(R.animator.engine_motion_in,
									R.animator.engine_motion_out);
					FragmentManager fm_1 = getFragmentManager();
					final Fragment engineFm = fm_1
							.findFragmentById(R.id.engine_fm);
					ft_1.hide(engineFm);
					ft_1.commit();
					break;
				case 2:
					FragmentTransaction ft_2;
					getPreferences(MODE_PRIVATE).edit().putInt("already", 1)
							.commit();
					ft_2 = getFragmentManager().beginTransaction()
							.setCustomAnimations(R.animator.exterior_motion_in,
									R.animator.exterior_motion_out);
					FragmentManager fm_2 = getFragmentManager();
					final Fragment exteriorFm = fm_2
							.findFragmentById(R.id.exterior_fm);
					ft_2.hide(exteriorFm);
					ft_2.commit();
					break;
				case 3:
					FragmentTransaction ft_3;
					getPreferences(MODE_PRIVATE).edit().putInt("already", 1)
							.commit();
					ft_3 = getFragmentManager().beginTransaction()
							.setCustomAnimations(R.animator.interior_motion_in,
									R.animator.interior_motion_out);
					FragmentManager fm_3 = getFragmentManager();
					final Fragment interiorFm = fm_3
							.findFragmentById(R.id.interior_fm);
					ft_3.hide(interiorFm);
					ft_3.commit();
					break;
				case 4:
					FragmentTransaction ft_4;
					getPreferences(MODE_PRIVATE).edit().putInt("already", 1)
							.commit();
					ft_4 = getFragmentManager().beginTransaction()
							.setCustomAnimations(R.animator.document_motion_in,
									R.animator.document_motion_out);
					FragmentManager fm_4 = getFragmentManager();
					final Fragment documentFm = fm_4
							.findFragmentById(R.id.document_fm);
					ft_4.hide(documentFm);
					ft_4.commit();
					break;
				case 5:
					FragmentTransaction ft_5;
					getPreferences(MODE_PRIVATE).edit().putInt("already", 1)
							.commit();
					ft_5 = getFragmentManager().beginTransaction()
							.setCustomAnimations(R.animator.setting_motion_in,
									R.animator.setting_motion_out);
					FragmentManager fm_5 = getFragmentManager();
					final Fragment settingFm = fm_5
							.findFragmentById(R.id.setting_fm);
					ft_5.hide(settingFm);
					ft_5.commit();
					break;
				}
			}
			menuIsShow[i] = false;
		}
	}
	
	public void setLocale(String lang) {
		 
        myLocale = new Locale(lang);
        Resources res = getResources();
        //DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, null);
        //Intent refresh = new Intent(this, AndroidLocalize.class);
        onCreate(null);
        //startActivity(refresh);
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ratingProgress = (ProgressBar) findViewById(R.id.ratingProgress);

		engineProgressText = (TextView) findViewById(R.id.engineProgressText);
		powerProgressText = (TextView) findViewById(R.id.powerProgressText);
		exteriorProgressText = (TextView) findViewById(R.id.exteriorProgressText);
		interiorProgressText = (TextView) findViewById(R.id.interiorProgressText);
		documentProgressText = (TextView) findViewById(R.id.documentProgressText);
		ratingLabel = (TextView) findViewById(R.id.ratingLabel);
		ratingPercent = (TextView) findViewById(R.id.ratingPercent);

		engineProgress = (ProgressBar) findViewById(R.id.engineProgress);
		powerProgress = (ProgressBar) findViewById(R.id.powerProgress);
		exteriorProgress = (ProgressBar) findViewById(R.id.exteriorProgress);
		interiorProgress = (ProgressBar) findViewById(R.id.interiorProgress);
		documentProgress = (ProgressBar) findViewById(R.id.documentProgress);

		engineVolume = (SeekBar) findViewById(R.id.engineVolume);
		powerVolume = (SeekBar) findViewById(R.id.powerVolume);
		exteriorVolume = (SeekBar) findViewById(R.id.exteriorVolume);
		interiorVolume = (SeekBar) findViewById(R.id.interiorVolume);
		documentVolume = (SeekBar) findViewById(R.id.documentVolume);

		engineProgressText.setText("0 %");
		powerProgressText.setText("0 %");
		exteriorProgressText.setText("0 %");
		interiorProgressText.setText("0 %");
		documentProgressText.setText("0 %");

		engineVolume.setProgress(2);
		powerVolume.setProgress(2);
		exteriorVolume.setProgress(2);
		interiorVolume.setProgress(2);
		documentVolume.setProgress(2);

		saveBtn = (Button) findViewById(R.id.saveBtn);
		resetBtn = (Button) findViewById(R.id.resetBtn);

		ratingLabel.setVisibility(View.INVISIBLE); // hide
		ratingPercent.setVisibility(View.INVISIBLE); // hide
		ratingProgress.setRotation(180);
		ratingProgress.setVisibility(View.INVISIBLE); // hide

		/*
		 * ProgressBar engineProgress = (ProgressBar)
		 * findViewById(R.id.engineProgress);
		 * engineProgress.getProgressDrawable().setColorFilter(Color.BLUE,
		 * Mode.AVOID);
		 */
		// engineProgressText.setTextColor(Color.WHITE);
		// engineProgressText.setText("65");

		final TableLayout powerLayout = (TableLayout) findViewById(R.id.power_layout);
		powerLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});

		final TableLayout engineLayout = (TableLayout) findViewById(R.id.engine_layout);
		engineLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		final TableLayout exteriorLayout = (TableLayout) findViewById(R.id.exterior_layout);
		exteriorLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		final TableLayout interiorLayout = (TableLayout) findViewById(R.id.interior_layout);
		interiorLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		final LinearLayout documentLayout = (LinearLayout) findViewById(R.id.document_layout);
		documentLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		final TableLayout settingLayout = (TableLayout) findViewById(R.id.setting_layout);
		settingLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		menuIsShow = new boolean[] { false, // power
				false, // engine
				false, // exterior
				false, // interior
				false, // document
				false // setting
		};

		RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainlayout);

		mainLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkSlide();
			}

		});

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		FragmentManager fm = getFragmentManager();

		Fragment engineFm = fm.findFragmentById(R.id.engine_fm);

		// Fragment engineSubFm = fm.findFragmentById(R.id.enginesub_fm);

		Fragment powerFm = fm.findFragmentById(R.id.power_fm);

		Fragment exteriorFm = fm.findFragmentById(R.id.exterior_fm);

		Fragment interiorFm = fm.findFragmentById(R.id.interior_fm);

		Fragment documentFm = fm.findFragmentById(R.id.document_fm);

		Fragment settingFm = fm.findFragmentById(R.id.setting_fm);

		Button engineBackBtn = (Button) findViewById(R.id.engineBackBtn);
		Button powerBackBtn = (Button) findViewById(R.id.powerBackBtn);
		Button exteriorBackBtn = (Button) findViewById(R.id.exteriorBackBtn);
		Button interiorBackBtn = (Button) findViewById(R.id.interiorBackBtn);
		Button documentBackBtn = (Button) findViewById(R.id.documentBackBtn);

		ft.hide(engineFm);
		// ft.hide(engineSubFm);
		ft.hide(powerFm);
		ft.hide(exteriorFm);
		ft.hide(interiorFm);
		ft.hide(documentFm);
		ft.hide(settingFm);

		ft.commit();

		LinearLayout engineLayoutBtn = (LinearLayout) findViewById(R.id.engine_layoutBtn);
		// RelativeLayout engineLayoutBtn = (RelativeLayout)
		// findViewById(R.id.engine_layoutBtn);
		LinearLayout powerLayoutBtn = (LinearLayout) findViewById(R.id.power_layoutBtn);
		LinearLayout allDialog = (LinearLayout) findViewById(R.id.engineLayout);
		LinearLayout exterior_layoutBtn = (LinearLayout) findViewById(R.id.exterior_layoutBtn);
		LinearLayout interior_layoutBtn = (LinearLayout) findViewById(R.id.interior_layoutBtn);
		LinearLayout document_layoutBtn = (LinearLayout) findViewById(R.id.document_layoutBtn);
		// LinearLayout setting_layoutBtn = (LinearLayout)
		// findViewById(R.id.setting_layoutBtn);
		ImageButton setting_layoutBtn = (ImageButton) findViewById(R.id.setting_layoutBtn);
		allDialog.setAlpha(0.9f);

		getPreferences(MODE_PRIVATE).edit().putInt("already", 1).commit();

		// engine**********************************************************************

		engineLayoutBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				int compareMenu = -1;
				for (int i = 0; i < menuIsShow.length; i++) {
					if (menuIsShow[i]) {
						compareMenu = i;
					}
				}
				if (compareMenu != ENGINE_INDEX) {
					checkSlide();
				}
				menuToggle(R.animator.engine_motion_in,
						R.animator.engine_motion_out, R.id.engine_fm,
						ENGINE_INDEX);
			}
		});

		engineBackBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				leaveChecklist(R.animator.engine_motion_in,
						R.animator.engine_motion_out, R.id.engine_fm,
						ENGINE_INDEX);
			}
		});

		// power**********************************************************************
	
		powerLayoutBtn.setOnClickListener(new OnClickListener() {
			@SuppressLint("NewApi") public void onClick(View v) {
				int compareMenu = -1;
				for (int i = 0; i < menuIsShow.length; i++) {
					if (menuIsShow[i]) {
						compareMenu = i;
					}
				}
				if (compareMenu != POWER_INDEX) {
					checkSlide();
					/*FragmentTransaction fragmentTransaction =
						      getFragmentManager().beginTransaction();

						    fragmentTransaction.setCustomAnimations(
						      R.animator.power_motion_in, R.animator.power_motion_out,
						      R.animator.power_motion_in, R.animator.power_motion_out);
						    Fragment powerFm = getFragmentManager().findFragmentById(R.id.power_fm);
						    fragmentTransaction.replace(R.id.engine_fm, powerFm);
						    fragmentTransaction.addToBackStack(null);
						    fragmentTransaction.commit();*/
				}
				menuToggle(R.animator.power_motion_in,
						R.animator.power_motion_out, R.id.power_fm, POWER_INDEX);
			}
		});

		powerBackBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				leaveChecklist(R.animator.power_motion_in,
						R.animator.power_motion_out, R.id.power_fm, POWER_INDEX);
			}
		});
		// exterior

		exterior_layoutBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				int compareMenu = -1;
				for (int i = 0; i < menuIsShow.length; i++) {
					if (menuIsShow[i]) {
						compareMenu = i;
					}
				}
				if (compareMenu != EXTERIOR_INDEX) {
					checkSlide();
				}
				menuToggle(R.animator.exterior_motion_in,
						R.animator.exterior_motion_out, R.id.exterior_fm,
						EXTERIOR_INDEX);
			}
		});

		exteriorBackBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				leaveChecklist(R.animator.exterior_motion_in,
						R.animator.exterior_motion_out, R.id.exterior_fm,
						EXTERIOR_INDEX);
			}
		});

		// interior

		interior_layoutBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				int compareMenu = -1;
				for (int i = 0; i < menuIsShow.length; i++) {
					if (menuIsShow[i]) {
						compareMenu = i;
					}
				}
				if (compareMenu != INTERIOR_INDEX) {
					checkSlide();
				}
				menuToggle(R.animator.interior_motion_in,
						R.animator.interior_motion_out, R.id.interior_fm,
						INTERIOR_INDEX);
			}
		});

		interiorBackBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				leaveChecklist(R.animator.interior_motion_in,
						R.animator.interior_motion_out, R.id.interior_fm,
						INTERIOR_INDEX);
			}
		});

		// document

		document_layoutBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				int compareMenu = -1;
				for (int i = 0; i < menuIsShow.length; i++) {
					if (menuIsShow[i]) {
						compareMenu = i;
					}
				}
				if (compareMenu != DOCUMENT_INDEX) {
					checkSlide();
				}
				menuToggle(R.animator.document_motion_in,
						R.animator.document_motion_out, R.id.document_fm,
						DOCUMENT_INDEX);
			}
		});

		documentBackBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				leaveChecklist(R.animator.document_motion_in,
						R.animator.document_motion_out, R.id.document_fm,
						DOCUMENT_INDEX);
			}
		});

		// setting

		setting_layoutBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				int compareMenu = -1;
				for (int i = 0; i < menuIsShow.length; i++) {
					if (menuIsShow[i]) {
						compareMenu = i;
					}
				}
				if (compareMenu != SETTING_INDEX) {
					checkSlide();
				}
				menuToggle(R.animator.setting_motion_in,
						R.animator.setting_motion_out, R.id.setting_fm,
						SETTING_INDEX);
			}
		});

		saveBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				savePriority();
			}
		});

		resetBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				resetPriority();
			}
		});

	}

}
