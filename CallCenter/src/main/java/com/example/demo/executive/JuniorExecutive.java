package com.example.demo.executive;

public class JuniorExecutive {

	private String id;
	private int timeTakenInMinutes;
	private int callsAttended;
	private int resolved;
	private int escalated;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getTimeTakenInMinutes() {
		return timeTakenInMinutes;
	}
	public void setTimeTakenInMinutes(int timeTakenInMinutes) {
		this.timeTakenInMinutes = timeTakenInMinutes;
	}
	public int getCallsAttended() {
		return callsAttended;
	}
	public void setCallsAttended(int callsAttended) {
		this.callsAttended = callsAttended;
	}
	public int getResolved() {
		return resolved;
	}
	public void setResolved(int resolved) {
		this.resolved = resolved;
	}
	public int getEscalated() {
		return escalated;
	}
	public void setEscalated(int escalated) {
		this.escalated = escalated;
	}
	
}
