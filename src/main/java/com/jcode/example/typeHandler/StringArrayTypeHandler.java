package com.jcode.example.typeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * 类型转换器：Java和数据库之间的数据类型转换桥梁，这里是String数组和数据库之间的一个转换
 * 
 * @author jay
 * 
 */

public class StringArrayTypeHandler implements TypeHandler<String[]> {

	/**
	 * 根据数据库列名转换为Java的数据类型：String数组
	 */
	public String[] getResult(ResultSet rs, String columnName)
			throws SQLException {
		String columnValue = rs.getString(columnName);
		return this.getStringArray(columnValue);
	}

	/**
	 * 根据数据库列索引转换为Java的数据类型：String数组
	 */
	public String[] getResult(ResultSet rs, int columnIndex)
			throws SQLException {
		String columnValue = rs.getString(columnIndex);
		return this.getStringArray(columnValue);
	}

	public String[] getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		String columnValue = cs.getString(columnIndex);
		return this.getStringArray(columnValue);
	}

	/**
	 * Java字符串数组转换为数据库类型
	 */
	public void setParameter(PreparedStatement ps, int i, String[] parameter,
			JdbcType jdbcType) throws SQLException {
		if (parameter == null)
			ps.setNull(i, Types.VARCHAR);
		else {
			StringBuffer result = new StringBuffer();
			for (String value : parameter)
				result.append(value).append(",");
			result.deleteCharAt(result.length() - 1);
			ps.setString(i, result.toString());
		}
	}

	private String[] getStringArray(String columnValue) {
		if (columnValue == null)
			return null;
		return columnValue.split(",");
	}

}
