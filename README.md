<p align="center">
	<strong>基于springboot的快速集成多数据源的启动器</strong>
</p>

# 简介

dynamic-datasource-starter 基于springboot的快速集成多数据源的启动器。

**Jdk 1.8, SpringBoot 2.x**。

# 功能点
- 本框架主要简化了对Druid、Dbcp2、ShardingJdbc等组件的集成方式；
- 支持普通数据源之间、shardingJdbc数据源之间、以及普通数据源与shardingJdbc数据源的相互切换；
- 约定配置文件名称为 jdbcdynamic.properties

# 快速上手
- 提供dynamic-datasource-test示例项目，提供大家快速上手。
- dynamic-datasource-test示例项目地址：https://github.com/bozrahvice/dynamic-datasource-test
- 下面介绍下 dynamic-datasource-starter 的使用。

# 添加依赖
```maven
    <dependency>
        <groupId>io.github.bozrahvice.starter</groupId>
        <artifactId>dynamic-datasource-starter</artifactId>
        <version>1.0.0</version>
    </dependency>
```
```
SpringBootApplication中去掉DataSourceAutoConfiguration.class 和 DataSourceHealthContributorAutoConfiguration.class
```

```
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceHealthContributorAutoConfiguration.class})
```
