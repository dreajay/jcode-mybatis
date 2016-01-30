package com.jcode.example.plugin;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

/**
 * Mybatis拦截器只能拦截四种类型的接口：Executor、StatementHandler、ParameterHandler和ResultSetHandler。
 * Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
 * ParameterHandler (getParameterObject, setParameters)
 * ResultSetHandler (handleResultSets, handleOutputParameters)
 * StatementHandler (prepare, parameterize, batch, update, query)
 * 该插件会拦截在Executor接口下所有实例的“update”方法调用，它也是负责低层次映射语句执行的内部对象
 * @author jay
 *
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = {
		MappedStatement.class, Object.class }) })
public class ExamplePlugin implements Interceptor {

	/**
	 * 对目标方法进行拦截，通过invocation.proceed()方法调用下一个拦截器拦截目标方法
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		return invocation.proceed();
	}

	/**
	 * 通过Plugin.wrap(target,this)方法，用当前这个拦截器生成对目标target的代理
	 */
	@Override
	public Object plugin(Object target) {
		// 当目标类是Executor类型时，才包装目标类，否则直接返回目标本身,减少目标被代理的次数
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
	}

	/**
	 * 从SqlMapConfig配置的plugin中获取参数
	 * <plugins>
		<plugin interceptor="com.demo.mybatis.plugin.ExamplePlugin">
			<property name="someProperty" value="100" />
		</plugin>
	  </plugins>
	 */
	@Override
	public void setProperties(Properties properties) {
	}
}
