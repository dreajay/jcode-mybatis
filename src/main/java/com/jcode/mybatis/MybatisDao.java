package com.jcode.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.cglib.beans.BeanMap;

import com.jcode.spring.SpringApplicationContextHolder;

/**
 * 
 * @Desc Mybatis通用Dao
 *
 * @Author dreajay
 * @DateTime 2015年11月29日 下午6:14:24
 * 
 * @param <T>
 */
public class MybatisDao {

	private SqlSessionTemplate sqlSession;

	private String mapperNamespace;

	public static final String DEFAULT_SQLSESSION = "sqlSession";

	public static final String SEPARATOR = ".";

	/**
	 * 
	 * @Desc 数据库执行语句，和Mapper配置的保持一致，可继承本接口并添加其他数据库执行语句
	 *
	 */
	public static interface Statement {
		/** 查询数量 statement */
		public static final String COUNT = "count";
		/** 查询单个对象 statement */
		public static final String SELECT = "select";
		/** 查询所有对象 statement */
		public static final String SELECT_ALL = "selectAll";
		/** 查询分页列表对象 statement */
		public static final String SELECT_LIST_BY_ROW = "selectListByRow";
		/** 插入对象 statement */
		public static final String INSERT = "insert";
		/** 更新对象 statement */
		public static final String UPDATE = "update";
		/** 删除对象 statement */
		public static final String DELETE = "delete";
	}

	public MybatisDao(String mapperNamespace) {
		this(mapperNamespace, (SqlSessionTemplate) SpringApplicationContextHolder.getBean(DEFAULT_SQLSESSION));
	}

	public MybatisDao(String mapperNamespace, SqlSessionTemplate sqlSession) {
		this.mapperNamespace = mapperNamespace;
		this.sqlSession = sqlSession;
	}

	public String getMapperNamespace() {
		return mapperNamespace;
	}

	public void setMapperNamespace(String mapperNamespace) {
		this.mapperNamespace = mapperNamespace;
	}

	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	/**
	 * 查询对象数目
	 * 
	 * @param parameter
	 * @return
	 */
	public long count(Object parameter) {
		return (Long) sqlSession.selectOne(mapperNamespace + SEPARATOR + Statement.COUNT, parameter);
	}

	/**
	 * 自定义查询对象数目
	 * 
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public long count(String statement, Object parameter) {
		return (Long) sqlSession.selectOne(mapperNamespace + SEPARATOR + statement, parameter);
	}

	/**
	 * 查询单个对象
	 * 
	 * @param parameter
	 * @return
	 */
	public <T> T select(Object parameter) {
		return sqlSession.selectOne(mapperNamespace + SEPARATOR + Statement.SELECT, parameter);
	}

	/**
	 * 自定义查询单个对象
	 * 
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public <T> T select(String statement, Object parameter) {
		return sqlSession.selectOne(mapperNamespace + SEPARATOR + statement, parameter);
	}

	/**
	 * 查询所有对象
	 * 
	 * @param parameter
	 * @return
	 */
	public <T> List<T> selectAll(Object parameter) {
		return sqlSession.selectList(mapperNamespace + SEPARATOR + Statement.SELECT_ALL, parameter);
	}

	/**
	 * 分页查询对象列表
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param parameter
	 * @return
	 */
	public <T> List<T> selectListByPage(Object parameter, int pageNum, int pageSize) {
		int rowNum = pageNum <= 1 ? 0 : (pageNum - 1) * (pageSize);
		return selectListByRow(parameter, rowNum, pageSize);
	}

	/**
	 * 分页查询对象列表
	 * 
	 * @param rowNum
	 * @param pageSize
	 * @param parameter
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> selectListByRow(Object parameter, int rowNum, int pageSize) {
		Map<String, Object> param = null;
		if (parameter instanceof Map) {
			param = (Map) parameter;
		} else {
			param = new HashMap<String, Object>();
			param.putAll(BeanMap.create(parameter));
		}
		param.put("rowNum", rowNum);
		param.put("pageSize", pageSize);
		return sqlSession.selectList(mapperNamespace + SEPARATOR + Statement.SELECT_LIST_BY_ROW, param);
	}

	/**
	 * 自定义查询对象列表
	 * 
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public <T> List<T> selectList(String statement, Object parameter) {
		return sqlSession.selectList(mapperNamespace + SEPARATOR + statement, parameter);
	}

	/**
	 * 插入对象
	 * 
	 * @param parameter
	 * @return
	 */
	public int insert(Object parameter) {
		return sqlSession.insert(mapperNamespace + SEPARATOR + Statement.INSERT, parameter);
	}

	/**
	 * 自定义插入对象
	 * 
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public int insert(String statement, Object parameter) {
		return sqlSession.insert(mapperNamespace + SEPARATOR + statement, parameter);
	}

	/**
	 * 更新对象
	 * 
	 * @param parameter
	 * @return
	 */
	public int update(Object parameter) {
		return sqlSession.update(mapperNamespace + SEPARATOR + Statement.UPDATE, parameter);
	}

	/**
	 * 自定义更新对象
	 * 
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public int update(String statement, Object parameter) {
		return sqlSession.update(mapperNamespace + SEPARATOR + statement, parameter);
	}

	/**
	 * 删除对象
	 * 
	 * @param parameter
	 * @return
	 */
	public int delete(Object parameter) {
		return sqlSession.delete(mapperNamespace + SEPARATOR + Statement.DELETE, parameter);
	}

	/**
	 * 自定义删除对象
	 * 
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public int delete(String statement, Object parameter) {
		return sqlSession.delete(mapperNamespace + SEPARATOR + statement, parameter);
	}

}
