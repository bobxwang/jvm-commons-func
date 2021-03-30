#### 缓存
- 一级缓存 BaseExecutor PerpetualCache 跟 SqlSession 绑定，不存在并发问题
- 二级缓存跟命名空间绑定，MappedStatement 中获取，