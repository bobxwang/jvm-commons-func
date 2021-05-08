#### 缓存
- 一级缓存 BaseExecutor PerpetualCache 跟 SqlSession 绑定，不存在并发问题
- 二级缓存跟命名空间绑定，MappedStatement 中获取，

组件	相关描述
Configuration	Mybatis 的主要配置。
包含属性、设置、类型别名、类型处理器、对象工厂、环境配置和映射器等信息。
MappedStatement	用于描述 Mapper 中的 SQL 配置信息。
对 Mapper XML 配置文件中 "<select | update | delete | insert>" 等标签，或者 @Select、@Update、@Delete、@Insert等注解配置信息的封装。
SqlSession	Mybatis 提供的面向用户的 API，可通过它来执行命令（增、删、改、查），获取映射器示例和管理事务。
Executor	Mybatis 的 SQL 执行器，Mybatis 中对数据库所有的增、删、改、查操作都是由它完成的。
StatementHandler	封装了对 JDBC Statement 对象的操作。
ParameterHandler	用于为 PreparedStatement 和 CallableStatement 对象参数占位符设置值。
ResultSetHandler	封装了对 JDBC 中的 ResultSet 对象操作，将 SELECT 查询结果抓换成 Java 对象。
TypeHandler	MyBatis 中的类型处理器，用于处理 Java 类型和 JDBC 类型之间的映射。