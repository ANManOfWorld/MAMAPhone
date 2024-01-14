<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/static/css/settings_global.css"/>
    <link rel="stylesheet" href="/static/css/settings_index.css"/>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
    />
    <title>Document</title>
</head>
<body>
<div class="rectangle-parent">
    <div class="group-child"></div>

    <div class="custom">
        <a href="/appSettings">
            <button class="group-item">
                <b class="b">Приложение</b>
                <img class="free-icon-setting-5064298-1" alt=""
                     src="/static/img/settings_public/freeiconsetting5064298-1@2x.png"/>
            </button>
        </a>

        <a href="/security">
            <button class="rectangle-button">
                <b class="b1">Безопасность</b>
                <div class="group-child2">
                    <img class="free-icon-shield-8017361-1" alt="" src="/static/img/settings_public/freeiconshield8017361-1@2x.png"/>
                </div>
            </button>
        </a>

        <a href="/personalData">
            <button class="group-child1">
                <b class="b2">Личные данные</b>
                <img class="ellipse-icon" alt="" src="/static/img/settings_public/ellipse-15@2x.png"/>
            </button>
        </a>

        <div class="rectangle-div">
            <b class="b3">${user.name}</b>
            <div class="div">${user.phoneNum}</div>
        </div>
        <img class="user-3-icon" alt="" src="/static/img/settings_public/user-3@2x.png"/>
    </div>

    <a href="/">
        <button class="group-child3">
            <img class="arrow-icon" alt="" src="/static/img/settings_public/arrow-1.svg"/>
        </button>
    </a>
</div>
</body>
</html>