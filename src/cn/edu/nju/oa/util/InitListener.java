package cn.edu.nju.oa.util;

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
		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		PrivilegeService privilegeService = (PrivilegeService) ac
				.getBean("privilegeServiceImpl");
		List<Privilege> topPrivilegeList = privilegeService
				.getTopPrivilegeList();
		sce.getServletContext().setAttribute("topPrivilegeList",
				topPrivilegeList);
		System.out.println("已准备数据");
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

}
