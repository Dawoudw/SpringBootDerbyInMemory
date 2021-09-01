package dev.wsd.Application;

import java.sql.SQLException;

import dev.wsd.dao.JdbcImpl;

public class JdbcApplication {

	public JdbcApplication() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String... args) {

		try {
			System.out.println(new JdbcImpl().getFriend(1).toString());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
