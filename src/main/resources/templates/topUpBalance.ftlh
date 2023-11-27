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
    Номер карты: <input <#--class="group-child3"--> type="text" name="numOfCard" value="${user.numOfCard}" placeholder="${user.numOfCard}"></input>
    <#if errorNum??>
        <script>
            alert("${errorNum}");
        </script>
    </#if>
    <hr>
    CVV/CVC: <input <#--class="group-child3"--> type="text" name="CVC" value="${user.CVC}" placeholder="${user.CVC}"></input>
    <#if errorCVC??>
        <script>
            alert("${errorCVC}");
        </script>
    </#if>
    <hr>
    Сумма: <input type="number" name="balance" value="0" <#--placeholder=" ${user.balance}"-->> Руб.<br><br>
    <#if errorBalance??>
        <script>
            alert("${errorBalance}");
        </script>
    </#if>
    <#if errorBalanceHigh??>
        <script>
            alert("${errorBalanceHigh}");
        </script>
    </#if>
    <input type="submit" value="Оплатить"/>
</form>

<#if errorAnswerOfTransaction??>
    <script>
        alert("${errorAnswerOfTransaction}");
    </script>
</#if>

<#if answerUserTopUpBalance??>
    <script>
        alert("${errorAnswerUserTopUpBalance}");
    </script>
</#if>

</body>
</html>