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
                        <#if idCurrent != user.getId()>
                            <form action="/user/ban/${user.id}" method="post">
                                <td>
                                    <button class="ban-button">
                                        <div class="button-text">Забанить</div>
                                    </button>
                                </td>
                            </form>
                        <#else>
                            <td>
                                <button class="ban-button" style="background-color: #9299a3">
                                    <div class="button-text">Забанить</div>
                                </button>
                            </td>
                        </#if>
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