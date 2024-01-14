<!doctype html> <!-- фри маркер, чтобы работать с какими-либо передаваемыми данными -->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="/static/css/email_password_index.css"/>
    <link rel="stylesheet" href="/static/css/email_password_global.css"/>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
    />

</head>
<#if errorEmailPas??>
    <script>
        alert("${errorEmailPas}");
    </script>
</#if>
<body>

<div class="rectangle-parent">
    <div class="group-child"></div>
    <section class="laptop-2"></section>
    <div class="parent">

        <form action="/email/send" method="post">
            <img class="icon" alt="" src="/static/img/email_password/-3@2x.png"/>
            <b class="b">Забыл пароль</b>
            <input class="group-item" type="email" name="email" placeholder="Email"/>
            <button type="submit" class="group-inner">
                <div class="div">Восстановить</div>
            </button>
        </form>


        <a href="/login">
            <button class="button">На главную</button>
        </a>
    </div>
</div>
</body>
</html>