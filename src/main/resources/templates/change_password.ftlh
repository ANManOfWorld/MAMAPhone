<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="/static/css/change_password_global.css"/>
    <link rel="stylesheet" href="/static/css/change_password_index.css"/>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
    />


</head>
<body>

<div class="rectangle-parent">
    <div class="group-child"></div>

    <div class="custom">
        <form action="/change_password" method="post">
            <div class="group-wrapper">
                <div class="rectangle-group">
                    <input class="group-item" type="text" name="oldPassword">

                    <input class="group-inner" type="text" name="newPassword"/>

                    <div class="div">Старый пароль</div>
                    <div class="div1">Новый пароль</div>
                    <b class="b">Смена пароля</b>
                </div>
            </div>

            <button class="group-child1">
                <div class="div5">Изменить</div>
            </button>

        </form>

        <#if errorAnswerOfPassword??>
            <script>
                alert("${errorAnswerOfPassword}");
            </script>
        </#if>


        <div class="rectangle-div">
            <b class="b1">${user.name}</b>
            <div class="div4">${user.phoneNum}</div>
        </div>
        <img class="user-3-icon" alt="" src="/static/img/change_password_public/user-3@2x.png"/>

    </div>

    <a href="/security">
        <button class="rectangle-button">
            <img class="arrow-icon" alt="" src="/static/img/change_password_public/arrow-3.svg"/>
        </button>
    </a>

</div>
</body>
</html>