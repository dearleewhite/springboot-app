1. 安装nohup

```sh

[root@localhost tool]# which nohup-- 查看是否有nohup命令
[root@localhost tool]# yum install coreutils -- 安装
[root@localhost tool]# which nohup --查看命令是否安装成功
/usr/bin/nohup
[root@localhost tool]# vi ~/.bash_profile  -- 修改配置文件
[root@localhost tool]# source ~/.bash_profile  -- 配置文件生效
[root@localhost tool]# nohup --version
```

2. nohup命令运行(后台): nohub 命令 &

```sh
[root@localhost tool]# nohup java -jar springboot-app-1.0-SNAPSHOT.jar > temp.txt &
[1] 3540
-- temp.txt 输出文件, & 后台运行
```

3. 下载并运行arthas

```sh
curl -O https://arthas.aliyun.com/arthas-boot.jar
java -jar arthas-boot.jar
-- 选择黏附的 进程号
-- 进入arthas的界面
-- 反编译代码
[arthas@3811]$ jad --source-only com.mercy.service.impl.TypeServiceImpl > /tmp/TypeServiceImpl.java
-- 修改代码
[arthas@3811]$ vim /tmp/TypeServiceImpl.java
-- 查找类加载器
[arthas@3811]$ sc -d com.mercy.service.impl.TypeServiceImpl  | grep classLoaderHash
 classLoaderHash   439f5b3d
-- 编译修改后的代码
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
-- 编译容易失败,还是使用idea的编译
-- 热更新代码	
[arthas@3811]$ redefine tmp/TypeServiceImpl.class 
redefine success, size: 1, classes:
com.mercy.service.impl.TypeServiceImpl
-- 热更新代码,路径改变并不能生效,只能修改代码逻辑, 即使jad命令看到的路径也不生效 --> 注解是不是都不生效

```

4. 查看java进程, 杀死进程

```sh
ps aux | grep java:查到java的进程
kill -s 9 pid
```











































