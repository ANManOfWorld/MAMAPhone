<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="/static/css/calls_finance_global.css"/>
    <link rel="stylesheet" href="/static/css/calls_finance_index.css"/>
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap"
    />
    <script src="https://www.google.com/jsapi"></script>
    <script>
        google.load("visualization", "1", {packages: ["corechart"]});
        google.setOnLoadCallback(drawGigs);

        function drawGigs() {
            var data = google.visualization.arrayToDataTable([
                ['Время', 'Гб'],
                ['', ${user.statisticOfInternetOne}],
                ['', ${user.statisticOfInternetTwo}],
                ['', ${user.statisticOfInternetThree}]
            ]);
            var options = {
                backgroundColor: '#BBB',
                colors: ["#438bf9"],
                backgroundColor: '#333',
                legendTextStyle: {
                    color: '#FFF'
                },
                titleTextStyle: {
                    color: '#FFF'
                },
                title: 'Использованный интернет за последнее время',
                hAxis: {textStyle: {color: '#FFF'}},
                vAxis: {
                    gridlines: {
                        color: 'transparent'
                    },
                    textStyle: {color: '#FFF'}
                }
            };

            var chart = new google.visualization.ColumnChart(document.getElementById('gigs'));
            chart.draw(data, options);
        }
    </script>
    <script>
        google.load("visualization", "1", {packages: ["corechart"]});
        google.setOnLoadCallback(drawMinutes);

        function drawMinutes() {
            var data = google.visualization.arrayToDataTable([
                ['Время', 'Мин'],
                ['', ${user.statisticOfMinutesOne}],
                ['', ${user.statisticOfMinutesTwo}],
                ['', ${user.statisticOfMinutesThree}]
            ]);
            var options = {
                colors: ["#06f2b1"],
                backgroundColor: '#333',
                legendTextStyle: {
                    color: '#FFF'
                },
                titleTextStyle: {
                    color: '#FFF'
                },
                title: 'Использованные минуты звонков за последнее время',
                hAxis: {textStyle: {color: '#FFF'}},
                vAxis: {
                    gridlines: {
                        color: 'transparent'
                    },
                    textStyle: {color: '#FFF'}
                },
            };
            var chart = new google.visualization.ColumnChart(document.getElementById('minutes'));
            chart.draw(data, options);
        }
    </script>
    <script>
        google.load("visualization", "1", {packages: ["corechart"]});
        google.setOnLoadCallback(drawMoney);

        function drawMoney() {
            var data = google.visualization.arrayToDataTable([
                ['Время', 'Руб'],
                ['', ${user.statisticOfFinanceOne}],
                ['', ${user.statisticOfFinanceTwo}],
                ['', ${user.statisticOfFinanceThree}]
            ]);
            var options = {
                colors: ["#FFD700"],
                backgroundColor: '#333',
                legendTextStyle: {
                    color: '#FFF'
                },
                titleTextStyle: {
                    color: '#FFF'
                },
                title: 'Потраченные деньги за последнее время',
                hAxis: {textStyle: {color: '#FFF'}},
                vAxis: {
                    gridlines: {
                        color: 'transparent'
                    },
                    textStyle: {color: '#FFF'}
                },
            };
            var chart = new google.visualization.ColumnChart(document.getElementById('money'));
            chart.draw(data, options);
        }
    </script>


</head>
<body>

<div class="rectangle-parent">
    <div class="custom">
        <div class="group-item">
            <b class="b">${user.name}</b>
            <div class="div">${user.phoneNum}</div>
        </div>
        <img class="user-1-icon" alt="" src="/static/img/calls_finance_public/user-1@2x.png"/>
    </div>

    <a href="/">
        <button class="group-inner">
            <img class="arrow-icon" alt="" src="/static/img/calls_finance_public/arrow-1.svg"/>
        </button>
    </a>

    <div id="gigs" class="graphic1"></div>
    <div id="minutes" class="graphic2"></div>
    <div id="money" class="graphic3"></div>


</div>


<#--<h1> MAMAPhone </h1>
<br>
<h1>Звонки и финансы</h1>
<h5>Детализированные отчёты. </h5>
<a href = "/">На главную</a>-->


</body>
</html>