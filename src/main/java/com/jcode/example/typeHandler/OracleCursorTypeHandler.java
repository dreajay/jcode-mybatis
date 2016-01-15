package com.jcode.example.typeHandler;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * Oracle存储过程游标类型处理. 通过该类处理，会把游标中的数据依次取出来并放到List中返回
 * 
 * 
 */
public class OracleCursorTypeHandler extends BaseTypeHandler<List<LinkedHashMap<String, Object>>> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, List<LinkedHashMap<String, Object>> parameter, JdbcType jdbcType) throws SQLException {
	}

	@Override
	public List<LinkedHashMap<String, Object>> getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return null;
	}

	@Override
	public List<LinkedHashMap<String, Object>> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return null;
	}

	/**
	 * Oracle存储过程中，把游标里面的值依次放入List中
	 */
	@Override
	public List<LinkedHashMap<String, Object>> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>();
		ResultSet rs = (ResultSet) cs.getObject(columnIndex);
		{
			if (rs != null) {
				ResultSetMetaData md = rs.getMetaData();
				int columnCount = md.getColumnCount();
				while (rs.next()) {
					LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
					Object value = null;
					for (int index = 1; index <= columnCount; index++) {
						if (rs.getObject(index) == null) {
							value = "";
						} else {
							value = rs.getObject(index);
						}
						String key = md.getColumnName(index);
						if (value == null) {
							map.put(key.toLowerCase(), "");
						} else {
							map.put(key.toLowerCase(), value);
						}

					}
					list.add(map);
				}
			}
			rs.close();
		}
		return list;
	}




}
