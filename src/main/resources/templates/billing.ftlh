<!doctype html> <!-- фри маркер, чтобы работать с какими-либо передаваемыми данными -->
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="/static/css/billing_index.css"/>
    <link rel="stylesheet" href="/static/css/billing_global.css"/>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
    />

</head>
<body>

<div class="group-parent">
    <div class="group-wrapper">
        <div class="group-child"></div>

        <a href="/">
            <button class="group-child1">
                <img class="arrow-icon" alt="" src="/static/img/billing_public/arrow-1.svg"/>
            </button>
        </a>

        <#if user.isAdmin()>
            <a href="/admin">
                <button class="group-child16"></button>
            </a>
        </#if>


        <#if user.isModerator()>

            <div class="custom">
                <div class="group-child6">
                    <b class="b3">${user.name}</b>
                    <div class="div3">${user.phoneNum}</div>
                </div>
                <img class="user-3-icon" alt="" src="/static/img/billing_public/user-3@2x.png"/>

                <form action="/rate/create" method="post" enctype="multipart/form-data">

                    <div class="div7">
                        <div class="rectangle-div"></div>
                        <input class="item" type="text" name="countOfMinutes" placeholder="1" min="1" max="9999"
                               maxlength="4" value="1"/>
                        <#--<input class="item" type="text" placeholder="0" maxlength="4" min="1" max="9999" value="1"/>-->
                        <script>
                            const input_item = document.querySelector('.item');

                            input_item.addEventListener('input', function () {
                                input_item.value = input_item.value.replace(/[^0-9, ]/g, "");
                                input_item.setAttribute('value', input_item.value);
                            });
                        </script>

                        <#--<input type="text" name="description" maxlength="255"/>-->
                        <textarea class="inner" name="description" type="text" placeholder="Текстовое описание"
                                  maxlength="255"></textarea>

                        <input class="rectangle-input" type="text" name="name" placeholder="Название"
                               maxlength="25"/>
                        <#--<input class="rectangle-input" type="text" placeholder="Название" maxlength="25"/>-->

                        <p class="p6">- Описание:</p>
                        <p class="p7">- Бесплатные звонки внутри сети оператора:</p>
                        <p class="p8">
                            <span> - Интернет-трафик: </span>
                        </p>
                        <p class="p9">
                            <span>Ежемесячная абонентская плата: </span>
                        </p>
                        <input class="child1" type="text" name="countOfTrafficInternet" placeholder="1" min="1"
                               max="9999" maxlength="4" value="1"/>
                        <#--<input class="child1" type="text" placeholder="0" maxlength="4" min="1" max="9999" value="1"/>-->
                        <script>
                            const input_child1 = document.querySelector('.child1');

                            input_child1.addEventListener('input', function () {
                                input_child1.value = input_child1.value.replace(/[^0-9, ]/g, "");
                                input_child1.setAttribute('value', input_child1.value);
                            });
                        </script>
                        <input class="child2" type="text" name="price" min="1" max="99000" maxlength="5"
                               placeholder="1" value="1"/>
                        <#--<input class="child2" type="text" placeholder="0" maxlength="5" min="1" max="99000" value="1"/>-->
                        <script>
                            const input_child2 = document.querySelector('.child2');

                            input_child2.addEventListener('input', function () {
                                input_child2.value = input_child2.value.replace(/[^0-9, ]/g, "");
                                input_child2.setAttribute('value', input_child2.value);
                            });
                        </script>
                        <p class="p10">Мин</p>
                        <p class="p11">ГБ</p>
                        <p class="p12">Включенный пакет услуг:</p>
                        <p class="p13">рублей/мес</p>
                    </div>
                    <div class="parent">
                        <button class="group-child12">
                            <div class="div1">Создать</div>
                        </button>
                    </div>

                </form>
            </div>

        </#if>

    </div>
</div>

<#if errorRate??>
    <script>
        alert(${errorRate});
    </script>
</#if>





<#--<h1> MAMAPhone </h1>
<h5>Удобное приложение по оплате счетов связи для беспрерывного общения с любимыми людьми. </h5>

<#if user.email??>
    <a><h3>Здравствуйте, ${user.name}</h3>
        <hr>
        <#if user.isAdmin()>
            <a href="/admin">Панель администратора</a>
            <hr>
        </#if>

        <form action="/logout" method="post">
            &lt;#&ndash;<input type="hidden" name="_csrf" value="${_csrf.token}"/>&ndash;&gt;
            <input type="submit" value="Выйти"/>
        </form>
    </a>
<#else>
    <a href="/login"><h4>Войти</h4></a>
    <hr>
</#if>
<br>
<br>-->

<#--<form action="/" method="get">
    Поиск тарифов: <input type="text" name="name"> <br>
    <input type="submit" value="Поиск">
</form>-->

<#--
<#if user.isModerator()>
<hr>
<#list rates as rate>
    <div>
        <p>${rate.name} || ${rate.description} ${rate.price} руб. | <a href="/rate/admin/${rate.id}">Подробнее</a></p>
    </div>
</#list>
<hr>
<#else>
<hr>
<#list rates as rate>
    <div>
        <p>${rate.name} || ${rate.description} ${rate.price} руб. | <a href="/rate/${rate.id}">Подробнее</a></p>
    </div>
</#list>
<hr>
</#if>
-->


<#--<#if user.isModerator()>                                                                    &lt;#&ndash;user.email??&ndash;&gt;
    <h3> Создать новый тариф</h3>
    <form action="/rate/create" method="post" enctype="multipart/form-data">
        <!-- "enctype="multipart/form-data"  " атрибут для фото&ndash;&gt;
        Название тарифа: <input type="text" name="name" maxlength="25"/> <br>
        Описание тарифа: <input type="text" name="description" maxlength="255"/> <br>
        Цена: <input id="need" type="text" name="price" min="1" max="99000" maxlength="5" value="1"/> <br>
        Количество минут: <input id="need" type="text" name="countOfMinutes" min="1" max="9999" maxlength="4"
                                 value="1"/> <br>
        Количество трафика (в ГБ): <input id="need" type="text" name="countOfTrafficInternet" min="1" max="9999"
                                          maxlength="4" value="1"/> <br>

        <!-- фотография &ndash;&gt;
        &lt;#&ndash;Фотография: <input type="file" name="file1"/> <br>&ndash;&gt;
        <!-- фотография &ndash;&gt;

        <input type="submit" value="Добавить тариф"/>
    </form>
</#if>-->

<#--<#if errorRate??>
    <script>
        alert(${errorRate});
    </script>
</#if>


<script>
    const input  = document.querySelector('#need');

    input.addEventListener('input', function() {
        input.value = input.value.replace(/[^0-9, ]/g,"");
        input.setAttribute('value', input.value);
    });
</script>-->


</body>
</html>