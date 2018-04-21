package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.example.demo.request.CallRequest;
import com.example.demo.service.CallCalculationService;
import com.example.demo.vo.CallResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CallCalculationService.class})

public class CallCalculationServiceTest {

	@InjectMocks
	private CallCalculationService serviceTest;

	private CallRequest callRequest;
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
	}
	@Before
	public void init() throws Exception
	{
		callRequest=new CallRequest();
		String[] je={ "5,6,7", "45,5" };
		String[] se={"5,6,7", "45,5" };
		String mgr= "20,12,25,13,20,3,3,3,9,2,7,1,7,11,10";
		callRequest.setJe(je);
		callRequest.setSe(se);
		callRequest.setMgr(mgr);
		callRequest.setNumber_of_calls("23");
	}
	@Test
	public void testCallResponse()
	{
		CallResponse res=serviceTest.calculateRequest(callRequest);
		
		junit.framework.Assert.assertEquals(String.valueOf(res.getNumber_of_calls()), callRequest.getNumber_of_calls());
	}
}
