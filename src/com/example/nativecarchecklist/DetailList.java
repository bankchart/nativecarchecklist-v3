package com.example.nativecarchecklist;

public class DetailList {

	// private Map<String, Integer> list;
	private String detailName;
	private int listSize;
	private int checked;
	private int divide;
	private double percent;

	/*
	 * public DetailList(String detailName, Map<String, Integer> list){
	 * this.detailName = detailName; this.list = list; }
	 */

	public DetailList(String detailName, int listSize) {
		this.detailName = detailName;
		this.listSize = listSize;	
		percent = 0;
	}

	public void setPercent(double percent){
		this.percent = percent;
	}
	
	public double getPercent(){
		return percent;
	}
	
	public double divideProgress() {
		double result = (double) 100 / listSize;
		return result;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	public String getDetailName() {
		return detailName;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getListSize() {
		return listSize;
	}

	public void addChecked() {		
		checked++;
	}

	public void unChecked() {		
		checked--;
	}

	public int getChecked() {
		return checked;
	}

	/*
	 * public int getSizeCheck() { int sum = 0; Iterator it =
	 * list.entrySet().iterator(); while (it.hasNext()) { it.next(); sum++; }
	 * return sum; }
	 */

	/*
	 * public int countChecked() { int sum = 0; Iterator it =
	 * list.entrySet().iterator(); while (it.hasNext()) { Map.Entry<String,
	 * Integer> tmp = (Map.Entry<String, Integer>) it.next(); if (tmp.getValue()
	 * == 1) sum++;
	 * 
	 * } return sum; }
	 */
}
