package com.jcode.example.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.jcode.example.model.User;


/**
 * 映射器
 * @author jay
 *
 */
public interface UserMapper {

	/**
	 * xml配置方式
	 */
	public User selectUser(int userid);
	public User findtest(int userid);
	public List<User> selectUsers();
	/**
	 * 使用Map传递多参数
	 * @param data
	 * @return
	 */
	public List<User> selectUsersByCondition(Map<String, Object> data);
	public int insertUser(User user);
	public int updateUser(User user);
	public int deleteUser(int userid);
	

	/**
	 * 注解方式
	 */
	@Select("select * from user where userid = #{userid}")
	public User annotateSelectUser(int userid);
	
	@Select("select * from user")
	@ResultMap(value="UserResult")
	public List<User> annotateSelectUsers();
    
	@Insert("insert into user(name, interests) values " +
			"(#{name}, #{interests, typeHandler=com.mybatisdemo.typeHandler.StringArrayTypeHandler})")
    @Options(useGeneratedKeys=true, keyProperty="userid", keyColumn="userid", flushCache=true)
    public int insertStudent(User student);
        

    
}
