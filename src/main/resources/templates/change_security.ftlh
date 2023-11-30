<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="/static/css/change_security_global.css"/>
    <link rel="stylesheet" href="/static/css/change_security_index.css"/>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
    />


</head>
<body>
<div class="rectangle-parent">
    <div class="group-child"></div>

    <div class="custom">

        <form action="/security/change" method="post" th:object="${user}">
            <button class="group-child1">
                <div class="div4">Изменить</div>
            </button>
            <div class="group-wrapper">
                <div class="rectangle-group">


                        <input class="group-item" type="text" name="numOfCard" placeholder="${user.numOfCard}">
                        <#if errorNum??>
                            <script>
                                alert("${errorNum}");
                            </script>
                        </#if>

                        <input class="group-inner" type="text" name="CVC" placeholder="${user.CVC}">
                        <#if errorCVC??>
                            <script>
                                alert("${errorCVC}");
                            </script>
                        </#if>


                        <div class="div">Номер карты</div>
                        <div class="cvvcvc">CVV/CVC</div>
                        <b class="b">Смена карты</b>
                </div>
            </div>
        </form>

        <div class="rectangle-div">
            <b class="b1">${user.name} ${user.lastName}</b>
            <div class="div3">${user.phoneNum}</div>
        </div>
        <img class="user-3-icon" alt="" src="/static/img/change_security_public/user-3@2x.png"/>





        <form action="/security/delete" method="post">
            <button class="group-child2">
                <div class="div5">Удалить</div>
            </button>
        </form>
    </div>


    <a href="/security">
        <button class="rectangle-button">
            <img class="arrow-icon" alt="" src="/static/img/change_security_public/arrow-3.svg"/>
        </button>
    </a>

</div>


<#--
<h1> MAMAPhone </h1>
<h3 color="darkred">Безопасность.</h3>

<a href="/security">На главную</a>

<form action="/security/change" method="post" th:object="${user}">
    <br>
    Данные карты:
    <hr>
    * <input type="text" name="numOfCard" placeholder="${user.numOfCard}">
    <#if errorNum??>
        <script>
            alert("${errorNum}");
        </script>
    </#if>
    <hr>
    * <input type="text" name="CVC" placeholder="${user.CVC}">
    <#if errorCVC??>
        <script>
            alert("${errorCVC}");
        </script>
    </#if>
    <hr>
    <input type="submit" value="Изменить">
</form>


<form action="/security/delete" method="post">
    <input type="submit" value="Удалить">
</form>
-->


</body>
</html>