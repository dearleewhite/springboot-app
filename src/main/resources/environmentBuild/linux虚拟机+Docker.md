1,基本信息:用户-密码 : root  b0b0463491

2,上传文件: yum  install lrzsz 安装文件传输软件

3,配置环境变量 

```sh
# 查看配置文件
vi /etc/profile
# 加入配置
export JAVA_HOME=/root/jdk1.8.0_181/
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$PATH:$JAVA_HOME/bin
# 刷新环境变量
source /etc/profile
```

二.Docker使用

1,安装docker yum install,(优先配置好 yum 源)

2,启动docker

```sh
[root@localhost ~]# sudo systemctl enable docker
Created symlink from /etc/systemd/system/multi-user.target.wants/docker.service to /usr/lib/systemd/system/docker.service.
[root@localhost ~]# sudo systemctl start docker
```

3,启动mysql镜像 

```sh
docker run -p 3306:3306 --name hwl_mysql -e MYSQL_ROOT_PASSWORD=bob463491 -d 697
```

建表语句

```sql
CREATE TABLE `user` (
  `id` bigint(64) NOT NULL COMMENT '主键', 
  `name` varchar(255) DEFAULT NULL COMMENT '名字',
  `age` tinyint UNSIGNED DEFAULT NULL COMMENT '年龄',
  `create_user_id` bigint(64) DEFAULT NULL COMMENT '创建人id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user_id` bigint(64) DEFAULT NULL COMMENT '更新人id',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '删除位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='人员表';
```





