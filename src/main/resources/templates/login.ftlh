<!DOCTYPE html>
<html>
<head>
    <title>MAMAPhone</title>
</head>
<body>
<h1>MAMAPhone</h1><hr>
<h4>Авторизация</h4>
<form <#--th:action="@{/login}"-->action="/login" method="post">
    Эл. почта: <input type="email" name="username"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Вход"/>
</form>
<br>
<a href="/registration">Зарегистрироваться</a>
</body>
</html>

<#--
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
<head>
    <title>MAMAPhone</title>
</head>
<body>
<div th:if="${param.error}">
    Неверное имя пользователя и пароль.
</div>
<div th:if="${param.logout}">
    Вы вышли из системы.
</div>
<form th:action="@{/login}" method="post">
    <div><label> Электронная почта: <input type="email" name="userName"/> </label></div> &lt;#&ndash; username - просит spring.security&ndash;&gt;
    <div><label> Пароль: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Войти"/></div>
</form>
</body>
</html>-->
