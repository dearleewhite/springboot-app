1. 安装nohup

```sh

[root@localhost tool] which nohup  # 查看是否有nohup命令
[root@localhost tool] yum install coreutils # 安装
[root@localhost tool] which nohup #查看命令是否安装成功
/usr/bin/nohup
[root@localhost tool] vi ~/.bash_profile  # 修改配置文件
[root@localhost tool] source ~/.bash_profile  # 配置文件生效
[root@localhost tool] nohup --version
```

2. nohup命令运行(后台): nohub 命令 &

```sh
[root@localhost tool] nohup java -jar springboot-app-1.0-SNAPSHOT.jar > temp.txt &
[1] 3540
# temp.txt 输出文件, & 后台运行
```

3. 下载并运行arthas

```sh
curl -O https://arthas.aliyun.com/arthas-boot.jar
java -jar arthas-boot.jar
# 选择黏附的 进程号
# 进入arthas的界面
# 反编译代码
[arthas@3811]$ jad --source-only com.mercy.service.impl.TypeServiceImpl > /tmp/TypeServiceImpl.java
# 修改代码
[arthas@3811]$ vim /tmp/TypeServiceImpl.java
# 查找类加载器
[arthas@3811]$ sc -d com.mercy.service.impl.TypeServiceImpl  | grep classLoaderHash
 classLoaderHash   439f5b3d
# 编译修改后的代码
[arthas@3811]$ mc -c 439f5b3d /tmp/TypeServiceImpl.java -d /tmp
Memory compiler error, exception message: Compilation Error
line: 36 , message: 对于le(boolean,Type::getLevel,java.lang.Object), 找不到合适的方法
    方法 com.baomidou.mybatisplus.core.conditions.interfaces.Compare.le(boolean,com.baomidou.mybatisplus.core.toolkit.support.SFunction<java.lang.Object,?>,java.lang.Object)不适用
      (参数不匹配; 方法引用无效
          无法将 类 com.mercy.entity.Type中的 方法 getLevel应用到给定类型
            需要: 没有参数
            找到: java.lang.Object
            原因: 实际参数列表和形式参数列表长度不同)
    方法 com.baomidou.mybatisplus.core.conditions.AbstractWrapper.le(boolean,com.baomidou.mybatisplus.core.toolkit.support.SFunction<java.lang.Object,?>,java.lang.Object)不适用
      (参数不匹配; 方法引用无效
          无法将 类 com.mercy.entity.Type中的 方法 getLevel应用到给定类型
            需要: 没有参数
            找到: java.lang.Object
            原因: 实际参数列表和形式参数列表长度不同) , 
            please check $HOME/logs/arthas/arthas.log for more details.
# 编译容易失败,还是使用idea的编译
# 热更新代码	
[arthas@3811]$ redefine tmp/TypeServiceImpl.class 
redefine success, size: 1, classes:
com.mercy.service.impl.TypeServiceImpl
# 热更新代码,路径改变并不能生效,只能修改代码逻辑, 即使jad命令看到的路径也不生效 --> 注解是不是都不生效

```

4. 查看java进程, 杀死进程

```sh
ps aux | grep java:查到java的进程
kill -s 9 pid
```

5. 运行nacos最新 , 默认运行端口 8848, app运行8080

```sh
1. 安装maven
tar -zxvf apache-maven-3.6.3-bin.tar.gz 
vim /etc/profile
	export MAVEN_HOME=/usr/local/src/apache-maven-3.5.0
	export PATH=${MAVEN_HOME}/bin:${PATH}
source ~/.bash_profile
mvn -v
-- 安装nacos
mvn -Prelease-nacos -Dmaven.test.skip=true clean install -U  ls -al distribution/target/

#  运行nacos,以上步骤非必须
tar -xvf nacos-server-2.0.0-ALPHA.1.tar.gz 
cd nacos/bin/
# 开启nacos
sh startup.sh -m standalone
# 关闭nacos
sh shutdown.sh
```

6. linux时间同步

```sh
yum install -y ntpdate
ntpdate 0.asia.pool.ntp.org
hwclock --systohc
# 配置开机校验
vim /etc/rc.d/rc.local 
	/usr/sbin/ntpdate -u cn.pool.ntp.org> /dev/null 2>&1; /sbin/hwclock -w
# 配置定时任务
vim /etc/crontab
	00 10 * * * root /usr/sbin/ntpdate -u cn.pool.ntp.org > /dev/null 2>&1; /sbin/hwclock -w 
```

6. 项目引入canal监听数据库

```sh
1. 安装canal
2. mysql配置canal账户
	-- 使用命令登录：mysql -u root -p
	-- 创建用户 用户名：canal 密码：Canal@123456
	create user 'canal'@'%' identified by 'Canal@123456';
	-- 授权 *.*表示所有库
	grant SELECT, REPLICATION SLAVE, REPLICATION CLIENT on *.* to 'canal'@'%' identified by 'Canal@123456';
3. 修改mysql配置文件开启log_bin
	docker exec -it 7a3 /bin/bash
	cd /etc/mysql/ 
	复制容器中的mysql配置文件(vim /etc/mysql/mysql.conf.d/mysqld.cnf)
	docker cp hwl_mysql:/etc/mysql/my.cnf /home/my.cnf
	[mysqld]
	# 打开binlog
	log-bin=mysql-bin
	# 选择ROW(行)模式
	binlog-format=ROW
	# 配置MySQL replaction需要定义，不要和canal的slaveId重复
	server_id=1
	配置文件到docker容器中
	docker cp /home/my.cnf mysql:/etc/mysql/my.cnf
	进入容器
	docker exec -it 7a3 /bin/bash
	容器内操作 : service mysql restart
	
	重启容器:
	docker restart 7a3

	# 这里重启配置文件并不生效,猜测my.cnf文件为空,查看文件 docker内安装vim
	这时候需要敲：apt-get update，这个命令的作用是：同步 /etc/apt/sources.list 和 /etc/apt/sources.list.d 中列出的源的索引，这样才能获取到最新的软件包。

    等更新完毕以后再敲命令：apt-get install vim命令即可。
-- 失败

```

7. 重新运行mysql 

```sh
docker run -d -p 3306:3306 --privileged=true -v /docker/mysql/conf/my.cnf:/etc/my.cnf -v /docker/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=bob463491 --name hwl_mysql 697

run　run 是运行一个容器
-d　 表示后台运行
-p　　表示容器内部端口和服务器端口映射关联
--privileged=true　设值MySQL 的root用户权限, 否则外部不能使用root用户登陆
-v /docker/mysql/conf/my.cnf:/etc/my.cnf 将服务器中的my.cnf配置映射到docker中的/docker/mysql/conf/my.cnf配置
-v /docker/mysql/data:/var/lib/mysql　　同上,映射数据库的数据目录, 避免以后docker删除重新运行MySQL容器时数据丢失
-e MYSQL_ROOT_PASSWORD=123456　　　设置MySQL数据库root用户的密码
--name mysql　　　　 设值容器名称为mysql
mysql:5.7　　表示从docker镜像mysql:5.7中启动一个容器
--character-set-server=utf8mb4 --collation-server=utf8mb4_general_ci 设值数据库默认编码
# 把配置输出到指定配置文件
docker exec hwl_mysql bash -c "echo 'log-bin=/var/lib/mysql/mysql-bin' >> /etc/mysql/mysql.conf.d/mysqld.cnf"
docker exec hwl_mysql bash -c "echo 'server-id=1' >> /etc/mysql/mysql.conf.d/mysqld.cnf"
docker exec hwl_mysql bash -c "echo 'binlog-format=ROW' >> /etc/mysql/mysql.conf.d/mysqld.cnf"
docker restart hwl_mysql
log_bin	ON
```

8. 启动canal服务

```sh
tar zxvf canal.deployer-1.1.5-SNAPSHOT.tar.gz
cd cd canal.deployer-1.1.5-SNAPSHOT
sh /bin/startup.sh 

[root@localhost example]# vim example.log 

2020-12-29 20:13:40.290 [main] INFO  c.a.o.c.i.spring.support.PropertyPlaceholderConfigurer - Loading properties file from class path resource [canal.properties]
2020-12-29 20:13:40.293 [main] INFO  c.a.o.c.i.spring.support.PropertyPlaceholderConfigurer - Loading properties file from class path resource [example/instance.properties]
2020-12-29 20:13:40.549 [main] INFO  c.a.o.c.i.spring.support.PropertyPlaceholderConfigurer - Loading properties file from class path resource [canal.properties]
2020-12-29 20:13:40.550 [main] INFO  c.a.o.c.i.spring.support.PropertyPlaceholderConfigurer - Loading properties file from class path resource [example/instance.properties]
2020-12-29 20:13:41.102 [main] INFO  c.a.otter.canal.instance.spring.CanalInstanceWithSpring - start CannalInstance for 1-example
2020-12-29 20:13:41.116 [main] WARN  c.a.o.canal.parse.inbound.mysql.dbsync.LogEventConvert - --> init table filter : ^.*\..*$
2020-12-29 20:13:41.116 [main] WARN  c.a.o.canal.parse.inbound.mysql.dbsync.LogEventConvert - --> init table black filter : ^mysql\.slave_.*$
2020-12-29 20:13:41.326 [main] INFO  c.a.otter.canal.instance.core.AbstractCanalInstance - start successful....
2020-12-29 20:13:41.851 [destination = example , address = /127.0.0.1:3306 , EventParser] WARN  c.a.o.c.p.inbound.mysql.rds.RdsBinlogEventParserProxy - ---> begin to find start position, it will be long time for reset or first position
2020-12-29 20:13:41.965 [destination = example , address = /127.0.0.1:3306 , EventParser] WARN  c.a.o.c.p.inbound.mysql.rds.RdsBinlogEventParserProxy - prepare to find start position mysql-bin.000001:4:1609242878000
2020-12-29 20:13:42.026 [destination = example , address = /127.0.0.1:3306 , EventParser] WARN  c.a.o.c.p.inbound.mysql.rds.RdsBinlogEventParserProxy - ---> find start position successfully, EntryPosition[included=false,journalName=mysql-bin.000001,position=4,serverId=1,gtid=<null>,timestamp=1609242878000] cost : 141ms , the next step is binlog dump
2020-12-29 20:13:43.066 [MultiStageCoprocessor-other-example-0] WARN  c.a.o.canal.parse.inbound.mysql.tsdb.DatabaseTableMeta - dup apply for sql : GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'canal'@'%' IDENTIFIED WITH 'mysql_native_password' AS '*05B6E1A86369DD0753E6936D2F4FC2FD12931DDE'
 成功
```

9.工程引入依赖,监听canal 代码已经上传











































