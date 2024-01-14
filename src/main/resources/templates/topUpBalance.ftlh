<!doctype html> <!-- фри маркер, чтобы работать с какими-либо передаваемыми данными -->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="/static/css/topupbalance_global.css"/>
    <link rel="stylesheet" href="/static/css/topupbalance_index.css"/>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
    />

</head>

<body>

<div class="rectangle-parent">
    <div class="group-child"></div>

    <div class="custom">
        <form action="/top_up_balance" method="post">
            <#if user.numOfCard == "XXXX-XXXX-XXXX-XXXX">
                <input class="cc-number-input group-item" type="text" name="numOfCard" placeholder="${user.numOfCard}" maxlength="19">
            <#else> <input class="cc-number-input group-item" type="text" name="numOfCard" value="${user.numOfCard}" placeholder="${user.numOfCard}" maxlength="19">
            </#if>
            <#if errorNum??>
                <script>
                    alert("${errorNum}");
                </script>
            </#if>

            <#if user.CVC == "XXX">
                <input class="cc-expiry-input group-inner" type="text" name="CVC" placeholder="${user.CVC}" maxlength="3">
            <#else><input class="cc-expiry-input group-inner" type="text" name="CVC" value="${user.CVC}" placeholder="${user.CVC}" maxlength="3">
            </#if>


            <#if errorCVC??>
                <script>
                    alert("${errorCVC}");
                </script>
            </#if>

            <input id="need" class="rectangle-input"  type="text" name="balance" min="1" value="1" maxlength="6"<#--placeholder=" ${user.balance}"-->>
            <#if errorBalance??>
                <script>
                    alert("${errorBalance}");
                </script>
            </#if>
            <#if errorBalanceHigh??>
                <script>
                    alert("${errorBalanceHigh}");
                </script>
            </#if>

            <button class="group-child1">
                <div class="div8">Оплатить</div>
            </button>

        </form>

        <div class="div">Номер карты</div>
        <div class="cvvcvc">CVV/CVC</div>
        <div class="div1">Сумма</div>
        <div class="div2">Руб.</div>
        <b class="b">Пополнение баланса</b>


        <div class="rectangle-div">
            <b class="b1">${user.name}</b>
            <div class="div7">${user.phoneNum}</div>
        </div>
        <img class="user-4-icon" alt="" src="/static/img/topupbalance_public/user-4@2x.png"/>


    </div>


    <a href="/">
        <button class="rectangle-button">
            <img class="arrow-icon" alt="" src="/static/img/topupbalance_public/arrow-5.svg"/>
        </button>
    </a>

</div>
<#if errorAnswerOfTransaction??>
    <script>
        alert("${errorAnswerOfTransaction}");
    </script>
</#if>

<#if answerUserTopUpBalance??>
    <script>
        alert("${errorAnswerUserTopUpBalance}");
    </script>
</#if>

<script>

        const input  = document.querySelector('#need');

        input.addEventListener('input', function() {
        input.value = input.value.replace(/[^0-9, ]/g,"");
        input.setAttribute('value', input.value);
    });




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

            /*  let activeCC = document.querySelector('.cc-types__img--active'),
                  newActiveCC = document.querySelector(`.cc-types__img--<#--${ccCardType}-->`);

            if (activeCC) activeCC.classList.remove('cc-types__img--active');
            if (newActiveCC) newActiveCC.classList.add('cc-types__img--active');*/
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