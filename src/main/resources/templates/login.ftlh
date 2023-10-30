<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/static/style.css">
    <link rel="stylesheet" href="/static/css/global.css"/>
    <link rel="stylesheet" href="/static/css/index.css"/>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
    />

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
          crossorigin="anonymous">
</head>
<body>

<#--<div class="container mt-4">-->
    <div class="rectangle-parent">
        <section class="group-child"></section>
        <img class="icon" alt="" src="/static/img/89560-2@2x.png"/>
        <form class="parent" action="/login" method="post">
            <h1 class="h1">Вход</h1>
            <div class="rectangle-group">
                <div class="group-item"></div>
                <input class="input" type="email" name="username">
            </div>

            <div class="rectangle-container">
                <div class="group-item"></div>
                <input class="input" type="password" name="password">
            </div>

            <div class="group-div">
                <button class="rectangle-button"><input class="rectangle-button" type="submit" value=""/>
                    <div class="div">Войти</div>
                </button>
            </div>
            <button class="button"><a href="/registration">Зарегистрироваться</a></button>
            <img class="icon1" alt="" src="/static/img/-3@2x.png"/>

        </form>
    </div>
<#--</div>-->
</body>
</html>


<#--<div class="coco">
<h1>MAMAPhone</h1><hr>
<h4>Авторизация</h4>
    </div>
<form &lt;#&ndash;th:action="@{/login}"&ndash;&gt;action="/login" method="post">-->

<#--Эл. почта: <input type="email" name="username"><br><br>
Пароль: <input type="password" name="password"><br><br>
&lt;#&ndash;<input type="hidden" name="_csrf" value="${_csrf.token}">&ndash;&gt;
<input type="submit" value="Вход"/>-->

<#--</form>
<br>
<a href="/registration">Зарегистрироваться</a>-->


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
