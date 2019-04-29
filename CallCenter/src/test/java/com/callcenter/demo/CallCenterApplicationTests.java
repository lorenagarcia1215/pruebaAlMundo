package com.callcenter.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.callcenter.demo.config.H2DatabaseConfig;
import com.callcenter.demo.constants.Constants;
import com.callcenter.demo.dispatcher.Dispatcher;
import com.callcenter.demo.domain.Call;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		CallCenterApplication.class, 
		H2DatabaseConfig.class})
@ActiveProfiles("test")
public class CallCenterApplicationTests {

	@Autowired
	private Dispatcher dispatcher;

	@Resource(name = "messages")
	private Properties props;

	/**
	 * 
	 * prueba unitaria con 10 llamadas concurrentes, para la prueba se tienen
	 * 3 operadores, 1 supervisor y 1 director
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
    @Test
	public void processTenCalls() throws InterruptedException, ExecutionException {

		List<Call> incomingCalls = new ArrayList<>();
		List<CompletableFuture<String>>list= new ArrayList<>();
		Call call1 = new Call(3);
		Call call2 = new Call(1);
		Call call3 = new Call(5);
		Call call4 = new Call(2);
		Call call5 = new Call(7);
		Call call6 = new Call(4);
		Call call7 = new Call(9);
		Call call8 = new Call(8);
		Call call9 = new Call(6);
		Call call10 = new Call(10);

		incomingCalls.add(call1);
		incomingCalls.add(call2);
		incomingCalls.add(call3);
		incomingCalls.add(call4);
		incomingCalls.add(call5);
		incomingCalls.add(call6);
		incomingCalls.add(call7);
		incomingCalls.add(call8);
		incomingCalls.add(call9);
		incomingCalls.add(call10);

		for (Call call : incomingCalls) {
			CompletableFuture<String> result =dispatcher.dispatchCall(call);
			list.add(result);
		}

		for (CompletableFuture<String> future : list) {
			String result =future.get();
			assertEquals(props.getProperty(Constants.CALL_ATTEND),result);
		}
	}

	/**
	 * prueba unitaria que permite verificar el comportamiento del sistema cuando 
	 * entran mas de 10 llamadas
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
    @Test
	public void processMoreThanTenCalls() throws InterruptedException, ExecutionException {

		List<Call> incomingCalls = new ArrayList<>();
		List<CompletableFuture<String>>list= new ArrayList<>();
		Call call1 = new Call(3);
		Call call2 = new Call(1);
		Call call3 = new Call(5);
		Call call4 = new Call(2);
		Call call5 = new Call(7);
		Call call6 = new Call(4);
		Call call7 = new Call(9);
		Call call8 = new Call(8);
		Call call9 = new Call(6);
		Call call10 = new Call(10);
		Call call11 = new Call(11);
		Call call12 = new Call(12);

		incomingCalls.add(call1);
		incomingCalls.add(call2);
		incomingCalls.add(call3);
		incomingCalls.add(call4);
		incomingCalls.add(call5);
		incomingCalls.add(call6);
		incomingCalls.add(call7);
		incomingCalls.add(call8);
		incomingCalls.add(call9);
		incomingCalls.add(call10);
		incomingCalls.add(call11);
		incomingCalls.add(call12);

		for (Call call : incomingCalls) {
			CompletableFuture<String> result =dispatcher.dispatchCall(call);
			list.add(result);
		}

		for (CompletableFuture<String> future : list) {
			String result =future.get();
			assertEquals(props.getProperty(Constants.CALL_ATTEND),result);
		}
	}
}
