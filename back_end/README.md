# 后端

## 如何运行？

运行之前确保已开启本地数据库服务：

```sh
sudo service mysql start
```

然后执行以下指令来执行项目：

```sh
mvn spring-boot:run
```

## 数据库

用用户名和密码登录数据库：

```sh
mysql -u root -p
```

```sql

insert into student(StudentID, Name, Password, Gender, Age, Phone, Email, Address, Training_plan) values(400000000, 'Mike', 'abc', '男', 20, '13766668888', 'mike@zju.edu.cn', 'dom 32 room 101', 'this is mikes training plan...')
```