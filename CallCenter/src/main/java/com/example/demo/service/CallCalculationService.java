package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.JuniorEscalationException;
import com.example.demo.exception.ManagerEscaltionException;
import com.example.demo.exception.SeniorEscalationException;
import com.example.demo.executive.JuniorExecutive;
import com.example.demo.executive.Manager;
import com.example.demo.executive.Performance;
import com.example.demo.executive.SeniorExecutive;
import com.example.demo.request.CallRequest;
import com.example.demo.vo.CallResponse;

@Service
public class CallCalculationService {

	private CallResponse callRes=new CallResponse();
	/**
	 * 
	 * @param callRequest
	 * @return
	 */
	public CallResponse calculateRequest(CallRequest callRequest)
	{
		
		callRes.setNumber_of_calls(Integer.parseInt(callRequest.getNumber_of_calls()));
			Performance performance=new Performance();
		String[] jeAttend=callRequest.getJe();
		for (int i=0;i<jeAttend.length;i++) {
			int number=i+1;
			String type="je"+number;
			int threshold=7;
			String calls=jeAttend[i];
			Object obj=doEscalte(type,threshold,calls);
			performance.getJunior_executives().add((JuniorExecutive)obj);
		}
		String[] seAttend=callRequest.getSe();
		for (int i=0;i<seAttend.length;i++) {
			int number=i+1;
			String type="se"+number;
			int threshold=10;
			String calls=seAttend[i];
			Object obj=doEscalte(type,threshold,calls);
			performance.getSenior_executives().add((SeniorExecutive)obj);
		}
		String mgrAttend=callRequest.getMgr();
		Object obj=doEscalte("mgr",15,mgrAttend);
		performance.setManager((Manager)obj);
		List<Performance> performanceList =new ArrayList<Performance>();
		performanceList.add(performance);
		callRes.setPerformance(performanceList);
		return callRes;
	}
	
	/**
	 * 
	 * 
	 * @param type
	 * @param threshold
	 * @param calls
	 * @return
	 */
	private Object doEscalte(String type,int threshold,String calls) 
	{
		Object obj=null;
		String[] callArray=calls.split(",");
		int numberOfCalls=callArray.length;
		if(type.contains("je"))
		{
			JuniorExecutive jr=new JuniorExecutive();
			jr.setCallsAttended(numberOfCalls);
			jr.setId(type);
			int totalTime=0;
			for (String string : callArray) {
				int value=Integer.parseInt(string);
				totalTime=totalTime+value;
				try
				{
					if(value>threshold)
					{
						throw new JuniorEscalationException("Time exceed");
					}
					else
					{
						jr.setResolved(jr.getResolved()+1);
						callRes.setResolved(callRes.getResolved()+1);
					}
				}
				catch(JuniorEscalationException e)
				{
					jr.setEscalated(jr.getEscalated()+1);
					callRes.setUnresolved(callRes.getUnresolved()+1);
				}
				
			}
			jr.setTimeTakenInMinutes(totalTime);
			callRes.setTotalTimeTakenInMinutes(callRes.getTotalTimeTakenInMinutes()+totalTime);
			obj=jr;
		}
		else if(type.contains("se"))
		{
			SeniorExecutive jr=new SeniorExecutive();
			jr.setCallsAttended(numberOfCalls);
			jr.setId(type);
			int totalTime=0;
			for (String string : callArray) {
				int value=Integer.parseInt(string);
				totalTime=totalTime+value;
				try
				{
					if(value>threshold)
					{
						throw new SeniorEscalationException();
						
					}
					else
					{
						jr.setResolved(jr.getResolved()+1);
						callRes.setResolved(callRes.getResolved()+1);
					}
				}
				catch(SeniorEscalationException e)
				{
					jr.setEscalated(jr.getEscalated()+1);
					callRes.setUnresolved(callRes.getUnresolved()+1);
				}
				
			
			}
			jr.setTimeTakenInMinutes(totalTime);
			callRes.setTotalTimeTakenInMinutes(callRes.getTotalTimeTakenInMinutes()+totalTime);
			obj=jr;
		}
		else
		{
			Manager jr=new Manager();
			jr.setCallsAttended(numberOfCalls);
			jr.setId(type);
			int totalTime=0;
			for (String string : callArray) {
				int value=Integer.parseInt(string);
				totalTime=totalTime+value;
				
				try
				{
					if(value>threshold)
					{
						throw new ManagerEscaltionException();
						
					}
					else
					{
						jr.setResolved(jr.getResolved()+1);
						callRes.setResolved(callRes.getResolved()+1);
					}
				}
				catch(ManagerEscaltionException e)
				{
					jr.setEscalated((jr.getEscalated()+1));
					callRes.setUnresolved(callRes.getUnresolved()+1);
				}
				
			}
			jr.setTimeTakenInMinutes(totalTime);
			callRes.setTotalTimeTakenInMinutes(callRes.getTotalTimeTakenInMinutes()+totalTime);
			obj=jr;
		}
			
		return obj;
	}
}
