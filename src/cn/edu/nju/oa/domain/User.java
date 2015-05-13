package cn.edu.nju.oa.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

public class User implements Serializable {

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

	// 根据权限名来判断有无权限
	public boolean hasPrivilegeByName(String privilegeName) {
		if (isAdmin()) {
			return true;
		}
		for (Role role : roles) {
			for (Privilege privilege : role.getPrivileges()) {
				if (privilegeName.equals(privilege.getName())) {
					return true;
				}
			}
		}
		return false;
	}

	// 根据URL来判断有无权限
	public boolean hasPrivilegeByUrl(String privilegeUrl) {

		if (privilegeUrl.contains("?")) {
			int pos = privilegeUrl.indexOf("?");
			privilegeUrl = privilegeUrl.substring(0, pos);
		}

		if (privilegeUrl.endsWith("UI")) {
			privilegeUrl = privilegeUrl.substring(0, privilegeUrl.length() - 2);
		}
		if (isAdmin()) {
			return true;
		}

		Collection<String> allPrivilegeUrls = (Collection<String>) ActionContext
				.getContext().getApplication().get("allPrivilegeUrls");
		if (!allPrivilegeUrls.contains(privilegeUrl)) {
			return true;
		} else {
			for (Role role : roles) {
				for (Privilege privilege : role.getPrivileges()) {
					if (privilegeUrl.equals(privilege.getUrl())) {
						return true;
					}
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
