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

<form action="/top_up_balance" method="post">
    Номер карты: <input <#--class="group-child3"--> type="text" name="numOfCard" placeholder="${user.numOfCard}"></input>
    <hr>
    CVV/CVC: <input <#--class="group-child3"--> type="text" name="CVC" placeholder=" ${user.CVC}"></input>
    <hr>
    Сумма: <input type="number" name="balance" placeholder=" ${user.balance}"><br><br>
    <input type="submit" value="Оплатить"/>
</form>

</body>
</html>