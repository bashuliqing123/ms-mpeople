#用户模块



#maven依赖
```
<!--war包含用户模块编译好的字节码文件与前端试图ftl页面-->
<dependency>
  <groupId>net.mingsoft</groupId>
  <artifactId>ms-basic</artifactId>
  <version>1.0.3-SNAPSHOT</version>
  <type>war</type>
</dependency>

<!--用户模块的源代码-->
<dependency>
  <groupId>net.mingsoft</groupId>
  <artifactId>ms-basic</artifactId>
  <version>1.0.3-SNAPSHOT</version>
  <classifier>sources</classifier>
  <scope>provided</scope>
</dependency>

<!--jar包依赖方便业务开发调用-->
<dependency>
  <groupId>net.mingsoft</groupId>
  <artifactId>ms-basic</artifactId>
  <version>1.0.3-SNAPSHOT</version>
  <classifier>classes</classifier>
  <scope>provided</scope>
</dependency> 
```
