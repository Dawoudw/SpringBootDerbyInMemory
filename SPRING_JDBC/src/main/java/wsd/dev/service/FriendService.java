package wsd.dev.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wsd.dev.entity.Friend;
import wsd.dev.repository.IFriendsRepo;

@Service
public class FriendService {
	@Autowired
	IFriendsRepo repo;

	public List<Friend> getAllFriends() {
		List<Friend> lst = new ArrayList<Friend>();
//		lst.add(new Friends(1,"Wael"));
//		lst.add(new Friends(2,"Ahmed"));
//		lst.add(new Friends(3,"Mohamed"));
		// TODO Auto-generated method stub
		lst = repo.findAll();
		System.out.println(lst);
		return lst;//
	}

	public Friend createFriend(Friend obj) {
		System.out.println("createFriend\n"+obj);
		return this.repo.save(obj);
	}
}
