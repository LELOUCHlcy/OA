package cn.edu.nju.oa.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.edu.nju.oa.base.BaseAction;
import cn.edu.nju.oa.domain.Department;
import cn.edu.nju.oa.domain.Role;
import cn.edu.nju.oa.domain.User;
import cn.edu.nju.oa.util.DepartmentUtils;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private Long departmentId;

	private Long[] roleIds;

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	/** 列表 */
	public String list() throws Exception {
		List<User> userList = userService.getAll();
		ActionContext.getContext().put("userList", userList);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 准备部门的树状结构
		List<Department> topList = departmentService.getTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备岗位列表
		List<Role> roleList = roleService.getAll();
		ActionContext.getContext().put("roleList", roleList);
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 属性封装到对象中
		model.setDepartment(departmentService.getById(departmentId));
		List<Role> roles = roleService.getByIds(roleIds);

		model.setRoles(new HashSet<Role>(roles));
		// 设置密码
		String md5Digest = DigestUtils.md5Hex("1234");
		model.setPassword(md5Digest);
		// 存储到数据库
		userService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备数据
		List<Department> topList = departmentService.getTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		List<Role> roleList = roleService.getAll();
		ActionContext.getContext().put("roleList", roleList);

		// 准备回显的数据
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		if (departmentId != null) {
			departmentId = user.getDepartment().getId();
		}
		if (roleIds != null) {
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}

		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 获取数据库的对象
		User user = userService.getById(model.getId());
		// 更新数据
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		// >> 设置所属部门
		user.setDepartment(departmentService.getById(departmentId));
		// >> 设置关联的岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));

		// 更新到数据库
		userService.update(user);

		return "toList";
	}

	public String initPassword() throws Exception {
		return "toList";
	}
}
