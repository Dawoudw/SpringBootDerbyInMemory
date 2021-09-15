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
			JdbcImpl jdbc = new JdbcImpl();
//			  System.out.println(jdbc.addFriend(new Friend(8,"Mohamed DAWOUD"))); 
//			  System.out.println(jdbc.addFriend(new Friend(9,"Mohamed DAWOUD"))); 
//			  System.out.println(jdbc.addFriend(new Friend(10,"Mohamed DAWOUD"))); 
//			  System.out.println(jdbc.addFriend(new Friend(11,"Mohamed DAWOUD"))); 
//			  System.out.println(jdbc.addFriend(new Friend(12, "DAWOUD")));

			// System.out.println(jdbc.getFriend(3).toString());
			// System.out.println(jdbc.getAllFriends());
			
			/* Batch Update */
			List<Friend> friends = jdbc.getAllFriends();
			friends.get(6).setName("Mohamed Ahmed");
			friends.get(7).setName("Dawoud");
			jdbc.batchUpdateFriends(friends);
		
			/* Batch Delete */
//			List<Friend> delLst = jdbc.getAllFriends().subList(7, 11);
//			jdbc.batchDeleteFriends(delLst);
			System.out.println(jdbc.getAllFriends());

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
