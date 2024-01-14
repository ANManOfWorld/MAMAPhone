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

<h3> Создать новую карту</h3>
<form action="/card/create" method="post" enctype="multipart/form-data">
    Номер карты: <input type="text" name="numOfCard" placeholder="XXXX-XXXX-XXXX-XXXX"/>
    <hr>
    <br>
    CVC: <input type="text" name="CVC" placeholder="XXX"/>
    <hr>
    <br>
    Баланс: <input type="number" name="balance"/>
    <hr>
    <br>
    <input type="submit" value="Добавить"/>
</form>

<table border="1">
    <tr>
        <th>Id</th>
        <th>Номер карты</th>
        <th>CVC</th>
        <th>Баланс</th>
        <th>Пополнение</th>
        <th>Изменить</th>
        <th>Удалить</th>
    </tr>
    <#list cards as card>
        <tr>
            <th>${card.id}</th>
            <th>${card.numOfCard}</th>
            <th>${card.CVC}</th>
            <th>${card.balance}</th>
            <form action="/card/update/${card.id}" method="post">
                <th><input type="number" name="balance" value="0"></th>
                <th><input type="submit" value="Изменить"></th>
            </form>
            <th>
                <form action="/card/delete/${card.id}" method="post">
                    <input type="submit" value="Удалить">
                </form>
            </th>
        </tr>
    <#else> <h4>Карт нет.</h4>
    </#list>
</table>


</body>
</html>