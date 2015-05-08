package cn.edu.nju.oa.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.nju.oa.domain.Role;
import cn.edu.nju.oa.service.RoleService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class RoleAction extends ActionSupport implements ModelDriven<Role> {

	@Resource
	private RoleService roleService;

	private Role model = new Role();

	@Override
	public Role getModel() {
		return model;
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

}
