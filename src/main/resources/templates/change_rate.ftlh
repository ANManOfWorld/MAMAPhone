<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="/static/css/change_rate_index.css"/>
    <link rel="stylesheet" href="/static/css/change_rate_global.css"/>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
    />
</head>
<body>
<div class="parent">
    <div class="div">
        <div class="child"></div>

        <#if errorList == true>
            <div class="custom">
                <div class="rectangle-parent">
                    <form action="/chooseRate/${rate.id}" method="post">
                        <button class="group-child">
                            <div class="div1">Подключить</div>
                        </button>
                    </form>
                </div>
                <div class="rectangle-group">
                    <div class="group-item"></div>
                    <b class="b">${rate.name}</b>
                    <div class="sms-container">

                        <p class="p"></p>
                        <p class="p">
                            <span>Ежемесячная абонентская плата: </span>
                            <b class="span">${rate.price} рублей/мес </b>
                        </p>
                        <p class="p"></p>
                        <p class="p">Включенный пакет услуг:</p>
                        <p class="p">
                            <span>- Интернет-трафик: </span>
                            <b class="span">${rate.countOfTrafficInternet} ГБ </b>
                        </p>
                        <p class="p">
                            <span>- Бесплатные звонки внутри сети оператора: </span>
                            <b class="span">${rate.countOfMinutes} Мин</b>
                            <span class="span"> </span>
                        </p>
                        <p class="p">
                            <span class="span">- Описание: </span>
                        <p class="span_custom">
                            <b class="span span_custom">${rate.description}</b>
                        </p>
                        <span> </span>
                        </p>
                    </div>
                </div>


            </div>
        </#if>

    </div>

    <a href="/">
        <button class="rectangle-container">
            <div class="group-inner"></div>
            <img class="arrow-icon" alt="" src="/static/img/change_rate_public/arrow-1.svg"/>
        </button>
    </a>
    <div class="custom">

        <#if errorPointers == false>
            <form action="/changeRate/pre" method="post">
                <button class="pre-button">
                    <img class="group-child1" alt="" src="/static/img/change_rate_public/arrow-11.svg"/>
                </button>
            </form>

            <form action="/changeRate/next" method="post">
                <button class="next-button">
                    <img class="group-child3" alt="" src="/static/img/change_rate_public/arrow-2.svg"/>
                </button>
            </form>
        </#if>
        <div class="rectangle-div">
            <b class="b6">${user.name}</b>
            <div class="div2">${user.phoneNum}</div>
        </div>
        <img class="user-3-icon" alt="" src="/static/img/change_rate_public/user-3@2x.png"/>

    </div>
</div>
</body>
</html>