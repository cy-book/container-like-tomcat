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
