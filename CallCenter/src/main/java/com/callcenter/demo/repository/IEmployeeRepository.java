package com.callcenter.demo.repository;

import java.util.List;

import com.callcenter.demo.domain.Employee;

public interface IEmployeeRepository{
	
	/**
	 * Método que permite consultar todos los empleados
	 * @return lista de empleados
	 */
	public List<Employee> findEmployees();	
	
	/**
	 * Método que permite actualizar el estado del empleado
	 * @param employee
	 * @param isAvailable
	 */
	public void updateEmployee(Employee employee, boolean isAvailable) ;

}
