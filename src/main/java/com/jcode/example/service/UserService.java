package com.jcode.example.service;

import org.springframework.stereotype.Service;

import com.jcode.example.model.User;
import com.jcode.mybatis.MybatisDao;

/**
 * @Desc
 *
 * @Author dreajay
 * @DateTime 2015年12月19日 下午10:58:20
 * 
 */
@Service
public class UserService {

	MybatisDao dao = new MybatisDao(User.class.getCanonicalName());

	public void getUser() {
		System.out.println(dao.select(4));
	}
	
	public void insert3() {
		User user = new User();
		user.setUserName("123name");
		user.setSex("1");
		int result = dao.insert(user);
		System.out.println("resutl:"+result);
		
		if(true) {
			throw new RuntimeException("测试异常");
		}
	}
	
	public void insert() throws Exception {
		User user = new User();
		user.setUserName("123name");
		user.setSex("1");
		int result = dao.insert(user);
		System.out.println("resutl:"+result);
		
		if(true) {
			throw new RuntimeException("测试异常");
		}
	}
	
}
