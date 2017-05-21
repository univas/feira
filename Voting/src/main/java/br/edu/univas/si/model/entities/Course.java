package br.edu.univas.si.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Course")

public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "VOTE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_gen")
	@SequenceGenerator(name = "vote_gen", sequenceName = "VOTE_SEQ", allocationSize = 1)
	private Long id;

	@Column(length = 500)
	private String courseName;

	@ManyToOne
	@JoinColumn(name = "email", nullable = false, updatable = false)
	private UserInfo user;

	public Course() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

}