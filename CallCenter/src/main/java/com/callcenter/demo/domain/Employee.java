package com.callcenter.demo.domain;

import com.callcenter.demo.enums.EmployeeType;

/**
 * Clase de dominio para la atención de llamadas 
 * @author Lorena Garcia Arias
 *
 */

public class Employee {

	private Long id;
	private String firstName;
	private boolean available;
	private EmployeeType type;


	/**
	 * @param available
	 * @param type
	 */
	public Employee(boolean available, EmployeeType type) {
		super();
		this.available = available;
		this.type = type;
	}

	public Employee() {}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

	/**
	 * @return the type
	 */
	public EmployeeType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(EmployeeType type) {
		this.type = type;
	}

	public void attendCall(Call call) {
		try {

			Thread.sleep(call.getDuration()*1000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", available=" + available + ", type=" + type + "]";
	}
}
