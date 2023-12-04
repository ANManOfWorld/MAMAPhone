<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="initial-scale=1, width=device-width"/>

    <link rel="stylesheet" href="/static/css/change_rate_moder_global.css"/>
    <link rel="stylesheet" href="/static/css/change_rate_moder_index.css"/>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
    />
</head>
<body>
<div class="parent">
    <div class="div">
        <div class="child"></div>

        <div class="custom">
            <div class="buttons">


                <button class="edit-button">
                    <div class="edit-text">Редактировать</div> <#--ДОПИСАТЬ ССЫЛКУ НА РЕДАЧ-->
                </button>

                <form action="/chooseRate/${rate.id}" method="post">
                    <button class="link-button">
                        <div class="link-text">Подключить</div>
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
                        <b class="span_custom">${rate.description}</b>
                    </p>
                    <span> </span>
                    </p>
                    <#--<p class="p"></p>
                    <p class="p">Дополнительные услуги и опции:</p>
                    <p class="p">
                        - Дополнительный интернет-трафик сверх пакета: 100 рублей за 1 ГБ
                    </p>
                    <p class="p">- Звонки на другие номера: 0,2 рубля в минуту</p>
                    <p class="p">- SMS на другие номера: 1 рубль за сообщение</p>-->
                </div>
            </div>
        </div>

    </div>

    <a href="/">
        <button class="rectangle-container">
            <div class="group-inner"></div>
            <img class="arrow-icon" alt="" src="/static/img/change_rate_public/arrow-1.svg"/>
        </button>
    </a>

    <#if errorPointers == false>
    <div class="custom">
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
