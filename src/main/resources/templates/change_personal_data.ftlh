<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1> MAMAPhone </h1>
<h3 color="darkred">Личные данные.</h3>
<a href="/personalData">На главную</a>

<form action="/change_personal_data/change" method="post" th:object="${user}">
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
    * <#--<input type="text" name="" placeholder="&lt;#&ndash;${user.name}&ndash;&gt;" &lt;#&ndash;value="${user.name}"&ndash;&gt;>  &lt;#&ndash;ДОПИСАТЬ! ДАТУ!!!&ndash;&gt;-->
    <hr>
    <hr>
    * <input type="text" name="email" placeholder="${user.email}" value="${user.email}" >
    <#if errorValidEmail??>
        <script>
            alert("${errorValidEmail}");
        </script>
    </#if>

    <hr>
<#--
    <#if errorMessage??>
        <script>
            alert("${errorMessage}");
        </script>
    </#if>-->

    <input type="submit" value="Изменить">
</form>

<#if good??>
    <form id = "myForm"  action="/logout" method="post">
        <button id="open_all" type="submit" ></button>
    </form>
</#if>

<script>
    window.addEventListener("load", function () {
        var btn = document.getElementById ("open_all");
        btn.click ();
    });

</script>

</body>
</html>