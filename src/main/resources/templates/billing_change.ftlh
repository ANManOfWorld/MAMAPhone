<!doctype html> <!-- фри маркер, чтобы работать с какими-либо передаваемыми данными -->
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="/static/css/billing_change_index.css"/>
    <link rel="stylesheet" href="/static/css/billing_change_global.css"/>
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

        <#if Change??>
            <script>
                /*alert("Тариф не был изменён. Причины: такое название уже существует; количество ГБ < 10000; Мин < 10000; Цена < 99000 руб. ");*/
                alert("${Change}");
            </script>
        </#if>


        <#if user.isModerator()>

            <div class="custom">
                <div class="group-child6">
                    <b class="b3">${user.name}</b>
                    <div class="div3">${user.phoneNum}</div>
                </div>
                <img class="user-3-icon" alt="" src="/static/img/billing_public/user-3@2x.png"/>

                <form action="/billing_change/change/${rate.id}" method="post" enctype="multipart/form-data">

                    <div class="div7">
                        <div class="rectangle-div"></div>
                        <input class="item" type="text" name="countOfMinutes" placeholder="1" min="1" max="9999"
                               maxlength="4" value="${rate.countOfMinutes}"/>
                        <script>
                            const input_item = document.querySelector('.item');

                            input_item.addEventListener('input', function () {
                                input_item.value = input_item.value.replace(/[^0-9, ]/g, "");
                                input_item.setAttribute('value', input_item.value);
                            });
                        </script>


                        <textarea class="inner" name="description" type="text" placeholder="${rate.description}"
                                  maxlength="255"></textarea>

                        <input class="rectangle-input" type="text" name="name" value="${rate.name}" placeholder="${rate.name}"
                               maxlength="25"/>


                        <p class="p6">- Описание:</p>
                        <p class="p7">- Бесплатные звонки внутри сети оператора:</p>
                        <p class="p8">
                            <span> - Интернет-трафик: </span>
                        </p>
                        <p class="p9">
                            <span>Ежемесячная абонентская плата: </span>
                        </p>
                        <input class="child1" type="text" name="countOfTrafficInternet" placeholder="1" min="1"
                               max="9999" maxlength="4" value="${rate.countOfTrafficInternet}"/>

                        <script>
                            const input_child1 = document.querySelector('.child1');

                            input_child1.addEventListener('input', function () {
                                input_child1.value = input_child1.value.replace(/[^0-9, ]/g, "");
                                input_child1.setAttribute('value', input_child1.value);
                            });
                        </script>
                        <input class="child2" type="text" name="price" min="1" max="99000" maxlength="5"
                               placeholder="1" value="${rate.price}"/>

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


                        <button class="edit-button">
                            <div class="button-text">Изменить</div>
                        </button>



                </form>

                    <form action="/rate/delete/${rate.id}" method="post">
                        <button class="delete-button">
                            <div class="button-text">Удалить</div>
                        </button>
                    </form>


            </div>

        </#if>

    </div>
</div>

</body>
</html>