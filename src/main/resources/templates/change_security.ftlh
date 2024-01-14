<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="/static/css/change_security_global.css"/>
    <link rel="stylesheet" href="/static/css/change_security_index.css"/>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
    />


</head>
<body>
<div class="rectangle-parent">
    <div class="group-child"></div>

    <div class="custom">

        <form action="/security/change" method="post" th:object="${user}">
            <button class="group-child1">
                <div class="div4">Изменить</div>
            </button>
            <div class="group-wrapper">
                <div class="rectangle-group">


                        <input class="cc-number-input group-item" type="text" name="numOfCard" placeholder="${user.numOfCard}" maxlength="19" <#--pattern="[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}"-->>
                        <#if errorNum??>
                            <script>
                                alert("${errorNum}");
                            </script>
                        </#if>

                        <input class="cc-expiry-input group-inner" type="text" name="CVC" placeholder="${user.CVC}" maxlength="3" <#--pattern="[0-9]{3}"-->>
                        <#if errorCVC??>
                            <script>
                                alert("${errorCVC}");
                            </script>
                        </#if>


                        <div class="div">Номер карты</div>
                        <div class="cvvcvc">CVV/CVC</div>
                        <b class="b">Смена карты</b>
                </div>
            </div>
        </form>

        <div class="rectangle-div">
            <b class="b1">${user.name}</b>
            <div class="div3">${user.phoneNum}</div>
        </div>
        <img class="user-3-icon" alt="" src="/static/img/change_security_public/user-3@2x.png"/>





        <form action="/security/delete" method="post">
            <button class="group-child2">
                <div class="div5">Удалить</div>
            </button>
        </form>
    </div>


    <a href="/security">
        <button class="rectangle-button">
            <img class="arrow-icon" alt="" src="/static/img/change_security_public/arrow-3.svg"/>
        </button>
    </a>

</div>

<script>
    let ccNumberInput = document.querySelector('.cc-number-input'),
        ccNumberPattern = /^\d{0,16}$/g,
        ccNumberSeparator = "-",
        ccNumberInputOldValue,
        ccNumberInputOldCursor,

        ccExpiryInput = document.querySelector('.cc-expiry-input'),
        ccExpiryPattern = /^\d{0,4}$/g,
        ccExpirySeparator = "",
        ccExpiryInputOldValue,
        ccExpiryInputOldCursor,

        ccCVCInput = document.querySelector('.cc-cvc-input'),
        ccCVCPattern = /^\d{0,3}$/g,



        mask = (value, limit, separator) => {
            var output = [];
            for (let i = 0; i < value.length; i++) {
                if ( i !== 0 && i % limit === 0) {
                    output.push(separator);
                }

                output.push(value[i]);
            }

            return output.join("");
        },
        unmask = (value) => value.replace(/[^\d]/g, ''),
        checkSeparator = (position, interval) => Math.floor(position / (interval + 1)),
        ccNumberInputKeyDownHandler = (e) => {
            let el = e.target;
            ccNumberInputOldValue = el.value;
            ccNumberInputOldCursor = el.selectionEnd;
        },
        ccNumberInputInputHandler = (e) => {
            let el = e.target,
                newValue = unmask(el.value),
                newCursorPosition;

            if ( newValue.match(ccNumberPattern) ) {
                newValue = mask(newValue, 4, ccNumberSeparator);

                newCursorPosition =
                    ccNumberInputOldCursor - checkSeparator(ccNumberInputOldCursor, 4) +
                    checkSeparator(ccNumberInputOldCursor + (newValue.length - ccNumberInputOldValue.length), 4) +
                    (unmask(newValue).length - unmask(ccNumberInputOldValue).length);

                el.value = (newValue !== "") ? newValue : "";
            } else {
                el.value = ccNumberInputOldValue;
                newCursorPosition = ccNumberInputOldCursor;
            }

            el.setSelectionRange(newCursorPosition, newCursorPosition);

            highlightCC(el.value);
        },
        highlightCC = (ccValue) => {
            let ccCardType = '',
                ccCardTypePatterns = {
                    amex: /^3/,
                    visa: /^4/,
                    mastercard: /^5/,
                    disc: /^6/,

                    genric: /(^1|^2|^7|^8|^9|^0)/,
                };

            for (const cardType in ccCardTypePatterns) {
                if ( ccCardTypePatterns[cardType].test(ccValue) ) {
                    ccCardType = cardType;
                    break;
                }
            }
        },
        ccExpiryInputKeyDownHandler = (e) => {
            let el = e.target;
            ccExpiryInputOldValue = el.value;
            ccExpiryInputOldCursor = el.selectionEnd;
        },
        ccExpiryInputInputHandler = (e) => {
            let el = e.target,
                newValue = el.value;

            newValue = unmask(newValue);
            if ( newValue.match(ccExpiryPattern) ) {
                newValue = mask(newValue, 2, ccExpirySeparator);
                el.value = newValue;
            } else {
                el.value = ccExpiryInputOldValue;
            }
        };

    ccNumberInput.addEventListener('keydown', ccNumberInputKeyDownHandler);
    ccNumberInput.addEventListener('input', ccNumberInputInputHandler);

    ccExpiryInput.addEventListener('keydown', ccExpiryInputKeyDownHandler);
    ccExpiryInput.addEventListener('input', ccExpiryInputInputHandler);
</script>


</body>
</html>