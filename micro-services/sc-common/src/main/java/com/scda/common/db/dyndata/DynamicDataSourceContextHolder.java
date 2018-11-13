package com.scda.common.db.dyndata;

/**
 * @author liuqi
 * @Date 2018-06-08.
 * @describe
 */
public class DynamicDataSourceContextHolder {
  private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();
  public static void putDataSource(String dataSource){
    HOLDER.set(dataSource);
  }


  public static String getDataSource(){
    return HOLDER.get();
  }

  public static void clearDataSource() {
    HOLDER.remove();
  }
}
