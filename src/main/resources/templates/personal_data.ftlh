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
                <input class="group-item" type="text" value="${user.lastName}" readonly="readonly"/>

                <input class="group-inner" type="text" value="${user.name}" readonly="readonly" />

                <input class="group-child1" type="text" value="${user.fatherName}" readonly="readonly" />

                <input class="rectangle-input" type="text" value="${user.birth}" readonly="readonly" /> <#--ВПИСАТЬ ДАТУ-->

                <input class="group-child2" type="text" value="${user.email}" readonly="readonly" />

                <div class="div">Фамилия</div>
                <div class="div1">Имя</div>
                <div class="div2">Дата рождения</div>
                <div class="div3">Отчество</div>
                <b class="b">Личные данные</b>
                <div class="div8">Эл. почта</div>
            </div>
        </div>


        <div class="rectangle-div">
            <b class="b1">${user.name}</b>
            <div class="div9">${user.phoneNum}</div>
        </div>

        <img class="user-3-icon" alt="" src="/static/img/personal_data_public/user-3@2x.png"/>

        <a href="/change_personal_data">
            <button class="group-child3">
                <div class="div10">Изменить</div>
            </button>
        </a>
    </div>

    <a href="/settings">
        <button class="rectangle-button">
            <img class="arrow-icon" alt="" src="/static/img/personal_data_public/arrow-3.svg"/>
        </button>
    </a>
</div>

<#--
<a href = "/settings">На главную</a>
<br>
* Имя: ${user.name}
<br>
<br>
* Фамилия: ${user.lastName}
<br>
<br>
* Отчество: ${user.fatherName}
<br>
<br>
* Дата рождения: &lt;#&ndash;${user.name}&ndash;&gt; &lt;#&ndash;ДОПИСАТЬ&ndash;&gt;
<br>
<br>
* Эл. почта: ${user.email}
<br>


<a href="/change_personal_data">
    <button>Изменить</button>
</a>-->

</body>
</html>