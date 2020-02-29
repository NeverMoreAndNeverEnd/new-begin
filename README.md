new-begin

项目目的

spring cloud 入门,mybatis plus 练习.

项目简述

简单版的在线教育系统,模块包括后台课程管理,讲师管理,课程发布(视频发布),课程分类管理,文件上传,视频上传,统计分析等.

后台采用springboot+springcloud+mybatisplus .

管理系统前端采用 vue-admin-template-master.

门户网站前端采用 nuxt-template.

技术栈:

java 1.8

maven 3.3.9

spring boot 2.2.1.RELEASE

spring cloud Hoxton.RC1

mybatis plus 3.2.0

swagger 2.9.2

poi 3.9

其他: lombok,druid,commons-fileupload ,echarts ,阿里云对象存储oss,阿里云服务器在线播放等等.

项目模块说明

1.never-common :

通用组件定义.

2.never-framework:

整合测试工程(测试整合mybatis,阿里云oss存储,阿里云文件上传,阿里云视频上传,视频点播功能等).

3.never-eureka:

spring cloud 注册中心

4.nevernever-microservice-edu :

后台课程中心微服务,负责管理课程,讲师,课程分类,文件上传等.

5.nevernever-microservice-video:

视频中心微服务,负责管理阿里云视频上传,点播等.

6.nevernever-microservice-ucenter:

用户中心微服务.负责统计登录,注册人数等

7.nevernever-microservice-statistics:

统计分析微服务,提供统计数据,生成报表等.

项目总结

后台总结

1.mybatisplus:

使用了代码生成工具,自动生成entity,controller,service,mapper;

创建config文件,定义分页插件,乐观锁插件,注解配置事物,扫描包;

自动插入的字段(插入时间,修改时间,乐观锁,逻辑删除等),实现MetaObjectHandler;

配置文件定义扫描mapper地址,同时pom.xml也要修改.

2.定义全局返回值 R 包括状态码,是否成功,以及返回数据

3,跨域解决,使用@CrossOrigin 注解

4.自定义异常继承RuntimeException

5.微服务之间的相互调用,先注册到eureka,然后配置 feignClient .定义调用接口,

通过ip地址+服务名调用对应微服务

6.严格遵循Restful 风格的api

前台总结

1.router中定义路由.

2.vue的基本模式,上面是template,下面是script,template使用element-ui中的组件,cv后改成自己想要的

3.scripts中写js代码,通用模式,先import 事先写好的api,export default中,普遍是3大块,

    data(){//定义变量和初始值
    
    return{
    
      }
    
    },

    created() {//在页面渲染之前调用,调用具体的某个方法
    
    },
    

    method{//写具体的方法调用
    
    }

4.通过nginx分发路由,进行不同微服务的调用.





