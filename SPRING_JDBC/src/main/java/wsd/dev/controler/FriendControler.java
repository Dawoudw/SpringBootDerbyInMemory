package wsd.dev.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wsd.dev.entity.Friend;
import wsd.dev.service.FriendService;

@RestController
@RequestMapping(path = "/friends")

@CrossOrigin("*")
public class FriendControler {
	@Autowired
	FriendService srv;

	@GetMapping
	public List<Friend> getFriends() {
		List<Friend> lst = this.srv.getAllFriends();
		System.out.println("Wael" + lst);
		return lst;
	}

	@PostMapping
	public Friend AddFriend(@RequestBody Friend obj) {
		return this.srv.createFriend(obj);
	}
}
