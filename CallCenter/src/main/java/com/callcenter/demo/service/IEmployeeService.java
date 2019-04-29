package com.callcenter.demo.service;

import java.util.concurrent.CompletableFuture;
import com.callcenter.demo.domain.Call;

public interface IEmployeeService {

	/**
	 * Método que permite procesar las llamadas
	 * @param call
	 * @return
	 */
	public CompletableFuture<String> processCall(Call call);
}
