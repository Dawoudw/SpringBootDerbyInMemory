package dev.wsd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dev.wsd.entity.Friend;

public class JdbcImpl {
	Connection conn = null;
	final String driver = "org.apache.derby.jdbc.ClientDriver";

	public JdbcImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection("jdbc:derby://localhost:1527/wsdDB");

	}

	private void closeConnections(Connection con, PreparedStatement p, ResultSet r) {
		try {
			if (con != null)
				con.close();
			if (p != null)
				p.close();
			if (r != null)
				r.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Friend getFriend(int id) {

		Friend f = new Friend();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM FRIENDS WHERE id = ?");

			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				f.setId(rs.getInt("ID"));
				f.setName(rs.getString("NAME"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnections(conn, ps, rs);
		}

		return f;
	}

}
