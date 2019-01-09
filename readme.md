# scda
#微服务框架
#目录结构
#apps 应用 dav+antd nmp管理
#microservices 微服务 Spring boot+cloud maven管理
sc-common 公共服务（框架）
sc-config 配置中心（框架）
sc-security 认证授权中心（框架）
sc-eureka 注册和发现服务（框架）
sc-zuul 网关（框架）
sc-modules 业务模块
    common-business 公共业务（业务）
    demo 演示模块（所有模块都要依赖公共业务）

#微服务框架下应用启动顺序
运行注册发现 sc-eureka
运行配置中心 sc-config
运行网关（如果需要统一url） sc-zuul
运行业务模块 sc-modules下模块

#本地单业务模块开发
1.修改单业务模块工程的bootstarp.yml文件中，以下三个enabled为false
  cloud:
    config:
      discovery:
        enabled: false
      enabled: false
eureka:
  client:
    enabled: false
2.复制sc-modules 下的application.yml 文件到对应模块的resources
3.修改resources下的application.yml
server:
  port: ${PORT:xx}
  context-path: /xx


