package com.jcode.example.cache;


import java.util.concurrent.locks.ReadWriteLock;

import org.apache.ibatis.cache.Cache;

/**
 * 
 * @Desc 自定义缓存
 *
 * @Author daijunjie
 * @DateTime 2016年1月15日 下午5:11:59
 *
 */
public class CustomCache implements Cache {

	@Override
	public String getId() {
		return null;
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public void putObject(Object key, Object value) {
		
	}

	@Override
	public Object getObject(Object key) {
		return null;
	}

	@Override
	public Object removeObject(Object key) {
		return null;
	}

	@Override
	public void clear() {
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return null;
	}


}
