# container-like-tomcat
模仿tomcat编写一个web容器

##运行
拷贝项目 ， 在根目录下（有pom.xml）执行maven package
会生成target目录， 下面有两个jar包，分别是包含依赖的jar，和原始jar 
也可以通过class 文件运行 ,依赖在lib 文件中

#配置
默认的端口 ： 8080
默认的静态资源文件夹： 根目录下webapps
修改配置文件: ./src/main/resources/application.properties 
			port=8081 
			web_root=/home/role/WWW

#添加功能加载Servlet
你可以将你的servlet文件放在WEB_ROOT目录下的servlets文件夹下
并在application.properties文件中配置 请求名称_Servlet=类名 
	例如：（默认含有一个servlet文件 hz.xhxh.container.servlet.PrimitiveServlet)
		Primitive_Servlet=hz.xhxh.container.servlet.PrimitiveServlet
当你请求页面时就可以加载servlet 比如访问 http://localhost/servlet/Primitive

#使用facade设计模式
使用FacadeRequest类和FacadeResponse类 将Request 类 和 Response 类封装起来
避免一些安全隐患：
This compromises security. Servlet programmers who know the internal workings of
this servlet container can downcast the ServletRequest and ServletResponse
instances back to ex02.pyrmont.Request and ex02.pyrmont.Response respectively
and call their public methods. Having a Request instance, they can call its parse
method. Having a Response instance, they can call its sendStaticResource method.
理解起来就是 用户的Servlet接受ServletRequest和ServletResponse, 如果他知道了内部的实现，
就可以用向下转型将其转换为Request和Response,这样就会调用他们的方法,parse(),getUri(),
使用门面模式避免了这些。


