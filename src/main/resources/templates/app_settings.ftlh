<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="initial-scale=1, width=device-width"/>

    <link rel="stylesheet" href="/static/css/app_settings_global.css"/>
    <link rel="stylesheet" href="/static/css/app_settings_index.css"/>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
    />
</head>
<body>
<div class="group-parent">
    <div class="rectangle-parent">
        <div class="group-child"></div>

        <div class="custom">
            <b class="b">Приложение</b>
        </div>

        <div class="div">Уведомления о новых счетах</div>
        <div class="div1">Предупреждения о превышении лимита</div>
    </div>
    <div class="custom">

        <form action="/appSettings/change" method="post">
            <#if user.warningOfPayment == true>
                <input name="paymentTime" class="group-item" type="checkbox" checked/>
            <#else>
                <input name="paymentTime" class="group-item" type="checkbox" />
            </#if>

            <#if user.warningOfResources == true>
                <input class="group-inner" name="resources" type="checkbox" checked/>
            <#else>
                <input class="group-inner" name="resources" type="checkbox" />
            </#if>

           <#-- <input name="paymentTime" class="group-item" type="checkbox"/>-->
            <#--<input name="resources" class="group-inner" type="checkbox"/>-->

            <button  type="submit" class="group-child2">
                <div class="div3">Изменить</div>
            </button>
        </form>

    </div>


    <div class="custom">
        <div class="group-child1">
            <b class="b1">${user.name}</b>
            <div class="div2">${user.phoneNum}</div>
        </div>
        <img class="user-3-icon" alt="" src="/static/img/app_settings/user-3@2x.png"/>
    </div>

    <a href="/settings">
        <button class="rectangle-button">
            <img class="arrow-icon" alt="" src="/static/img/app_settings/arrow-4.svg"/>
        </button>
    </a>
</div>
</body>
</html>
