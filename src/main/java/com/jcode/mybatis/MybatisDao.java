package com.jcode.mybatis;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

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

	private String namespace;

	public static final String DEF_SQLSESSION = "sqlSession";

	public static final String SEPARATOR = ".";

	public static final String SELECT = "select";
	public static final String SELECT_LIST = "selectList";
	public static final String INSERT = "insert";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";

	public MybatisDao(String namespace) {
		this(namespace, (SqlSessionTemplate) SpringApplicationContextHolder.getBean(DEF_SQLSESSION));

	}

	public MybatisDao(String namespace, SqlSessionTemplate sqlSession) {
		this.namespace = namespace;
		this.sqlSession = sqlSession;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	public <T> T select(Object parameter) {
		return sqlSession.selectOne(namespace + SEPARATOR + SELECT, parameter);
	}

	public <T> T select(String statementId, Object parameter) {
		return sqlSession.selectOne(namespace + SEPARATOR + statementId, parameter);
	}

	public <T> List<T> selectList(int startRow, int pageSize, Object parameter) {
		return sqlSession.selectList(namespace + SEPARATOR + SELECT_LIST, parameter);
	}

	public <T> List<T> selectList(String statementId, Object parameter) {
		return sqlSession.selectList(namespace + SEPARATOR + statementId, parameter);
	}

	public int insert(Object parameter) {
		return sqlSession.insert(namespace + SEPARATOR + INSERT, parameter);
	}

	public int insert(String statementId, Object parameter) {
		return sqlSession.insert(namespace + SEPARATOR + statementId, parameter);
	}

	public int update(Object parameter) {
		return sqlSession.update(namespace + SEPARATOR + UPDATE, parameter);
	}

	public int update(String statementId, Object parameter) {
		return sqlSession.update(namespace + SEPARATOR + statementId, parameter);
	}

	public int delete(Object parameter) {
		return sqlSession.delete(namespace + SEPARATOR + DELETE, parameter);
	}

	public int delete(String statementId, Object parameter) {
		return sqlSession.delete(namespace + SEPARATOR + statementId, parameter);
	}

}
