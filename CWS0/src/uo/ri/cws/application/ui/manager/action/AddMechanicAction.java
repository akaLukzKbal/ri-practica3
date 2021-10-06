package uo.ri.cws.application.ui.manager.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.cws.application.BusinessException;

public class AddMechanicAction implements Action {

	private static String SQL = "insert into TMechanics(id, dni, name, surname) values (?, ?, ?, ?)";

	@Override
	public void execute() throws BusinessException {
		
		// Get info
		String dni = Console.readString("Dni"); 
		String name = Console.readString("Name"); 
		String surname = Console.readString("Surname");
		
		// Process
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setString(1, UUID.randomUUID().toString());
			pst.setString(2, dni);
			pst.setString(3, name);
			pst.setString(4, surname);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		
		// Print result
		Console.println("Mechanic added");
	}

}
