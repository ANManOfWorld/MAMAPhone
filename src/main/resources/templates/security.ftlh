<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/static/css/security_global.css"/>
    <link rel="stylesheet" href="/static/css/security_index.css"/>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
    />


</head>
<body>

<div class="rectangle-parent">
    <div class="group-child"></div>

    <div class="custom">
        <div class="rectangle-group">
            <input class="group-item" type="text" value="${user.numOfCard}" readonly="readonly"/>

            <input class="group-inner" type="text" value="${user.CVC}" readonly="readonly"/>

            <div class="div">Пароль</div>
            <a href="/change_password">
                <button class="group-child2">
                    <div class="div6">Изменить пароль</div>
                </button>
            </a>
            <div class="div1">Номер карты</div>
            <div class="cvvcvc">CVV/CVC</div>
            <b class="b">Безопасность</b>
        </div>

        <div class="rectangle-div">
            <b class="b1">${user.name}</b>
            <div class="div4">${user.phoneNum}</div>
        </div>

        <img class="user-3-icon" alt="" src="/static/img/security_public/user-3@2x.png"/>

        <a href="/change_security">
            <button class="group-child1">
                <div class="div5">Изменить карту</div>
            </button>
        </a>
    </div>

    <a href="/settings">
        <button class="rectangle-button">
            <img class="arrow-icon" alt="" src="/static/img/security_public/arrow-2.svg"/>
        </button>
    </a>
</div>
</body>
</html>