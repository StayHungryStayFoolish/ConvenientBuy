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


### 补充: IDEA 解决 Invalid bound statement (not found) 办法

    看到了两篇文章,测试了效果不错,做下笔记.
    
    问题原因: xml 没有在 resources 文件下.
    
    打脸方法: 移动到 resources 文件夹内,在 sqlSessionFactory 内,配置扫描路径
    <!-- 自动扫描mapping.xml文件 -->
    		<property name="mapperLocations" value="classpath*:mapper/*.xml"/>
    
    解决前提: xml 不存在 resources 文件夹内
    方法1: maven 中添加过滤
    
    <!--配置Maven 对resource文件 过滤 -->
            <resources>
                <resource>
                    <directory>src/main/resources</directory>
                    <includes>
                        <include>**/*.properties</include>
                        <include>**/*.xml</include>
                    </includes>
                    <filtering>true</filtering>
                </resource>
                <resource>
                    <directory>src/main/java</directory>
                    <includes>
                        <include>**/*.properties</include>
                        <include>**/*.xml</include>
                    </includes>
                    <filtering>true</filtering>
                </resource>
            </resources>
            
    方法2: sqlMap.xml（mybatis-config.xml）中配置mapper自动注册扫描包
         <mappers>
                 <mapper resource="sqlmap/User.xml"/>
                 <!--通过resource方法一次加载一个映射文件 -->
                  <!--<mapper resource="sqlmap/UserMapper.xml"/>-->
         
                 <!-- 通过mapper接口加载单个 映射文件
                 遵循一些规范：需要将mapper接口类名和mapper.xml映射文件名称保持一致，且在一个目录 中
                 上边规范的前提是：使用的是mapper代理方法
                  -->
                  <!--<mapper class="cn.itcast.mybatis.mapper.UserMapper"/>-->
         
                 <!-- 批量加载mapper
                 指定mapper接口的包名，mybatis自动扫描包下边所有mapper接口进行加载
                 遵循一些规范：需要将mapper接口类名和mapper.xml映射文件名称保持一致，且在一个目录 中
                 上边规范的前提是：使用的是mapper代理方法
                  -->
                 <package name="cn.itcast.mybatis.mapper"/>
         
             </mappers>

    方法3:父类项目 pom.xml 文件中
    <build>
        <!--这里进行配置，后会自动的加载mapper.xml文件　:配置Maven 对resource文件 过滤 -->
        <resources>
          <resource>
            <directory>src/main/resources</directory>
            <includes>
              <include>**/*.properties</include>
              <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
          </resource>
          <resource>
            <directory>src/main/java</directory>
            <includes>
              <include>**/*.properties</include>
              <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
          </resource>
        </resources>
      </build>
      
      