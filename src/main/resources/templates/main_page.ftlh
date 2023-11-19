<!doctype html> <!-- фри маркер, чтобы работать с какими-либо передаваемыми данными -->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./global.css" />
    <link rel="stylesheet" href="./index.css" />
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;700&display=swap"
    />
    <title>Document</title>
</head>

<body>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="initial-scale=1, width=device-width" />

    <link rel="stylesheet" href="/static/css/main_page_global.css" />
    <link rel="stylesheet" href="/static/css/main_page_index.css" />
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;700&display=swap"
    />
</head>
<body>


<div class="parent">
    <b class="b">${user.name}</b>
    <div class="div">${user.phoneNum}</div>
    <canvas class="group-child"> </canvas>
    <div class="rectangle-parent">
        <div class="group-item"></div>
        <button class="group-inner"></button>

        <b class="b1">Баланс</b>
        <div class="div1">${user.balance}₽</div>
        <a href = "/top_up_balance">
            <button class="group-inner"><div class="div2">Пополнить</div></button>  <#--ДОБАВИТЬ ССЫЛКУ-->
        </a>
    </div>
    <div class="rectangle-group">
        <div class="rectangle-div"></div>
        <b class="b2">${user.name}</b>
        <div class="div3">${user.phoneNum}</div>
    </div>
    <button class="rectangle-container">
        <a href="/callsFinance">
        <div class="group-child1"></div>
        <div class="div4">Детализированные отчёты</div>
        <b class="b3">Звонки и финансы</b>
        <div class="ellipse-div"></div>
        <img
                class="telephone-call-1-icon"
                alt=""
                src="/static/img/main_page_public/telephonecall-1@2x.png"
        />
        </a>
    </button>
    <button class="group-button">
        <a href="/changeRate">
        <div class="group-child1"></div>
        <div class="div4">Настройте связь под себя</div>
        <b class="b3">Сменить тариф</b>
        <div class="ellipse-div"></div>
        <img class="exchange-1-icon" alt="" src="/static/img/main_page_public/exchange-1@2x.png" />
        </a>
    </button>
    <button class="vector-parent">
        <a href="/settings">
        <img class="rectangle-icon" alt="" src="/static/img/main_page_public/rectangle-24.svg" />
        <div class="div4">Управление вашим профилем</div>
        <b class="b3">Настройки</b>
        <div class="ellipse-div"></div>
        <img class="cogwheel-1-icon" alt="" src="/static/img/main_page_public/cogwheel-1@2x.png" />
        </a>
    </button>
    <div class="group">
        <b class="b6">Минуты и Гигабайты</b>
        <div class="div7">на 9 дней</div>
        <div class="div8">на 9 дней</div>
        <div class="div9">${user.minutes} мин</div>
        <div class="group-child5"></div>
        <div class="div10">${user.internet} Гб</div>
        <div class="group-child6"></div>
        <div class="group-child7"></div>
        <div class="group-child8"></div>
    </div>
    <img class="user-1-icon" alt="" src="/static/img/main_page_public/user-1@2x.png" />


    <#--ДОБАВИТЬ ССЫЛКУ-->
    <button class="chat-1"></button>

    <#if user.isAdmin()>
    <#--ДОБАВИТЬ ССЫЛКУ--><a href = "/billing"><button class="free-icon-control-panel-233339"></button></a>

    </#if>
    <#--ДОБАВИТЬ ССЫЛКУ-->
    <button class="free-icon-bell-1157000-1"></button>

    <#--ДОБАВИТЬ ССЫЛКУ-->
    <form action="/logout" method="post">
        <#--<input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
        <input type="submit" value="Выйти"/>
        <button class="rectangle-button">
            <img class="free-icon-exit-1286969-1" src="/static/img/main_page_public/freeiconexit1286969-1_2x.png"/>
        </button>
    </form>


</div>



<#--<div>
    <a>
        <h3>Здравствуйте, ${user.name}</h3>
        <hr>
        ${user.phoneNum}
        <hr>
        <br>
        <form action="/logout" method="post">
            &lt;#&ndash;<input type="hidden" name="_csrf" value="${_csrf.token}"/>&ndash;&gt;
            <input type="submit" value="Выйти"/>
        </form>
    </a>
</div>
<div>
    <a href="/callsFinance">
        <h3>Звонки и финансы </h3>
    </a>
</div>

<div>
    <a href="/changeRate">
        <h3>Cменить тариф</h3>
        &lt;#&ndash;Настройки связи под себя&ndash;&gt;
    </a>
</div>

<div>
    <a href="/settings">
        <h3>Настройки</h3>
        Управление Вашим профилем
    </a>
</div>

<#if user.isAdmin()>
    <a href="/admin">Панель администратора</a>
    <hr>
</#if>-->

</body>
</html>
