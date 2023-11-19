<!DOCTYPE html>
<html xmlns:th = "http://www.thymeleaf.org">
<head>
    <link rel="stylesheet" href="/static/css/sign_up_global.css" />
    <link rel="stylesheet" href="/static/css/sign_up_index.css" />
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
    />
    <title>MAMAPhone</title>


    <#--<script src="script.js"></script>-->

    <#--<script>
        function showHide(element_id) {
            //Если элемент с id-шником element_id существует
            if (document.getElementById(element_id)) {
                //Записываем ссылку на элемент в переменную obj
                var obj = document.getElementById(element_id);
                //Если css-свойство display не block, то:
                if (/*element_id != "1"*/obj.style.display != "block") {
                    obj.style.display = "block"; //Показываем элемент
                } else obj.style.display = "none"; //Скрываем элемент
            }
            //Если элемент с id-шником element_id не найден, то выводим сообщение
            else alert("Элемент с id: " + element_id + " не найден!");
        }
    </script>-->

    <#--<a href="javascript:void(0)" onclick="showHide('hiddenPhone')"> </a>-->


</head>
<body>

<div class="group-parent">
    <div class="rectangle-parent">
        <section class="group-child"></section>
        <section class="laptop-1"></section>
        <form class="group-wrapper" action="/registration" method="post" th:object="${user}">
            <div class="rectangle-group">


                <#--<input class="group-inner" type="text" />-->
                <input class="group-inner" type="text" <#--th:field="*{name}"--> name = "phoneNum" placeholder="Номер телефона">
                <#if errorNum??>
                    <#--<h3 style = "color: green"> ${errorNum} </h3>-->
                    <script>
                        alert("${errorNum}");
                    </script>
                </#if>


                <#--<input class="group-item" type="text" />-->
                <input class="group-item" type="text" name="name" placeholder="ФИО">
                <#if errorName??>
                    <#--<h3 style = "color: green">${errorName}</h3>-->
                    <script>
                        alert("${errorName}");
                    </script>
                </#if>


                <#--<input class="rectangle-input" type="text" />-->
                <input class="rectangle-input" type="password" name="password" placeholder="Пароль">
                <#if errorPas??>
                    <#--<h3 style = "color: green"> ${errorPas} </h3>-->
                    <script>
                        alert("${errorPas}");
                    </script>
                </#if>


                <#--<input class="group-child1" type="text" />-->
                <input class="group-child1" type="email" name="email" placeholder="Email">
                <#if errorEmail??>
                    <#--<h3 style = "color: green"> ${errorEmail} </h3>-->
                    <script>
                        alert("${errorEmail}");
                    </script>
                </#if>



                <#--<div class="div">ФИО</div>
                <div id="hiddenPhone" class="div1">Номер телефона</div>-->
                <b class="b">Регистрация</b>
                <#--<div class="div2">Пароль</div>
                <div class="email">Email</div>-->



                <button class="rectangle-button"><div class="div3">Зарегистрироваться</div></button>


                <img class="icon" alt="" src="/static/img/sign_up_public/-3@2x.png" />
            </div>
        </form>
    </div>
</div>



<#--

<h1>MAMAPhone</h1>
<hr>
<a href="/">На главную</a>
<hr>
<h4>Регистрация</h4>
<form action="/registration" method="post" th:object="${user}">
    Имя пользователя: <input type="text" &lt;#&ndash;th:field="*{name}"&ndash;&gt; name = "name"><br><br>

    <#if errorName??>
        <h3 style = "color: green">
            ${errorName}
        </h3>
    </#if>

   &lt;#&ndash; <div style="color:red" th:if="${#fields.hasErrors("name")}" th:errors="*{name}">
        Name Error
    </div>&ndash;&gt;

    Электронная почта: <input type="email" name="email"><br><br>
    <#if errorEmail??>
    <h3 style = "color: green"> ${errorEmail} </h3>
    </#if>

    Номер телефона: <input type="text" name="phoneNum"><br><br>
    <#if errorNum??>
        <h3 style = "color: green"> ${errorNum} </h3>
    </#if>

    Пароль: <input type="password" name="password"><br><br>
    <#if errorPas??>
        <h3 style = "color: green"> ${errorPas} </h3>
    </#if>

   &lt;#&ndash; <input type="hidden" name="_csrf" value="${_csrf.token}">&ndash;&gt; &lt;#&ndash; Защита приложения от "взлома".
                                                                Токен создаёт уникальный ключ,
                                                                который проверяется SPR.SECURITY. &ndash;&gt;
    <input type="submit" value="Зарегистрироваться"/>
</form>
<#if errorMessage??>
    <h3 style = "color: green">${errorMessage}</h3>
</#if>

-->


</body>
</html>