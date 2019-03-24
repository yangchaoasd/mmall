#### mmall接口文档
    	http://localhost:8080/swagger-ui.html

#### sql监控
		http://localhost:8080/druid
        
#### 主要使用技术 
		spring+springmvc+mybatis+redis
        
#### 主要功能模块 
* 用户模块
* 分类模块
* 地址模块
* 商品模块
* 购物车模块
* 订单模块
* 支付模块	


#### 快速启动

* 新建数据库mmall,执行sql文件

* 配置mmall.properties(ftp, redis)


###### 以下配置均为可选


* 如需测试支付模块
    * 修改mmall.properties中alipay.callback.url支付宝回调地址
    
    * 修改zfbinfo.properties中支付宝沙箱环境配置


* 配置ShardedJedis(分布式)
    * 添加mmall.properties里的redis.ip and redis.port可以添加多个

    * 在com.mmall.common.RedisShardedPool中进行初始化即可


* 部署tomcat集群

    * 单机部署多实例：

            tomcat集群配置
        	添加环境变量
        		CATALINA_BASE 	D:\tomcat1
        		CATALINA_HOME 	D:\tomcat1
        		CATALINA_2_HOME	D:\tomcat2
        		CATALINA_2_BASE D:\tomcat2
        		TOMCAT_HOME 	D:\tomcat1
        		TOMCAT_2_HOME 	D:\tomcat2
        	在tomcat2中打开${tomcat2}/bin目录下catalina.bat, startup.bat
        		修改 CATALINA_BASE -> CATALINA_2_BASE
        			 CATALINA_HOME -> CATALINA_2_HOME
        	打开${tomcat2}/conf目录下server.xml
        		修改server port, connector port, connector port

    * 多机部署多应用：

        	如果一个机器部署一个tomcat实例，不用修改
        	如果一个机器部署多个tomcat实例，依照单机部署多实例方法
        	注：多个服务器并且每个服务器只安装一个tomcat,要保证他们之间是互通的，方可集群，nginx装在任意一台服务器上即可
        	, 也可单独把nginx服务独立出一台

* 负载均衡配置
        在nginx.conf的http节点上增加 include vhost/*.conf;
    	在nginx/conf目录下增加vhost文件夹，并添加www.ckx.vaiwan.com.conf文件
        
    	upstream www.ckx.vaiwan.com {   
    		server 127.0.0.1:8080 weight=1; #权重
    		server 127.0.0.1:9080 weight=1;
    	}
    
    	server {
    		listen 80;
    		autoindex on;
    		server_name www.ckx.vaiwan.com;
    		access_log D:/nginx-1.14.2/access.log combined;
    		index index.html index.htm index.jsp index.php;
    		location / {
    			proxy_pass http://www.ckx.vaiwan.com;
    			add_header Access-Control-Allow-Origin *;
    		}
    	}
