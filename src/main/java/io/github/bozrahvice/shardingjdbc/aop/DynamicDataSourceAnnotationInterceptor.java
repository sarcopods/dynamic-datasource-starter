package io.github.bozrahvice.shardingjdbc.aop;

import io.github.bozrahvice.shardingjdbc.commons.DataSourceClassResolver;
import io.github.bozrahvice.shardingjdbc.commons.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


/**
 * @author ylpanda
 * @since 1.0.0
 */
@Slf4j
public class DynamicDataSourceAnnotationInterceptor implements MethodInterceptor {

    private final DataSourceClassResolver dataSourceClassResolver;

    public DynamicDataSourceAnnotationInterceptor(Boolean allowedPublicOnly) {
        dataSourceClassResolver = new DataSourceClassResolver(allowedPublicOnly);
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        String dsKey = determineDatasourceKey(methodInvocation);
        DynamicDataSourceContextHolder.push(dsKey);
        try {
            return methodInvocation.proceed();
        } finally {
            DynamicDataSourceContextHolder.poll();
        }
    }

    /**
     * 获取默认数据源
     *
     * @ date 2021/9/23 11:26
     * @ param invocation
     * @ return java.lang.String
     */
    private String determineDatasourceKey(MethodInvocation invocation) {
        String ds = dataSourceClassResolver.findDSKey(invocation.getMethod(), invocation.getThis());
        if (ds == null) {
            return "";
        }
        return ds;
    }
}