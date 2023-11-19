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
<h2>Пополнение баланса.</h2>

<a href="/">На главную</a>

<form action="/registration" method="post">
    Номер карты: ${user.numOfCard}
    CVV/CVC: ${user.CVC}
    Пароль: <input type="number" name="balance"><br><br>
    <#-- <input type="hidden" name="_csrf" value="${_csrf.token}">--> <#-- Защита приложения от "взлома".
                                                                Токен создаёт уникальный ключ,
                                                                который проверяется SPR.SECURITY. -->
    <input type="submit" value="Оплатить"/>
</form>

</body>
</html>