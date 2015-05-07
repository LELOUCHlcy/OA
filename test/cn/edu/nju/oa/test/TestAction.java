package cn.edu.nju.oa.test;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
public class TestAction extends ActionSupport {

	public String execute() {
		System.out.println("TestAction.execute()");
		return "success";
	}
}
