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
    <h3 color="darkred">Безопасность.</h3>
    <a href="/">На главную</a>
    <form class="group-wrapper" action="/security/change" method="post" th:object="${user}">
        <br>
        Пароль:
        * <#--${user.password}-->

        <br>
        Данные карты:
        <hr>
        * <input type="text" name="numOfCard" placeholder="${user.numOfCard}">
        <hr>
        * <input  type="text" name="CVC" placeholder="${user.CVC}">
        <hr>
        <input type="submit" value="Изменить">
    </form>
</body>
</html>