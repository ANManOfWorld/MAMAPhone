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
<h1> MAMAPhone </h1>
<h5>Удобное приложение по оплате счетов связи для беспрерывного общения с любимыми людьми. </h5>

<#if user.email??>
    <a><h3>Здравствуйте, ${user.name}</h3>
        <hr>
        <#if user.isAdmin()>
            <a href="/admin">Панель администратора</a>
            <hr>
        </#if>

        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Выйти"/>
        </form>
    </a>
<#else>
    <a href="/login"><h4>Войти</h4></a>
    <hr>
</#if>
<br>
<br>
<form action="/" method="get">
    Поиск тарифов: <input type="text" name="name"> <br>
    <input type="submit" value="Поиск">
</form>

<hr>
<#list rates as rate>
    <div>
        <p>${rate.name} || ${rate.description} ${rate.price} руб. | <a href="/rate/${rate.id}">Подробнее</a></p>
    </div>
</#list>
<hr>


<#if user.isAdmin()>                                                                    <#--user.email??-->
    <h3> Создать новый тариф</h3>
    <form action="/rate/create" method="post" enctype="multipart/form-data">
        <!-- "enctype="multipart/form-data"  " атрибут для фото-->
        Название тарифа: <input type="text" name="name"/> <br>
        Описание тарифа: <input type="text" name="description"/> <br>
        Цена: <input type="number" name="price"/> <br>
        Количество минут: <input type="number" name="countOfMinutes"/> <br>
        Количество трафика (в ГБ): <input type="number" name="countOfTrafficInternet"/> <br>

        <!-- фотография -->
        Фотография: <input type="file" name="file1"/> <br>
        <!-- фотография -->

        <input type="submit" value="Добавить тариф"/>
    </form>
</#if>


</body>
</html>