package dev.wsd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.wsd.entity.Friend;

public class JdbcImpl {
	private Connection conn = null;
	private final String driver = "org.apache.derby.jdbc.ClientDriver";

	public JdbcImpl() {
		// TODO Auto-generated constructor stub

	}

	private Connection getConnection() {
		/*
		 * https://docs.oracle.com/javase/tutorial/jdbc/basics/transactions.html
		 * Usually, you do not need to do anything about the transaction isolation
		 * level; you can just use the default one for your DBMS. The default
		 * transaction isolation level depends on your DBMS. For example, for Java DB,
		 * it is TRANSACTION_READ_COMMITTED. JDBC allows you to find out what
		 * transaction isolation level your DBMS is set to (using the Connection method
		 * getTransactionIsolation) and also allows you to set it to another level
		 * (using the Connection method setTransactionIsolation).
		 * 
		 * Note: A JDBC driver might not support all transaction isolation levels. If a
		 * driver does not support the isolation level specified in an invocation of
		 * setTransactionIsolation, the driver can substitute a higher, more restrictive
		 * transaction isolation level. If a driver cannot substitute a higher
		 * transaction level, it throws a SQLException. Use the method
		 * DatabaseMetaData.supportsTransactionIsolationLevel to determine whether or
		 * not the driver supports a given level.
		 */
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection("jdbc:derby://localhost:1527/wsdDB");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	private void closeConnections(Connection con) {
		try {
			if (con != null)
				con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void closeResultSets(ResultSet r) {
		try {

			if (r != null)
				r.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Friend getFriend(int id) {

		Friend f = new Friend();

		ResultSet rs = null;
		try (PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM FRIENDS WHERE id = ?");) {

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
			closeConnections(conn);
			closeResultSets(rs);
		}

		return f;
	}

	public List<Friend> getAllFriends() {

		ResultSet rs = null;
		List<Friend> lst = new ArrayList<Friend>();
		try (PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM FRIENDS");) {
			rs = ps.executeQuery();
			while (rs.next()) {
				Friend f = new Friend();
				f.setId(rs.getInt("ID"));
				f.setName(rs.getString("NAME"));
				lst.add(f);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn);
		}

		return lst;
	}

	public int addFriend(Friend f) {

		int i = -1;
		try (PreparedStatement ps = getConnection().prepareStatement("INSERT INTO FRIENDS (ID,NAME) VALUES (?,?)");) {

			ps.setInt(1, f.getId());
			ps.setString(2, f.getName());
			i = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnections(conn);
		}
		return i;

	}

	public int[] batchUpdateFriends(List<Friend> flst) {

		this.getConnection();
		int[] ar = {};
		List<Friend> lst = new ArrayList<Friend>();
		try (PreparedStatement ps = conn.prepareStatement("UPDATE FRIENDS SET  NAME=?  WHERE ID=?");) {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			for (Friend f : flst) {
				ps.setString(1, f.getName());
				ps.setInt(2, f.getId());
				ps.addBatch();
			}

			ar = ps.executeBatch();
			conn.commit();

		} catch (SQLException e) {
			if (conn != null)
				try {
					System.err.print("The transaction of batch update Friends is being rolled back");
					conn.rollback();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			e.printStackTrace();
		} finally {
			closeConnections(conn);
		}

		return ar;
	}

	public int[] batchDeleteFriends(List<Friend> flst) {

		this.getConnection();
		int[] ar = {};
		List<Friend> lst = new ArrayList<Friend>();
		try (PreparedStatement ps = conn.prepareStatement("DELETE FROM FRIENDS WHERE ID=?");) {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			for (Friend f : flst) {
				ps.setInt(1, f.getId());
				ps.addBatch();
			}
			ar = ps.executeBatch();
			conn.commit();

		} catch (SQLException e) {
			if (conn != null)
				try {
					System.err.print("The transaction of batch delete Friends is being rolled back");
					conn.rollback();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			e.printStackTrace();
		} finally {
			closeConnections(conn);
		}

		return ar;
	}
}
