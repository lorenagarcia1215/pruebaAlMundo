package com.callcenter.demo.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.callcenter.demo.constants.Constants;
import com.callcenter.demo.domain.Call;
import com.callcenter.demo.domain.Employee;
import com.callcenter.demo.repository.IEmployeeRepository;
import com.callcenter.demo.service.IEmployeeService;
import com.callcenter.demo.util.Util;


/**
 * Servicio que permite manejar las operaciones relacionadas con el empleado
 * @author Lorena Garcia Arias
 *
 */
@Service
public class EmployeeService implements IEmployeeService{


	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	@Resource(name = "messages")
	private Properties props;

	@Autowired
	private IEmployeeRepository employeeRepository;


	@Async("dispatcherTaskExecutor")
	public CompletableFuture<String> processCall(Call call) {

		Employee  employeeSelected = null;

		try {
			// se obtiene el empleado a cual se le va asignar la llamada
			employeeSelected =assignCallToEmployee(call);

		} catch (NoSuchElementException e) {
			LOGGER.error(props.getProperty(Constants.EMPLOYEES_NOT_AVAILABLE));
			try {
				Thread.sleep(10000);
				processCall(call);
			} catch (InterruptedException e1) {
				LOGGER.error(props.getProperty(Constants.ERROR_INTER_THREAD));
			}
		}

		// se procesa la llamada
		attendCall(employeeSelected, call);

		//se actualiza el estado del empleado a disponible 
		updateEmployee(employeeSelected, true);

		return CompletableFuture.completedFuture(props.getProperty(Constants.CALL_ATTEND));
	}

	/**
	 * Método que procesa la llamada
	 * @param employeeSelected
	 * @param call
	 */
	public void attendCall(Employee employeeSelected, Call call) {
		if(employeeSelected!=null) {
			employeeSelected.attendCall(call);
		}
	}

	/**
	 * Método que permite obtener el empleado a cual se le va asignar la llamada
	 * @param call
	 * @return Empleado
	 */
	public synchronized Employee assignCallToEmployee(Call call) {

		LOGGER.info("******************************************* ");
		LOGGER.info(Util.getCurrentDate());

		Employee employeeSelected = null;

		List<Employee>employeeList = findEmployees();

		employeeSelected= employeeList.stream()
				.sorted(Comparator.comparing(Employee::getType))
				.filter(employee->employee.isAvailable()).findFirst().get();

		//se actualiza el estado del empleado a ocupado (N) 
		updateEmployee(employeeSelected, false);

		LOGGER.info("Empleado seleccionado...."+employeeSelected.getFirstName());
		LOGGER.info("Duracion llamada... "+call.getDuration()+" segundo(s)");

		return employeeSelected;

	}


	/**
	 * Método que permite consultar todos los empleados
	 * @return lista de empleados
	 */
	public synchronized List<Employee> findEmployees(){
		List<Employee> employeeList = employeeRepository.findEmployees();
		return employeeList;
	}

	/**
	 * Método que permite actualizar el estado del empleado
	 * @param employee
	 * @param isAvailable
	 * @return
	 */
	public  void updateEmployee(Employee employee, boolean isAvailable) {
		if(employee!=null) {
			employeeRepository.updateEmployee(employee, isAvailable);
		}
	}
}
