package wsd.dev.entity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "FRIENDS")
@Entity
public class Friend {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "fName")
	String fname = "";

	public Friend() {

	}

	public Friend(int id, String fname) {

		this.id = id;
		this.fname = fname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fname, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Friend other = (Friend) obj;
		return Objects.equals(fname, other.fname) && id == other.id;
	}

	@Override
	public String toString() {
		return "Friend [id=" + id + ", fname=" + fname + "]";
	}
}
