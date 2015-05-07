package cn.edu.nju.oa.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	private ApplicationContext ac = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	
	//测试Spring
	@Test
	public void testBean(){
		TestAction ta = (TestAction) ac.getBean("testAction");
		System.out.println(ta);
	}
	
	//测试SessionFactory
	@Test
	public void testSessionFactory() {
		SessionFactory sf = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sf);
	}
	
	//测试事务
	@Test
	public void testTxManager() {
		TestService ts = (TestService) ac.getBean("testService");
		ts.save();
	
	}
}
