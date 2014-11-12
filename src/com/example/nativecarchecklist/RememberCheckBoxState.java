package com.example.nativecarchecklist;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.CheckBox;

public class RememberCheckBoxState {

	private SharedPreferences pref;
	private CheckBox[] cbs;
	private int size;

	public RememberCheckBoxState() {
	}
	// set initial 
	public RememberCheckBoxState(Activity main, String groupName, int size) {
		pref = main.getSharedPreferences(groupName, 0);
		cbs = new CheckBox[size];
		this.size = size;
	}

	// datatype of cbName is string but put number
	public void checkBoxMapping(CheckBox cb, String index) {
		cbs[Integer.parseInt(index)] = cb;
		Editor edit = pref.edit();
		edit.putBoolean(index, cb.isChecked());
		edit.commit();
	}

	// call this method always startActivity *MainActivity.java
	public void restoreStateCB() {
		for (int i = 0; i < size; i++) {
			String key = String.valueOf(i);
			boolean state = pref.getBoolean(key, false);
			cbs[i].setChecked(state);
		}
	}
}
