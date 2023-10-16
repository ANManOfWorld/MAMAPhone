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
    Hello, MAMAPhone
    <br/>
    <h1> MAMAPhone </h1>
    <h5>Удобное приложение по оплате счетов связи для беспрерывного общения с любимыми людьми. </h5>
    <#list rates as rate>
        <div>
            <p>${rate.name} || ${rate.description} ${rate.price} руб. | <a href = "/rate/${rate.id}">Подробнее</a></p>
        </div>
    </#list>
    <hr>
    <h3> Создать новый тариф</h3>
    <form action="/rate/create" method="post">
        Название тарифа: <input type = "text" name = "name"/> <br>
        Описание тарифа: <input type = "text" name = "descroption"/> <br>
        Цена: <input type = "number" name = "price"/> <br>
        Количество минут: <input type = "number" name = "countOfMinutes"/> <br>
        Количество трафика (в ГБ): <input type = "number" name = "countOfTrafficInternet"/> <br>>
        <input type = "submit" value = "Добавить тариф"/>
    </form>
    </body>
</html>