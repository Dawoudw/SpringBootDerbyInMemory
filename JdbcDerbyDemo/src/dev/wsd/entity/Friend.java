package dev.wsd.entity;

public class Friend {
	int id;
	String name;

	public Friend() {
		// TODO Auto-generated constructor stub
	}

	public Friend(int id, String name) {

		setId(id);
		setName(name);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Friend [id=" + id + ", name=" + name + "]";
	}

}
