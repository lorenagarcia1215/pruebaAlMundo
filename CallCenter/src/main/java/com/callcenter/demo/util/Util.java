package com.callcenter.demo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.callcenter.demo.constants.Constants;
import com.callcenter.demo.enums.EmployeeType;

/**
 * Clase Utilitaria
 * @author Lorena Garcia Arias
 *
 */
public class Util {

	/**
	 * Método que permite validar el tipo de empleado
	 * @param value
	 * @return
	 */
	public static EmployeeType validateType(String value) {

		EmployeeType[] types =EmployeeType.values();
		for (EmployeeType employeeType : types) {
			if(employeeType.name().equals(value)) {
				return employeeType;
			}
		}
		return null;
	}

	/**
	 * Método que permite obtener la fecha actual en un formato especifico
	 * @return fecha actual en formato yyyy/MM/dd HH:mm:ss
	 */
	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT);
		Calendar cal = Calendar.getInstance();
		String currentDate = dateFormat.format(cal.getTime());
		return currentDate;

	}
}
