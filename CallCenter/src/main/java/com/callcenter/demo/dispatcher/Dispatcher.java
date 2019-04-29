package com.callcenter.demo.dispatcher;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.callcenter.demo.domain.Call;
import com.callcenter.demo.service.IEmployeeService;

@Component
public class Dispatcher {

	@Autowired
	private IEmployeeService employeeService;

	
	public CompletableFuture<String> dispatchCall(Call call) {
		
		 return employeeService.processCall(call);
	}
	
}
