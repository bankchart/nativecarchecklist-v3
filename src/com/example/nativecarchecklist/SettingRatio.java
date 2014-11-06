package com.example.nativecarchecklist;

import java.util.Map; 
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class SettingRatio {

	private Map<String, Integer> list;
	private String language;

	public SettingRatio() {
		list = new HashMap<String, Integer>();
		list.put("exterior", 3);
		list.put("interior", 3);
		list.put("power", 3);
		list.put("engine", 3);
		list.put("document", 3);
	}

	public int getRatio(String ratioName){
		Iterator<Entry<String, Integer>> it = list.entrySet().iterator();
		int checked = 0;
		while(it.hasNext()){
			Map.Entry<String, Integer> data = (Map.Entry<String, Integer>) it.next();
			if(data.getKey().equals(ratioName)){
				checked = 1;
				break;
			}
		}
		return checked;
	}
	
	public int[] getAllRatio() {
		Iterator<Entry<String, Integer>> it = list.entrySet().iterator();
		int[] tmp = new int[5];
		int i = 0;
		while (it.hasNext()) {
			Map.Entry<String, Integer> data = (Map.Entry<String, Integer>) it.next();
			tmp[i] = Integer.parseInt(data.getValue() + "");
			i++;
		}
		return tmp;
	}

	public void setLanguage(String language){
		this.language = language;
	}
	
	public String getLanguage(){
		return language;
	}
}
