package cn.edu.nju.oa.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.nju.oa.base.BaseAction;
import cn.edu.nju.oa.domain.Department;
import cn.edu.nju.oa.service.DepartmentService;
import cn.edu.nju.oa.util.DepartmentUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

	@Resource
	private DepartmentService departmentService;
	private Department model = new Department();

	private Long parentId;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public Department getModel() {
		return model;
	}

	public String list() {
		List<Department> departmentList = null;
		if (parentId == null) {
			departmentList = departmentService.getTopList();
		} else {
			departmentList = departmentService.getChildren(parentId);
		}
		ActionContext.getContext().put("departmentList", departmentList);
		Department parent = departmentService.getById(parentId);
		ActionContext.getContext().put("parent", parent);
		return "list";
	}

	public String delete() {
		departmentService.delete(model.getId());
		return "toList";
	}

	public String addUI() {
		List<Department> topList = departmentService.getTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		return "saveUI";
	}

	public String add() {
		model.setParent(departmentService.getById(parentId));
		departmentService.save(model);
		return "toList";
	}

	public String editUI() {

		List<Department> departmentList = departmentService.getAll();
		ActionContext.getContext().put("departmentList", departmentList);
		// 准备回显的数据
		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		if (department.getParent() != null) {
			parentId = department.getParent().getId();
		}
		return "saveUI";
	}

	public String edit() {
		Department department = departmentService.getById(model.getId());
		department.setParent(departmentService.getById(parentId));
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		departmentService.update(department);
		return "toList";
	}

}
