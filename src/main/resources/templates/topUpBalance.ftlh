<!doctype html> <!-- фри маркер, чтобы работать с какими-либо передаваемыми данными -->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="/static/css/topupbalance_global.css"/>
    <link rel="stylesheet" href="/static/css/topupbalance_index.css"/>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
    />

</head>

<body>

<div class="rectangle-parent">
    <div class="group-child"></div>

    <div class="custom">
        <form action="/top_up_balance" method="post">
            <input class="group-item" type="text" name="numOfCard" value="${user.numOfCard}" placeholder="${user.numOfCard}">
            <#if errorNum??>
                <script>
                    alert("${errorNum}");
                </script>
            </#if>

            <input class="group-inner" type="text" name="CVC" value="${user.CVC}" placeholder="${user.CVC}">
            <#if errorCVC??>
                <script>
                    alert("${errorCVC}");
                </script>
            </#if>

            <input class="rectangle-input"  type="number" name="balance" value="0" <#--placeholder=" ${user.balance}"-->>
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

            <button class="group-child1">
                <div class="div8">Оплатить</div>
            </button>

        </form>

        <div class="div">Номер карты</div>
        <div class="cvvcvc">CVV/CVC</div>
        <div class="div1">Сумма</div>
        <div class="div2">Руб.</div>
        <b class="b">Пополнение баланса</b>


        <div class="rectangle-div">
            <b class="b1">${user.name}</b>
            <div class="div7">${user.phoneNum}</div>
        </div>
        <img class="user-4-icon" alt="" src="/static/img/topupbalance_public/user-4@2x.png"/>


    </div>


    <a href="/">
        <button class="rectangle-button">
            <img class="arrow-icon" alt="" src="/static/img/topupbalance_public/arrow-5.svg"/>
        </button>
    </a>

</div>
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




<#--<h1> MAMAPhone </h1>
<h2>Пополнение баланса.</h2>

<a href="/">На главную</a>

<form action="/top_up_balance" method="post">
    Номер карты: <input &lt;#&ndash;class="group-child3"&ndash;&gt; type="text" name="numOfCard" value="${user.numOfCard}"
                                                    placeholder="${user.numOfCard}"></input>
    <#if errorNum??>
        <script>
            alert("${errorNum}");
        </script>
    </#if>
    <hr>
    CVV/CVC: <input &lt;#&ndash;class="group-child3"&ndash;&gt; type="text" name="CVC" value="${user.CVC}"
                                                placeholder="${user.CVC}"></input>
    <#if errorCVC??>
        <script>
            alert("${errorCVC}");
        </script>
    </#if>
    <hr>
    Сумма: <input type="number" name="balance" value="0" &lt;#&ndash;placeholder=" ${user.balance}"&ndash;&gt;> Руб.<br><br>
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
</#if>-->

</body>
</html>