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
<h5>Информация о товаре. </h5>

<b>Название тарифа: </b>${rate.name}
<br>
<b>Описание тарифа: </b>${rate.description}
<br>
<b>Количество минут тарифа: </b>${rate.countOfMinutes}
<br>
<b>Количество интернет трафика тарифа: </b>${rate.countOfTrafficInternet}
<br>
<b>Цена тарифа: </b>${rate.price}

<hr>
<form action="/rate/delete/${rate.id}" method="post">
   <input type = "submit" value = "Удалить тариф"/>
</form>
</body>
</html>