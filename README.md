# 良品购 -- 在线商城

### 开发环境
    
    OS + IDEA + Ubuntu
    
    MySQL 数据库
    
    本机 Tomcat 7.0 以上

### 项目结构介绍
    | parent 父类聚合工程 POM
   
        | common 通用组件 JAR
        
        | manager CMS 聚合工程 POM
        
            | manager-mapper Mybatis Generator 生成接口 JAR
            
            | manager-pojo Mybatis Generator 生成POJO JAR
            
            | manager-service CMS 业务层 JAR
            
            | manager-web CMS 表现层 WAR
            
        | portal 网站系统 WEB
        
        | rest 服务接口 WEB
        
        | search 搜索接口 WEB
        
        | sso 单点登录接口 WEB (Srping HandlerInterceptor 前处理 )
        
        | order 订单接口 WEB
        
    
### 框架及个别技术点
    
    
    Spring / SpringMVC / Mybatis
    
    Nginx 反向代理
      
    Redis 缓存服务
    
    Zookeeper (动物馆管理员,管理者一群不听话的小动物,哈哈)
    
    Solr 搜索服务
    
    jsonp 跨域服务器之间请求
    
    commons-net FTPClien 
### 
    
### 坑
    
    系统起来,但是调用接口无效.提示资源文件找不到.
  
    修改 pom.xml 文件做了映射处理,不起作用.
    使用 maven 的 mapper 插件,无效.
    使用 maven 的 reourses 插件无效.
    检查 spring 配置,正确.
    系统起不来.
    
    stackoverfollow 也找不到正确答案.
    后来发现,逆向工程生成的 xml 文件有问题.
    mybatis generator 坑太多.
    建议使用 freemarker .
    
####
    
    年底了, GitHub 终于收了个尾.
    明天开始新的 commit .....
    
    