<!doctype html> <!-- фри маркер, чтобы работать с какими-либо передаваемыми данными -->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>
<h1><b> КАРТЫ </b></h1>
<hr>
<a href="/">На главную</a>
<hr>

<h3> Изменить минуты и трафик интернета</h3>

<table border = "1">
    <tr>
        <th>Id</th>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Email</th>
        <th>Минуты</th>
        <th>Интернет</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.id}</th>
            <th>${user.lastName}</th>
            <th>${user.name}</th>
            <th>${user.email}</th>
            <th>${user.minutes}</th>
            <th>${user.internet}</th>
        </tr>
    <#else> <h4>Юзеров нет.</h4>
    </#list>
</table>
<hr>
<hr>
<table border = "1">
    <tr>
        <th>Id</th>
        <th>Email</th>
        <th>Минуты</th>
        <th>Интернет</th>
        <th>Изменить</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.id}</th>
            <th>${user.email}</th>
            <form action="/decrement/create/${user.id}" method="post">
                <th><input type="number" value="0" name="minutes"></th>
                <th><input type="number" value="0" name="internet" ></th>
                <th><input type="submit" value="Изменить"></th>
            </form>

        </tr>
    <#else> <h4>Таймеров нет.</h4>
    </#list>
</table>


</body>
</html>