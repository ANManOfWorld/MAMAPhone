<!DOCTYPE html>
<html>
<head>
    <title>MAMAPhone</title>
</head>
<body>
<h1>MAMAPhone</h1>
<hr>
<a href="/">На главную</a>
<hr>
<h4>Регистрация</h4>
<form action="/registration" method="post">
    Имя пользователя: <input type="text" name="name"><br><br>
    Электронная почта: <input type="email" name="email"><br><br>
    Номер телефона: <input type="text" name="phoneNum"><br><br>
    Пароль: <input type="password" name="password"><br><br>
   <#-- <input type="hidden" name="_csrf" value="${_csrf.token}">--> <#-- Защита приложения от "взлома".
                                                                Токен создаёт уникальный ключ,
                                                                который проверяется SPR.SECURITY. -->
    <input type="submit" value="Зарегистрироваться"/>
</form>
<#if errorMessage??>
    <h3 style = "color: green">${errorMessage}</h3>
</#if>
</body>
</html>