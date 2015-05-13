package cn.edu.nju.oa.util;

import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.edu.nju.oa.domain.Privilege;
import cn.edu.nju.oa.service.PrivilegeService;

public class InitListener implements ServletContextListener {

	public InitListener() {
	}

	public void contextInitialized(ServletContextEvent sce) {
		//获取容器对象，取得其中的privilegeService实现类对象
		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		PrivilegeService privilegeService = (PrivilegeService) ac
				.getBean("privilegeServiceImpl");

		//准备顶级的权限菜单
		List<Privilege> topPrivilegeList = privilegeService
				.getTopPrivilegeList();
		sce.getServletContext().setAttribute("topPrivilegeList",
				topPrivilegeList);
		System.out.println("已准备数据");

		// 准备所有的权限实体
		Collection<String> allPrivilegeUrls = privilegeService
				.getAllPrivilegeUrls();
		sce.getServletContext().setAttribute("allPrivilegeUrls",
				allPrivilegeUrls);
		System.out.println("------------> 已准备数据allPrivilegeUrls <------------");
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

}
