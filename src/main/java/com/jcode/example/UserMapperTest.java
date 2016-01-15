package com.jcode.example;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jcode.example.mapper.UserMapper;
import com.jcode.example.model.User;
import com.jcode.mybatis.SqlSessionFactoryManager;
import com.jcode.util.Log4jUtil;

public class UserMapperTest {

	static SqlSessionFactory sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();
	SqlSession session;
	UserMapper userMapper;

	@BeforeClass
	public static void beforeClass() {
		Log4jUtil.initLog4jByProperty();
	}
	@Before
	public void before() {
		try {
			session = sqlSessionFactory.openSession();
			userMapper = session.getMapper(UserMapper.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void after() {
		//SqlSession调用完之后一定要关闭，最好放在finally中执行
		if (session != null) {
			session.close();
		}
	}

	
    
    /**
	 * 接口方式调用，接口方法名称要跟XML配置的映射ID和参数对应
	 */
	@Test
	public void testSelectUser2() {
		RowBounds rowBounds = new RowBounds(0, 1);
	    List<User> list = session.selectList("dao.userdao.selectUserByPage", new Object(), rowBounds);
	    System.out.println(list);
	}
	
	/**
	 * 接口方式调用，接口方法名称要跟XML配置的映射ID和参数对应
	 */
	@Test
	public void testSelectUser() {
		User user = userMapper.selectUser(1);
		System.out.println("testSelectUsers result：" + user);
	}

	@Test
	public void testSelectUsers() {
		List<User> userList = userMapper.selectUsers();
		System.out.println("testSelectUsers result：" + userList);
	}

	@Test
	public void testSelectUsersByCondition() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("interests", new String[]{"java","android"});
		data.put("start", 0);
		data.put("size", 6);
		List<User> userList = userMapper.selectUsersByCondition(data);
		System.out.println("testSelectUsersByCondition result：" + userList);
	}

	@Test
	public void testQueryAllUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertUser() {
		User user = new User();
//		user.setName("testInsertUser");
//		user.setSex("1");
//		user.setInterests(new String[] { "android" });
		int result = userMapper.insertUser(user);
		session.commit();
		System.out.println("testInsertUser result：" + result + ", " + user);
		Assert.assertEquals(1, result);
	}

	@Test
	public void testUpdateUser() {
		User user = userMapper.selectUser(1);
//		user.setName("testUpdateUser");
//		user.setSex("0");
//		user.setInterests(new String[] { "java" });
		int result = userMapper.updateUser(user);
		session.commit();
		System.out.println("testUpdateUser result：" + result + ", " + user);
		Assert.assertEquals(1, result);
	}

	@Test
	public void testDeleteUser() {
		int result = userMapper.deleteUser(1);
		session.commit();
		System.out.println("testDeleteUser result：" + result);
		Assert.assertEquals(1, result);
	}

}
