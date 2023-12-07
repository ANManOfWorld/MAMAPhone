<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="/static/css/admin_index.css"/>
    <link rel="stylesheet" href="/static/css/admin_global.css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"/>

</head>
<body>

<div class="group-parent">
    <a href="/">
        <div class="rectangle-parent">
            <button class="group-item">
                <img class="group-inner" alt="" src="/static/img/admin_public/arrow-1.svg"/>
            </button>
        </div>
    </a>

    <div class="custom">

        <div class="div">
            <div class="child"></div>
            <b class="b">${user.name}</b>
            <div class="div1">${user.phoneNum}</div>
            <img class="user-3-icon" alt="" src="/static/img/admin_public/user-3@2x.png"/>
        </div>
        <div class="to-scroll">
            <table class="group-table" rules="all">
                <tr>
                    <td class="text-style">ID</td>
                    <td class="text-style">Email</td>
                    <td class="text-style">Статус</td>
                    <td class="text-style">Имя</td>
                    <td class="text-style">Роль</td>
                    <td class="text-style">Бан</td>
                    <td class="text-style">Модер</td>
                    <td class="text-style">Админ</td>
                </tr>

                <#list users as user>
                <tr>
                    <td>
                        <div class="text-style">${user.id}</div>
                    </td>
                    <td>
                        <div class="text-style">${user.email}</div>
                    </td>
                    <td>
                        <div class="text-style">
                            <#if user.active>true
                            <#else> false
                            </#if>
                        </div>
                    </td>
                    <td>
                        <div class="text-style">${user.name}</div>
                    </td>
                    <td>
                        <div class="text-style"><#list user.roles as role> ${role}
                                <#--<hr>--><#sep> </#list></div>
                    </td>
                    <form action="/user/ban/${user.id}" method="post">
                        <td>
                            <button class="ban-button">
                                <div class="button-text">Забанить</div>
                            </button>
                        </td>
                    </form>

                    <form action="/admin/user/edit/moderator/${user.id}" method="post">
                        <td>
                            <button class="moder-button">
                                <div class="button-text">Назначить</div>
                            </button>
                        </td>
                    </form>

                    <form action="/admin/user/edit/administrator/${user.id}" method="post">
                        <td>
                            <button class="admin-button">
                                <div class="button-text">Назначить</div>
                            </button>
                        </td>
                    </form>
                </tr>
                </#list>
            </table>
        </div>

    </div>

</div>

</body>
</html>


<#--<h1> MAMAPhone </h1>
<h5>Удобное приложение по оплате счетов связи для беспрерывного общения с любимыми людьми. </h5>
<a href="/">На главную</a>

&lt;#&ndash;<#if user.email??>
    <a><h3>Здравствуйте, админ: ${user.name}</h3>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Выйти"/>
        </form>
    </a>
<#else>
    <a href="/login"><h4>Войти</h4></a>
</#if>&ndash;&gt;

<hr>
<br>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Email</th>
        <th>Активность</th>
        <th>Имя</th>
        <th>Роль</th>
        <th>Бан</th>
        <th>Модератор</th>
        <th>Администратор</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.id}</th>
            <th>${user.email}</th>
            &lt;#&ndash;<th>${user.active}</th>&ndash;&gt;
            <th><#if user.active>true
                <#else> false
                </#if></th>
            <th>${user.name}</th>
            &lt;#&ndash;<th>${user.role}</th>&ndash;&gt;
            <th><#list user.roles as role> ${role}<#sep> </#list></th>
            <th>
                <form action="/user/ban/${user.id}" method="post">
                    &lt;#&ndash;<input type="hidden" name="_csrf" value="${_csrf.token}">&ndash;&gt;
                    <input type="submit" value="Забанить!">
                </form>
            </th>
            &lt;#&ndash;ДОБАВИТЬ ПОДРОБНУЮ ИНФОРМАЦИЮ О ЮЗЕРЕ&ndash;&gt;
            <th>
                <form action="/admin/user/edit/moderator/${user.id}" method="post">
                    <input type="submit" value="Модератор!">
                </form>
                &lt;#&ndash;<a href="/admin/user/edit/moderator/${user.id}">Модератор&lt;#&ndash;Изменение роли&ndash;&gt;</a>&ndash;&gt;
            </th>
            <th>
                <form action="/admin/user/edit/administrator/${user.id}" method="post">
                    <input type="submit" value="Администратор!">
                </form>
            </th>
        </tr>

    <#else> <h4>Юзеров нет.</h4>
    </#list>
</table>-->