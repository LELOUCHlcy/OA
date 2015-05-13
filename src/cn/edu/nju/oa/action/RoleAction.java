package cn.edu.nju.oa.action;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.nju.oa.base.BaseAction;
import cn.edu.nju.oa.domain.Privilege;
import cn.edu.nju.oa.domain.Role;
import cn.edu.nju.oa.service.RoleService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private Long[] privilegeIds;

	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	/**
	 * 列表功能
	 * 
	 * @return
	 */
	public String list() {
		List<Role> roleList = roleService.getAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}

	/**
	 * 删除功能
	 * 
	 * @return
	 */
	public String delete() {
		roleService.delete(model.getId());
		return "toList";
	}

	/**
	 * 添加功能
	 * 
	 * @return
	 */
	public String add() {
		// // 封装到对象中
		// Role role = new Role();
		// role.setName(model.getName());
		// role.setDescription(model.getDescription());
		// // 保存到数据库中
		roleService.save(model);
		return "toList";
	}

	/**
	 * 添加页面
	 * 
	 * @return
	 */
	public String addUI() {

		return "saveUI";
	}

	/**
	 * 修改功能
	 * 
	 * @return
	 */
	public String edit() {
		Role role = roleService.getById(model.getId());
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		roleService.update(role);
		return "toList";
	}

	/**
	 * 修改页面
	 * 
	 * @return
	 */
	public String editUI() {
		// 准备会显得数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		return "saveUI";
	}

	/**
	 * 设置权限
	 * 
	 * @return
	 */
	public String setPrivilege() {
		Role role = roleService.getById(model.getId());
	
		role.setPrivileges(new HashSet<Privilege>(privilegeService
				.getByIds(privilegeIds)));
		roleService.update(role);
		return "toList";
	}

	/**
	 * 设置权限页面
	 * 
	 * @return
	 */
	public String setPrivilegeUI() {
		// 准备会显得数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		int index = 0;
		if (role.getPrivileges() != null) {
			privilegeIds = new Long[role.getPrivileges().size()];
			for (Privilege privilege : role.getPrivileges()) {
				privilegeIds[index++] = privilege.getId();
			}
		}

		List<Privilege> privilegeList = privilegeService.getAll();
		ActionContext.getContext().put("privilegeList", privilegeList);
		return "setPrivilegeUI";
	}
}
