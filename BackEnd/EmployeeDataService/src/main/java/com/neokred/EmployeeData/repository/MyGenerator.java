package com.neokred.EmployeeData.repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class MyGenerator implements IdentifierGenerator {

	static final String COUNT_EMPLOYEE_ID_QUERY = "select count(employee_id) as Id from Employee";
	static final String EMPLOYEE_ID_PREFIX = "NK";

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		Connection connection = session.connection();

		try {
			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(COUNT_EMPLOYEE_ID_QUERY);

			if (rs.next()) {
				// start employee IDs with 100
				int id = rs.getInt(1) + 100;
				String generatedId = EMPLOYEE_ID_PREFIX + new Integer(id).toString();
				return generatedId;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}