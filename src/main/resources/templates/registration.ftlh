<!DOCTYPE html>
<html xmlns:th = "http://www.thymeleaf.org">
<head>
    <title>MAMAPhone</title>
</head>
<body>
<h1>MAMAPhone</h1>
<hr>
<a href="/">На главную</a>
<hr>
<h4>Регистрация</h4>
<form action="/registration" method="post" th:object="${user}">
    Имя пользователя: <input type="text" <#--th:field="*{name}"--> name = "name"><br><br>

    <#if errorName??>
        <h3 style = "color: green">
            ${errorName}
        </h3>
    </#if>

   <#-- <div style="color:red" th:if="${#fields.hasErrors("name")}" th:errors="*{name}">
        Name Error
    </div>-->

    Электронная почта: <input type="email" name="email"><br><br>
    <#if errorEmail??>
    <h3 style = "color: green"> ${errorEmail} </h3>
    </#if>

    Номер телефона: <input type="text" name="phoneNum"><br><br>
    <#if errorNum??>
        <h3 style = "color: green"> ${errorNum} </h3>
    </#if>

    Пароль: <input type="password" name="password"><br><br>
    <#if errorPas??>
        <h3 style = "color: green"> ${errorPas} </h3>
    </#if>

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