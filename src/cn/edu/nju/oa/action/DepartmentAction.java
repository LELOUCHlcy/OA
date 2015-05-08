package cn.edu.nju.oa.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.nju.oa.domain.Department;
import cn.edu.nju.oa.service.DepartmentService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class DepartmentAction extends ActionSupport implements
		ModelDriven<Department> {

	@Resource
	private DepartmentService departmentService;
	private Department model= new Department();
	@Override
	public Department getModel() {
		return model;
	}

	public String list() {
		List<Department> departmentList = departmentService.getAll();
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	public String delete() {
		departmentService.delete(model.getId());
		return "toList";
	}

	public String addUI() {
		return "saveUI";
	}

	public String add() {
		departmentService.save(model);
		return "toList";
	}

	public String editUI() {
		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		return "saveUI";
	}

	public String edit() {
		Department department = departmentService.getById(model.getId());
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		departmentService.update(department);
		return "toList";
	}

}
