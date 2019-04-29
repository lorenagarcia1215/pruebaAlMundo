package com.callcenter.demo.repository.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.callcenter.demo.constants.Constants;
import com.callcenter.demo.domain.Employee;
import com.callcenter.demo.repository.IEmployeeRepository;
import com.callcenter.demo.util.Util;

@Repository
public class EmployeeRepository implements IEmployeeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Resource(name = "database")
	private Properties props;

	@Override
	public List<Employee> findEmployees(){

		String query = props.getProperty(Constants.FIND_ALL_EMPLOYEES);

		return jdbcTemplate.query(query, (resultSet, i) -> {
			return toEmployee(resultSet);
		});
	}

	private Employee toEmployee(ResultSet resultSet) throws SQLException {
		Employee employee = new Employee();
		employee.setId(resultSet.getLong("ID"));
		employee.setFirstName(resultSet.getString("FIRST_NAME"));
		employee.setAvailable(resultSet.getString("AVAILABLE").equals("S")?true:false);
		employee.setType(Util.validateType(resultSet.getString("TYPE")));
		return employee;
	}

	public void updateEmployee(Employee employee, boolean isAvailable) {
		String query = props.getProperty(Constants.UPD_STATUS_EMPLOYEE);

		jdbcTemplate.update(query,
				new Object[] { isAvailable==true? "S":"N" , employee.getId()});
	}
}
