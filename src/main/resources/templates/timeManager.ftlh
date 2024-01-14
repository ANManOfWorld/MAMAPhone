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
<h1><b> Таймер Тарифов. </b></h1>
<hr>
<a href="/">На главную</a>
<hr>

<table border = "1">
    <tr>
        <th>Id</th>
        <th>Секунды</th>
        <th>Минуты</th>
        <th>Часы</th>
        <th>Дни</th>
        <th>Месяца</th>
    </tr>
    <#list timeManagers as time>
        <tr>
            <th>${time.id}</th>
            <th>${time.seconds}</th>
            <th>${time.minutes}</th>
            <th>${time.hours}</th>
            <th>${time.days}</th>
            <th>${time.month}</th>
        </tr>
    <#else> <h4>Таймеров нет.</h4>
    </#list>
</table>
<hr>
<hr>
<table border = "1">
    <tr>
        <th>Id</th>
        <th>Секунды</th>
        <th>Минуты</th>
        <th>Часы</th>
        <th>Дни</th>
        <th>Месяца</th>
        <th>Изменить</th>
    </tr>
    <#list timeManagers as time>
        <tr>
            <th>${time.id}</th>
            <form action="/timeManager/create/${time.id}" method="post">
                <th><input type="number" value="${time.seconds}" name="seconds" ></th>
                <th><input type="number" value="${time.minutes}" name="minutes" ></th>
                <th><input type="number" value="${time.hours}" name="hours" ></th>
                <th><input type="number" value="${time.days}" name="days" ></th>
                <th><input type="number" value="${time.month}" name="month" ></th>
                <th><input type="submit" value="Изменить"></th>
            </form>

        </tr>
    <#else> <h4>Таймеров нет.</h4>
    </#list>


</table>
</body>
</html>
