# hystrix

**目的:**

- 使用 spring + hystrix 实现类似于 spring boot hystrix 的功能
- 测试 hystrix 功能点:
    - 基于线程池 THREAD 的 隔离 和 基于 SEMAPHORE 的限流
    - 如何处理异常
    - fallback

# 模拟网络服务

服务方式选择使用 HTTP ，序列化方式不是重点，没有，直接传基础类型, 用 springmvc 模拟
- 其中 `saturn` 包下面就是远程服务提供方
- 客户端位于 `client` 包下面，测试调用情况