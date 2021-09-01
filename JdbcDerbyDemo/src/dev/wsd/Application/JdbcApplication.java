package dev.wsd.Application;

import java.util.List;
import dev.wsd.dao.JdbcImpl;
import dev.wsd.entity.Friend;

public class JdbcApplication {

	public JdbcApplication() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String... args) {

		try {

			/*
			  System.out.println(new JdbcImpl().addFriend(new Friend(11,"Mohamed DAWOUD"))); 
			  System.out.println(new JdbcImpl().addFriend(new Friend(12, "  DAWOUD")));
			 */
			// System.out.println(new JdbcImpl().getFriend(3).toString());
			// System.out.println(new JdbcImpl().getAllFriends());

			JdbcImpl jdbc = new JdbcImpl();
			/*
			 * List<Friend> friends = jdbc.getAllFriends(); friends.get(0).setName("Omar");
			 * friends.get(1).setName("Ali"); friends.get(2).setName("Belal");
			 * friends.get(3).setName("Sohaib"); jdbc.batchUpdateFriends(friends);
			 * System.out.println(jdbc.getAllFriends());
			 */

			List<Friend> delLst = jdbc.getAllFriends().subList(7, 11);
			jdbc.batchDeleteFriends(delLst);
			System.out.println(jdbc.getAllFriends());

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
