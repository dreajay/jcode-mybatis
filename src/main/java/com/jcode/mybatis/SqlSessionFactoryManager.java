package com.jcode.mybatis;

import java.io.IOException;
import java.io.Reader;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

/**
 * SqlSessionFactory生命周期为贯穿整个应用程序,一般保持生命周期都使用同一个实例，
 * 所以SqlSessionFactory应该保持全局静态变量或使用单利模式来使用它
 * 
 * @author dreajay
 * 
 */
public class SqlSessionFactoryManager {
	// SqlSessionFactory一个实例对应一个数据库配置
	public SqlSessionFactory sqlSessionFactory;

	public static SqlSessionFactoryManager INSTANCE = new SqlSessionFactoryManager();

	private SqlSessionFactoryManager() {
		sqlSessionFactory = buildSqlSessionFactoryFromXML("SqlMapConfig.xml");
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return INSTANCE.sqlSessionFactory;
	}

	/**
	 * 根据配置文件创建SqlSessionFactory
	 * 
	 * @param SqlMapConfigFile
	 *            SqlMapConfig配置文件，文件应该放在当前的classpath中
	 * @return SqlSessionFactory
	 */
	public SqlSessionFactory buildSqlSessionFactoryFromXML(String SqlMapConfigFile) {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(SqlMapConfigFile);
			// SqlSessionFactoryBuilder创建完SqlSessionFactory就不需要了，生命周期在方法内，不需要保存本地变量
			return new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (reader != null) {
				try {
					reader.close();
					reader = null;
				} catch (IOException e) {
				}
			}
		}

	}

	/**
	 * 根据配置文件创建SqlSessionFactory
	 * 
	 * @param SqlMapConfigFile
	 *            SqlMapConfig配置文件，文件应该放在当前的classpath中
	 * @param environment
	 *            指定的数据库环境
	 * @return SqlSessionFactory
	 */
	public SqlSessionFactory buildSqlSessionFactoryFromXML(String SqlMapConfigFile, String environment) {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(SqlMapConfigFile);
			// SqlSessionFactoryBuilder创建完SqlSessionFactory就不需要了，生命周期在方法内，不需要保存本地变量
			return new SqlSessionFactoryBuilder().build(reader, environment);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (reader != null) {
				try {
					reader.close();
					reader = null;
				} catch (IOException e) {
				}
			}
		}

	}

	/**
	 * 手动创建SqlSessionFactory
	 * 
	 * @return SqlSessionFactory
	 */
	public SqlSessionFactory buildSqlSessionFactoryFromJava() {
		DataSource dataSource = null;// 数据库连接
		// dataSource = BlogDataSourceFactory.getBlogDataSource();
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);
		return new SqlSessionFactoryBuilder().build(configuration);
	}

}
