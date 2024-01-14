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

<div class="rectangle-parent">
    <section class="group-child"></section>
    <section class="laptop-1"></section>
    <form class="group-wrapper" action="/registration" method="post" th:object="${user}">
        <div class="rectangle-group">
            <b class="b">Регистрация</b>
            <input class="group-inner tel" type="text" name = "phoneNum" placeholder="Номер телефона" style="text-align:center"></input>
            <#if errorValidPhone??>
                <script>
                    alert("${errorValidPhone}");   /*errorNum*/
                </script>
            </#if>
            <script>
                window.addEventListener("DOMContentLoaded", function() {
                    [].forEach.call( document.querySelectorAll('.tel'), function(input) {
                        var keyCode;
                        function mask(event) {
                            event.keyCode && (keyCode = event.keyCode);
                            var pos = this.selectionStart;
                            if (pos < 3) event.preventDefault();
                            var matrix = "+7 (___)___-__-__",
                                i = 0,
                                def = matrix.replace(/\D/g, ""),
                                val = this.value.replace(/\D/g, ""),
                                new_value = matrix.replace(/[_\d]/g, function(a) {
                                    return i < val.length ? val.charAt(i++) || def.charAt(i) : a
                                });
                            i = new_value.indexOf("_");
                            if (i != -1) {
                                i < 5 && (i = 3);
                                new_value = new_value.slice(0, i)
                            }
                            var reg = matrix.substr(0, this.value.length).replace(/_+/g,
                                function(a) {
                                    return "\\d{1," + a.length + "}"
                                }).replace(/[+()]/g, "\\$&");
                            reg = new RegExp("^" + reg + "$");
                            if (!reg.test(this.value) || this.value.length < 5 || keyCode > 47 && keyCode < 58) this.value = new_value;
                            if (event.type == "blur" && this.value.length < 5)  this.value = ""
                        }

                        input.addEventListener("input", mask, false);
                        input.addEventListener("focus", mask, false);
                        input.addEventListener("blur", mask, false);
                        input.addEventListener("keydown", mask, false)

                    });

                });
            </script>


            <input class="group-item" type="text" name="name" placeholder="Имя" maxlength="12" style="text-align:center"></input>
            <#if errorValidName??>
                <script>
                    alert("${errorValidName}");  /*errorName*/
                </script>
            </#if>

            <input class="group-child1" type="text" name="lastName" placeholder="Фамилия" maxlength="12" style="text-align:center"></input>
            <#if errorValidLastName??>
                <script>
                    alert("${errorValidLastName}");  /*errorLastName*/
                </script>
            </#if>


            <input class="rectangle-input" type="text" name="fatherName" placeholder="Отчество" maxlength="12" style="text-align:center"></input>
            <#if errorValidFatherName??>
                <script>
                    alert("${errorValidFatherName}");    /*errorFatherName*/
                </script>
            </#if>


            <input class="group-child2" type="password" name="password" placeholder="Пароль" style="text-align:center"></input>
            <#if errorValidPassword??>
                <script>
                    alert("${errorValidPassword}");   /*errorPas*/
                </script>
            </#if>


            <input class="group-child3" type="email" name="email" placeholder="Email" style="text-align:center"></input>
            <#if errorValidEmail??>
                <script>
                    alert("${errorValidEmail}"); /*errorEmail*/
                </script>
            </#if>

            <#if canNotReg??>
                <script>
                    alert("${canNotReg}");
                </script>
            </#if>

            <button class="rectangle-button">
                <div class="div5">Зарегистрироваться</div>
            </button>
            <img class="icon" alt="" src="/static/img/sign_up_public/-3@2x.png" />
        </div>
    </form>
</div>
</body>
</html>