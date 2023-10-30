<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/static/global.css">

    <title>MAMAPhone</title>
    <style>
        body {background-color: aquamarine}
        group-child {
            top: 0;
            left: 1px;
            background-color: var(--color-black);
            width: 1290px;
            height: 1080px;
        }
        button,
        group-child,
        icon {
            position: absolute;
        }
        icon {
            top: 1px;
            left: 0;
            width: 1291px;
            height: 1080px;
            object-fit: cover;
        }
        .button {
            cursor: pointer;
            border: 0;
            padding: 0;
            background-color: transparent;
            top: 456px;
            left: 120px;
            font-size: 25px;
            font-family: var(--font-inter);
            color: #438bf9;
            text-align: left;
            display: inline-block;
            width: 162px;
        }
        .group-item {
            position: absolute;
            top: 0;
            left: 0;
            border-radius: var(--br-24xl);
            background-color: var(--color-white);
            width: 401px;
            height: 57px;
        }
        .input {
            border: 0;
            font-family: var(--font-inter);
            font-size: var(--font-size-8xl);
            background-color: transparent;
            position: absolute;
            top: 12px;
            left: 27px;
            color: var(--color-black);
            text-align: left;
            display: inline-block;
            width: 343px;
        }
        .rectangle-group {
            position: absolute;
            top: 196px;
            left: 0;
            width: 401px;
            height: 57px;
        }
        .h1 {
            margin: 0;
            position: absolute;
            top: 131px;
            left: 161px;
            font-size: 32px;
            font-weight: 700;
            font-family: var(--font-inter);
            color: var(--color-white);
            text-align: left;
            display: inline-block;
            width: 80px;
        }
        .rectangle-button,
        .rectangle-container {
            position: absolute;
            top: 285px;
            left: 0;
            width: 401px;
            height: 57px;
        }
        .rectangle-button {
            cursor: pointer;
            border: 0;
            padding: 0;
            background-color: #06f2b1;
            top: 0;
            border-radius: var(--br-24xl);
        }
        .div {
            position: absolute;
            top: 11px;
            left: 165px;
            font-size: var(--font-size-8xl);
            font-family: var(--font-inter);
            color: var(--color-black);
            text-align: left;
            display: inline-block;
            width: 80px;
        }
        .group-div {
            top: 374px;
            left: 0;
            width: 401px;
            height: 57px;
        }
        .group-div,
        .icon1,
        .parent {
            position: absolute;
        }
        .icon1 {
            top: 0;
            left: 90px;
            width: 222px;
            height: 140px;
            object-fit: cover;
        }
        .parent {
            top: 298px;
            left: 445px;
            width: 401px;
            height: 486px;
        }
        .rectangle-parent {
            position: relative;
            width: 100%;
            height: 1081px;
        }

    </style>
</head>
<body>
<h1>MAMAPhone</h1><hr>
<h4>Авторизация</h4>
<form <#--th:action="@{/login}"-->action="/login" method="post">
    Эл. почта: <input type="email" name="username"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    <#--<input type="hidden" name="_csrf" value="${_csrf.token}">-->
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
