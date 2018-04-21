package com.example.demo;

import junit.framework.Assert;

import org.mockito.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.CallCenterController;
import com.example.demo.request.CallRequest;
import com.example.demo.service.CallCalculationService;
import com.example.demo.vo.CallResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CallCenterController.class,CallCalculationService.class})
public class CallCenterApplicationTests {

	@InjectMocks
	private CallCenterController callController;
	@Mock
	private CallCalculationService call;
	ResponseEntity<? extends Object> res;
	CallRequest callRequest=null;
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
	public void testManageCall() {
		CallResponse callresponse=new CallResponse();
		PowerMockito.when(call.calculateRequest(Matchers.any(CallRequest.class))).thenReturn(callresponse);
		res=callController.manageCall(callRequest);
		Assert.assertEquals(200, res.getStatusCodeValue());
	}

}
