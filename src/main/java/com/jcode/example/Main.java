package com.jcode.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jcode.example.model.User;
import com.jcode.example.service.UserService;
import com.jcode.mybatis.MybatisDao;

/**
 * @Desc
 *
 * @Author dreajay
 * @DateTime 2015年12月19日 下午10:28:27
 * 
 */
public class Main {
	static ApplicationContext context;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("spring.xml");
		testDao();
	}

	public static void testDao() {

		MybatisDao dao = new MybatisDao(User.class.getCanonicalName());

		User user = dao.select(4);
		System.out.println(user);

		user = dao.select("select2", 5);
		System.out.println(user);

	}

	public static void testTransaction() {
		UserService userService = context.getBean(UserService.class);
		userService.getUser();

	}

}
