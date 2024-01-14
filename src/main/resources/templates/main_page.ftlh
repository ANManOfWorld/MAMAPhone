<!doctype html> <!-- фри маркер, чтобы работать с какими-либо передаваемыми данными -->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./global.css"/>
    <link rel="stylesheet" href="./index.css"/>
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
    <meta charset="utf-8"/>
    <meta name="viewport" content="initial-scale=1, width=device-width"/>

    <link rel="stylesheet" href="/static/css/main_page_global.css"/>
    <link rel="stylesheet" href="/static/css/main_page_index.css"/>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;700&display=swap"
    />
</head>
<body>

<#if changeInformationRateFlag??>
    <script>
        alert("Условия тарифа были изменены. Вы были отключены от тарифа. Чтобы пользоваться услугами, выберите новый тариф.");
    </script>
</#if>

<#if WarningOfResources == true && ConditionOfResources == true>
    <script>
        alert("Ваш пакет частично/полностью исчерпан.");
    </script>
</#if>

<#if WarningOfPayment == true && ConditionOfPayment == true>
    <script>
        alert("На Вашем балансе недостаточно средств для следующего списания. Пополните Ваш счёт.");
    </script>
</#if>


<div class="parent">
    <div class="group-child"></div>

    <div class="custom">
        <div class="group-item"></div>
        <button class="group-inner">
            <a href="/top_up_balance">
                <divf class="div2">Пополнить</divf>
            </a>
        </button>

        <div id="block1">
            <b class="b1">Баланс</b>
            <div id="block" class="div1">${user.balance}₽</div>
            <div class="dark-ring1"></div>
            <div class="blue-ring" style="--value:${internetOfPercent}">
                <div class="div10"></div>
            </div>
            <div class="dark-ring2"></div> <#--ДОПИСАТЬ-->
            <div class="green-ring" style="--value:${minutesInPercent}">
                <div class="div9"></div> <#--ДОПИСАТЬ-->
            </div>


            <div class="rectangle-div"></div>
            <b class="b2">${user.name}</b>
            <div class="div3">${user.phoneNum}</div>
            <img class="user-1-icon" alt="" src="/static/img/main_page_public/user-1@2x.png"/>

            <a href="/callsFinance">
                <button class="rectangle-button">
                    <b class="b3">Звонки и финансы</b>
                    <div class="div4">Детализированные отчёты</div>
                    <img class="telephone-call-1-icon" alt=""
                         src="/static/img/main_page_public/telephonecall-1@2x.png"/>
                </button>
            </a>

            <a href="/changeRate">
                <button class="group-child4">
                    <b class="b4">Сменить тариф</b>
                    <div class="div5">Настройте связь под себя</div>
                    <img class="exchange-1-icon" alt="" src="/static/img/main_page_public/exchange-1@2x.png"/>
                </button>
            </a>

            <a href="/settings">
                <button class="group-child5">
                    <b class="b5">Настройки</b>
                    <div class="div6">Управление вашим профилем</div>
                    <img class="cogwheel-1-icon" alt="" src="/static/img/main_page_public/cogwheel-1@2x.png"/>
                </button>
            </a>
            <div class="div7">${user.minutes} мин</div>
            <div class="div8">${user.internet} Гб</div>
        </div>
    </div>


    <form action="/logout" method="post">
        <input type="submit" value="Выйти"/>
        <button class="group-child6">
            <img class="free-icon-exit-1286969-1" alt=""
                 src="/static/img/main_page_public/freeiconexit1286969-1@2x.png"/>
        </button>
    </form>

    <a href="/appSettings">
        <button class="free-icon-bell-1157000-1"></button>
    </a>

    <#if (user.isModerator() && user.isAdmin())>
        <a href="/billing">
            <button class="free-icon-control-panel-233339"></button>
        </a>
    <#elseif (user.isModerator())>
        <a href="/billing">
            <button class="free-icon-control-panel-233339"></button>
        </a>
    <#elseif (user.isAdmin() && user.isModerator())>
        <a href="/billing">
            <button class="free-icon-control-panel-233339"></button>
        </a>
    <#elseif user.isAdmin()>
        <a href="/admin">
            <button class="free-icon-control-panel-233339"></button>
        </a>
    </#if>


    <button id="myButton" class="chat-1"></button>


    <script>


        const callback = () => {
            alert("Наша эл. почта для связи: maximumega@yandex.ru");
        };
        const button = document.querySelector('#myButton');
        button.addEventListener('click', callback);


        (async function () {

            var selector = '#block'; // Селектор блока, который надо обновлять

            while (true) {

                await new Promise(function (s) {
                    setTimeout(s, 1 * 1000);
                }); // Каждые 30 сек.

                try {
                    var html = await (await fetch(location.href)).text();
                    var newdoc = new DOMParser().parseFromString(html, 'text/html');
                    document.querySelector(selector).outerHTML = newdoc.querySelector(selector).outerHTML;
                    console.log('Элемент ' + selector + ' был успешно обновлен');
                } catch (err) {
                    console.error('При обновлении элемента ' + selector + ' произошла ошибка:', err);
                }

            }

        })();

        (async function () {

            var selector = '#block1'; // Селектор блока, который надо обновлять

            while (true) {

                await new Promise(function (s) {
                    setTimeout(s, 1 * 1000);
                }); // Каждые 30 сек.

                try {
                    var html = await (await fetch(location.href)).text();
                    var newdoc = new DOMParser().parseFromString(html, 'text/html');
                    document.querySelector(selector).outerHTML = newdoc.querySelector(selector).outerHTML;
                    console.log('Элемент ' + selector + ' был успешно обновлен');
                } catch (err) {
                    console.error('При обновлении элемента ' + selector + ' произошла ошибка:', err);
                }

            }

        })();

        (async function () {

            var selector = '#block2'; // Селектор блока, который надо обновлять

            while (true) {

                await new Promise(function (s) {
                    setTimeout(s, 1 * 1000);
                }); // Каждые 30 сек.

                try {
                    var html = await (await fetch(location.href)).text();
                    var newdoc = new DOMParser().parseFromString(html, 'text/html');
                    document.querySelector(selector).outerHTML = newdoc.querySelector(selector).outerHTML;
                    console.log('Элемент ' + selector + ' был успешно обновлен');
                } catch (err) {
                    console.error('При обновлении элемента ' + selector + ' произошла ошибка:', err);
                }

            }

        })();


    </script>
</div>
</body>
</html>
