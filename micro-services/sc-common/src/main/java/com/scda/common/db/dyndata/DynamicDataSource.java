package com.scda.common.db.dyndata;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author liuqi
 * @Date 2018-06-08.
 * @describe
 */
public class DynamicDataSource  extends AbstractRoutingDataSource {
  @Override
  protected Object determineCurrentLookupKey() {
    return DynamicDataSourceContextHolder.getDataSource();
  }


}
