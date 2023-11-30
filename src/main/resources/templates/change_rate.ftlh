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


<hr>
<form action="/changeRate/next" method="post" >
    <input type="submit" name="next">NEXT</input>
</form>

<hr>
<hr>

<form action="/changeRate/pre" method="post" >
    <input type="submit" name="pre">PRE</input>
</form>
<hr>

<#--<a href="/rate/${rate.id}">Вперёд</a>

<a href="/rate/${rate.id}">Назад</a>-->

<#--<#list rates as rate>-->
    <div>
        <b>Название тарифа: </b>${rate.name}
        <br>
        <b>Описание тарифа: </b>${rate.description}
        <br>
        <b>Количество минут тарифа: </b>${rate.countOfMinutes}
        <br>
        <b>Количество интернет трафика тарифа: </b>${rate.countOfTrafficInternet}
        <br>
        <b>Цена тарифа: </b>${rate.price}


        <form action="/chooseRate/${rate.id}" method="post">
            <input type="submit" value="Подключить"/>
        </form>


    </div>
<#--</#list>

<hr>
<#list rates as rate>
    <div>
        <p>${rate.name} || ${rate.description} ${rate.price} руб. | <a href="/rate/${rate.id}">Подробнее</a></p>
    </div>
</#list>-->

</body>
</html>