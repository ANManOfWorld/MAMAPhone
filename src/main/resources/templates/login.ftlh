<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/static/style.css">
    <link rel="stylesheet" href="/static/css/global.css"/>
    <link rel="stylesheet" href="/static/css/index.css"/>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
    />

    <#--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
          crossorigin="anonymous">-->
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
    />
</head>
<body link="#6495ed" vlink="#6495ed" alink="#6495ed">

<#if error??>
    <script>
    alert("${error}");
</script>
</#if>

<div class="rectangle-parent">
    <div class="group-child"></div>
    <section class="laptop-2"></section>
    <div class="parent">
        <img class="icon" alt="" src="/static/img/login_public/-3@2x.png" />
        <b class="b">Авторизация</b>

        <form action="/login" method="post">

            <input class="group-inner" type="email" name="username" placeholder="Email" style="text-align:center"/>

            <input class="group-item" type="password" name="password" placeholder="Пароль" style="text-align:center"/>

            <button class="rectangle-button">
                <div class="div">Авторизоваться</div>
            </button>
        </form>

        <button class="button"><a href="/registration">Регистрация</a></button>


        <button class="custom-button"><a href="/email">Забыл пароль</a></button>

    </div>
</div>
</body>
</html>