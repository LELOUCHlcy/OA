package cn.edu.nju.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.edu.nju.oa.domain.User;
import cn.edu.nju.oa.service.DepartmentService;
import cn.edu.nju.oa.service.ForumService;
import cn.edu.nju.oa.service.PrivilegeService;
import cn.edu.nju.oa.service.ReplyService;
import cn.edu.nju.oa.service.RoleService;
import cn.edu.nju.oa.service.TopicService;
import cn.edu.nju.oa.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	@Resource
	protected RoleService roleService;

	@Resource
	protected DepartmentService departmentService;

	@Resource
	protected UserService userService;

	@Resource
	protected PrivilegeService privilegeService;

	@Resource
	protected ForumService forumService;

	@Resource
	protected TopicService topicService;

	@Resource
	protected ReplyService replyService;

	protected T model;

	public BaseAction() {
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

	protected User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}
}
