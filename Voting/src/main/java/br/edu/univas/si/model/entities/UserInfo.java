package br.edu.univas.si.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "UserInfo")

public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false)
	private String email;

	@Column
	private int age;
	
	@Column
	private boolean permission;

	@Temporal(TemporalType.DATE)
	private Date registerDate;

	@OneToMany(mappedBy = "user")
	private List<Course> courses = new ArrayList<Course>();

	public UserInfo() {
	}

	public Course addCourse(Course course) {
		courses.add(course);
		course.setUser(this);

		return course;
	}

	public Course removeCourse(Course course) {
		courses.remove(course);
		course.setUser(null);

		return course;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean getPermission() {
		return permission;
	}

	public void setPermission(boolean permission) {
		this.permission = permission;
	}
	

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

}