<%--
  Created by IntelliJ IDEA.
  User: 白小兮
  Date: 2021/9/7
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="r" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${message}<br>
<r:hasPermission name="bookmanager:book:query">
    查询哦
</r:hasPermission>
<r:hasPermission name="bookmanager:book:add">
    新增
</r:hasPermission>
<r:hasPermission name="bookmanager:book:update">
    修改
</r:hasPermission>
<a href="/ssmshiro/handleUser/godel">删除用户</a><br>
增加<br>
<form action="/ssmshiro/handleUser/add" method="post">
    账号：<input type="text" name="username"/><br/>
    密码：<input type="password" name="password"/><br/>
    <input type="submit"/>
</form>
</body>
</html>
