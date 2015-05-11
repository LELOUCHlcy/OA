package cn.edu.nju.oa.domain;

import java.util.HashSet;
import java.util.Set;

public class User {

	private Long id;
	private Department department;
	private String name;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private Set<Role> roles = new HashSet<Role>();
	private String loginName;
	private String gender;
	private String phoneNumber;
	private String email;
	private String description;

	public boolean hasPrivilegeByName(String privilegeName) {
		if (isAdmin()) {
			return true;
		}
		for (Role role : roles) {
			for (Privilege privilege : role.getPrivileges()) {
				if (privilege.getName().equals(privilegeName)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isAdmin() {
		return "admin".equals(loginName);
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
