<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="/static/css/personal_data_index.css"/>
    <link rel="stylesheet" href="/static/css/personal_data_global.css"/>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
    />
</head>
<body>
<div class="rectangle-parent">
    <div class="group-child"></div>

    <div class="custom">
        <div class="group-wrapper">
            <div class="rectangle-group">

                <form action="/change_personal_data/change" method="post" th:object="${user}">

                    <button type="submit" class="group-child-custom">
                        <div class="div10"><#--Изменить-->Сохранить</div>
                    </button>

                    <input class="group-item" type="text" name="lastName" placeholder="${user.lastName}"
                           value="${user.lastName}" maxlength="12">
                    <#if errorValidLastName??>
                        <script>
                            alert("${errorValidLastName}");
                        </script>
                    </#if>
                    <input class="group-inner" type="text" name="name" placeholder="${user.name}" value="${user.name}"
                           maxlength="12">
                    <#if errorValidName??>
                        <script>
                            alert("${errorValidName}");
                        </script>
                    </#if>

                    <input class="group-child1" type="text" name="fatherName" placeholder="${user.fatherName}"
                           value="${user.fatherName}" maxlength="12">
                    <#if errorValidFatherName??>
                        <script>
                            alert("${errorValidFatherName}");
                        </script>
                    </#if>


                    <input class="rectangle-input" type="date" name="birth" value="${user.birth}2021" placeholder="${user.birth}" min="1950-01-01" max="2024-01-30"/> <#--ВПИСАТЬ ДАТУ-->




                    <input class="group-child2" type="text" name="email" placeholder="${user.email}"
                           value="${user.email}">
                    <#if errorValidEmail??>
                        <script>
                            alert("${errorValidEmail}");
                        </script>
                    </#if>

                </form>


                <div class="div">Фамилия</div>
                <div class="div1">Имя</div>
                <div class="div2">Дата рождения</div>
                <div class="div3">Отчество</div>
                <b class="b">Изменение данных</b>
                <div class="div8">Эл. почта</div>
            </div>
        </div>


        <div class="rectangle-div">
            <b class="b1">${user.name}</b>
            <div class="div9">${user.phoneNum}</div>
        </div>
        <img class="user-3-icon" alt="" src="/static/img/personal_data_public/user-3@2x.png"/>
    </div>

    <a href="/personalData">
        <button class="rectangle-button">
            <img class="arrow-icon" alt="" src="/static/img/personal_data_public/arrow-3.svg"/>
        </button>
    </a>
</div>

<#if good??>
    <script>alert("Внимание!\nПосле изменения электронной почты Вы выйдете из аккаунта.");</script>
    <form id="myForm" action="/logout" method="post">
        <button id="open_all" type="submit"></button>
    </form>
</#if>

<script>
    window.addEventListener("load", function () {
        var btn = document.getElementById("open_all");
        btn.click();
    });

</script>

</body>
</html>





<#--<h1> MAMAPhone </h1>
<h3 color="darkred">Личные данные.</h3>
<a href="/personalData">На главную</a>-->

<#--<form action="/change_personal_data/change" method="post" th:object="${user}">
    <br>
    <hr>
    * <input type="text" name="name" placeholder="${user.name}" value="${user.name}" maxlength="12">
    <#if errorValidName??>
        <script>
            alert("${errorValidName}");
        </script>
    </#if>
    <hr>
    <hr>
    * <input type="text" name="lastName" placeholder="${user.lastName}" value="${user.lastName}" maxlength="12">
    <#if errorValidLastName??>
        <script>
            alert("${errorValidLastName}");
        </script>
    </#if>
    <hr>
    <hr>
    * <input type="text" name="fatherName" placeholder="${user.fatherName}" value="${user.fatherName}" maxlength="12">
    <#if errorValidFatherName??>
        <script>
            alert("${errorValidFatherName}");
        </script>
    </#if>
    <hr>
    <hr>
    * &lt;#&ndash;<input type="text" name="" placeholder="&lt;#&ndash;${user.name}&ndash;&gt;" &lt;#&ndash;value="${user.name}"&ndash;&gt;>  &lt;#&ndash;ДОПИСАТЬ! ДАТУ!!!&ndash;&gt;&ndash;&gt;
    <hr>
    <hr>
    * <input type="text" name="email" placeholder="${user.email}" value="${user.email}">
    <#if errorValidEmail??>
        <script>
            alert("${errorValidEmail}");
        </script>
    </#if>

    <hr>
    &lt;#&ndash;
        <#if errorMessage??>
            <script>
                alert("${errorMessage}");
            </script>
        </#if>&ndash;&gt;

    <input type="submit" value="Изменить">
</form>-->