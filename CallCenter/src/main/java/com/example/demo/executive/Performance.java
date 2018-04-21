package com.example.demo.executive;

import java.util.ArrayList;
import java.util.List;

public class Performance {

	private Manager manager=new Manager();
	private List<JuniorExecutive> junior_executives=new ArrayList<JuniorExecutive>();
	private  List<SeniorExecutive> senior_executives=new ArrayList<SeniorExecutive>();
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public List<JuniorExecutive> getJunior_executives() {
		return junior_executives;
	}
	public void setJunior_executives(List<JuniorExecutive> junior_executives) {
		this.junior_executives = junior_executives;
	}
	public List<SeniorExecutive> getSenior_executives() {
		return senior_executives;
	}
	public void setSenior_executives(List<SeniorExecutive> senior_executives) {
		this.senior_executives = senior_executives;
	}
	
	
}
