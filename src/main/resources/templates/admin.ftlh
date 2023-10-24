<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1> MAMAPhone </h1>
<h5>Удобное приложение по оплате счетов связи для беспрерывного общения с любимыми людьми. </h5>
<a href = "/">На главную</a>

<#--<#if user.email??>
    <a><h3>Здравствуйте, админ: ${user.name}</h3>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Выйти"/>
        </form>
    </a>
<#else>
    <a href="/login"><h4>Войти</h4></a>
</#if>-->

<hr>
<br>
<table border = "1">
    <tr>
        <th>Id</th>
        <th>Email</th>
        <th>Активность</th>
        <th>Имя</th>
        <th>Роль</th>
        <th>Бан</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.id}</th>
            <th>${user.email}</th>
            <#--<th>${user.active}</th>-->
            <th><#if user.active>true
                <#else> false
                </#if></th>
            <th>${user.name}</th>
            <#--<th>${user.role}</th>-->
            <th><#list user.roles as role> ${role}<#sep> </#list></th>
            <th><form action="/user/ban/${user.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Забанить!">
                </form>
            </th>
            <#--ДОБАВИТЬ ПОДРОБНУЮ ИНФОРМАЦИЮ О ЮЗЕРЕ-->
            <th><a href="/admin/user/edit/${user.id}">Изменение роли</a></th>
        </tr>
    <#else> <h4>Юзеров нет.</h4>
    </#list>
</table>

</body>
</html>