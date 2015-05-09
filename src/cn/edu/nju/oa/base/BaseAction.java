package cn.edu.nju.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.edu.nju.oa.service.DepartmentService;
import cn.edu.nju.oa.service.RoleService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	@Resource
	protected RoleService roleService;

	@Resource
	protected DepartmentService departmentService;

	protected T model;

	public BaseAction()  {
		Class<T> clazz = null;
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
		try {
			model = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T getModel() {
		return model;
	}

}
