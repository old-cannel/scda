#认证授权模块
    该模块是基于前后端分离的架构设计，基于spring security框架实现
#模块目录
    sc-security
        security-single-jwt 单工程-jwt认证+授权
        security-sso 单点登录认证+授权
        security-test-client1 测试客户端1
        security-test-client2 测试客户端2
#单工程-jwt认证+授权（架包依赖）
    使用说明:  
    1.添加依赖  
         <dependency>  
            <groupId>com.scda.sc-security</groupId>  
            <artifactId>security-single-jwt</artifactId>  
            <version>${project_version}</version>  
         </dependency>  
    2.修改用户登录从数据库获取用户信息、用户权限  
        sc-security\security-single-jwt\src\main\java\com\scda\security\service\MySecurityService.java  
        方法：loadByUserName(String userName)  
              loadByUserId(String userId)  
    3.修改从数据库获取权限资源  
        sc-security\security-single-jwt\src\main\java\com\scda\security\service\MySecurityService.java  
        方法：List<SysRoleMenuVo> loadAllRroleMenuIds()  
    4.修改安全配置文件(可选,存在不需要登录的资源就需要配置)   
        sc-security\security-single-jwt\src\main\java\com\scda\security\config\WebSecurityConfig.java  
    5.关于密码加密(该方法相同密码加密后值会不一样，但不影响校验)
        BCryptPasswordEncoder类中方法：encode("your password")  
    6. todo 短信登录、qq登录、微信登录
#分布式工程-sso认证+授权（独立服务） 

    
