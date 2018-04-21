package com.example.demo.vo;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.executive.Performance;


public class CallResponse {

	private int number_of_calls;
	private int resolved;
	private int unresolved;
	private int totalTimeTakenInMinutes;
	private List<Performance> performance=new ArrayList<Performance>();
	public int getResolved() {
		return resolved;
	}
	public void setResolved(int resolved) {
		this.resolved = resolved;
	}
	public int getNumber_of_calls() {
		return number_of_calls;
	}
	public void setNumber_of_calls(int number_of_calls) {
		this.number_of_calls = number_of_calls;
	}
	public int getUnresolved() {
		return unresolved;
	}
	public void setUnresolved(int unresolved) {
		this.unresolved = unresolved;
	}
	public int getTotalTimeTakenInMinutes() {
		return totalTimeTakenInMinutes;
	}
	public void setTotalTimeTakenInMinutes(int totalTimeTakenInMinutes) {
		this.totalTimeTakenInMinutes = totalTimeTakenInMinutes;
	}
	public List<Performance> getPerformance() {
		return performance;
	}
	public void setPerformance(List<Performance> performance) {
		this.performance = performance;
	}

	
}
