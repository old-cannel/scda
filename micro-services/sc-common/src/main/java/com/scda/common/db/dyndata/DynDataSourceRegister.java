package com.scda.common.db.dyndata;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.wall.WallFilter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuqi
 * @Date 2018-06-11.
 * @describe
 */
//@Configuration
public class DynDataSourceRegister {
    private static Logger LOGGER = LoggerFactory.getLogger(DynDataSourceRegister.class);
    public static final String ONE = "one";
    public static final String TWO = "two";
    public static final String PREFIX = "spring.datasource.druid.";


    /**
     * 构建数据源
     *
     * @param env       配置环境
     * @param properKey 多数据库key
     * @return
     */
    public static DataSource bulidDataSource(Environment env, String properKey) {
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, DynDataSourceRegister.PREFIX + properKey + ".");
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();

        druidDataSource.setDbType(propertyResolver.containsProperty("type") ? "mysql" : propertyResolver.getProperty("type"));
        druidDataSource.setUrl(propertyResolver.getProperty("url"));
        druidDataSource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
        druidDataSource.setUsername(propertyResolver.getProperty("username"));
        druidDataSource.setPassword(propertyResolver.getProperty("password"));
        //连接池
        druidDataSource.setMaxActive(Integer.parseInt(propertyResolver.getProperty("max-active")));
        druidDataSource.setMaxWait(Integer.parseInt(propertyResolver.getProperty("max-wait")));
        druidDataSource.setInitialSize(Integer.parseInt(propertyResolver.getProperty("initial-size")));
        druidDataSource.setMinIdle(Integer.parseInt(propertyResolver.getProperty("min-idle")));
        druidDataSource.setPoolPreparedStatements(Boolean.valueOf(propertyResolver.getProperty("pool-prepared-statements")));
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(propertyResolver.getProperty("max-pool-prepared-statement-per-connection-size")));
//    过滤器
        if (propertyResolver.containsProperty("filters") && StringUtils.isNotBlank(propertyResolver.getProperty("filters"))) {
            List<Filter> filterList = new ArrayList<>();
            String[] filters = propertyResolver.getProperty("filters").split(",");
            for (int i = 0; i < filters.length; i++) {
                String filterName = filters[i];
                if ("stat".equals(filterName)) {
                    StatFilter statFilter = new StatFilter();
                    filterList.add(statFilter);
                    continue;
                } else if ("wall".equals(filterName)) {
                    WallFilter wallFilter = new WallFilter();
                    filterList.add(wallFilter);
                    continue;
                }
            }

            druidDataSource.setProxyFilters(filterList);
        }
        return druidDataSource;

    }


}
