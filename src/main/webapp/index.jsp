<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/head.jsp" %>


<link rel="stylesheet" href="css/main.css">

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Library</title>
</head>
<body>

<!-- Slider-->
<div id="carousel" class="carousel slide" data-ride="carousel" id="slides">

    <div class="carousel-inner ">
        <div class="carousel-item active myclass">
            <img src="img/wal11.jpg">
            <div class="carousel-caption">
                <h1 class="display-2">Welcome to Library</h1>
            </div>
        </div>
        <div class="carousel-item">
            <div class="row padding">
                <div class="offset-lg-2 col-lg-4 jumbotron">
                    <h2>ТОП-11 самых ожидаемых книг октября</h2>
                    <p>Чем на улице холоднее, тем приятнее достать любимые шерстяные носочки с дыркой на пятке и залечь в берлоге, наслаждаясь осенним чтением.
                        Тем более что в этом октябре издатели порадовали нас сразу несколькими громкими новинками. Сейчас расскажем!
                    </p>
                    <br>
                    <a href="#" class="btn btn-danger">Read more</a>
                </div>
                <div class="col-lg-4">
                    <br>
                    <img src="img/news/news1.jpg" class="img-fluid">
                </div>

            </div>
        </div>
        <div class="carousel-item">
            <div class="row padding">
                <div class="offset-lg-2 col-lg-4 jumbotron">
                    <h2>В каком порядке читать книги Достоевского</h2>
                    <p>Подступаетесь к одному из главных авторов русской литературы? Подсказываем план, который действительно сработает.
                        <br><br>
                        Ваше школьное знакомство с Достоевским закончилось провалом? Показалось слишком мрачно, многословно и трудно?
                        Вроде бы понимаете, что многое теряете, но по разным причинам никак не можете подружиться с текстами классика?
                        Вы просто не знаете, с чего начать</p>
                    <br>
                    <a href="#" class="btn btn-danger">Read more</a>
                </div>
                <div class="col-lg-4">
                    <br>
                    <img src="img/news/news2.jpg" class="img-fluid">
                </div>

            </div>
        </div>
    </div>
    <a class="carousel-control-prev dark" href="#carousel" role="button" data-slide="prev">
        <i class="fas fa-angle-left"></i>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carousel" role="button" data-slide="next">
        <i class="fas fa-angle-right"></i>
        <span class="sr-only">Next</span>
    </a>
</div>

<!--News-->
<hr>
<div class="container padding">
    <div class="row padding">
        <div class="col-md-4">
            <div class="card">
                <img src="img/news/news21.jpg" class="card-img-top">
                <div class="card-body">
                    <h4 class="card-title">Книги, о которых говорят: «Атлант расправил плечи»</h4>
                    <p class="card-text">Рассказ о романе, который стал популярным через 50 лет после публикации.</p>
                    <a href="#" class="btn btn-info">Look at &xrArr;</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <img src="img/news/news22.jpg" class="card-img-top">
                <div class="card-body">
                    <h4 class="card-title">Новый фильм Ромы Либерова про Платонова выходит в прокат</h4>
                    <p class="card-text">Совсем скоро увидим премьеру «Сокровенного человека».</p>
                    <a href="#" class="btn btn-info">Look at &xrArr;</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <img src="img/news/news23.jpg" class="card-img-top">
                <div class="card-body">
                    <h4 class="card-title">Что ещё почитать: книги как «Гарри Поттер»</h4>
                    <p class="card-text">Давно закончили семикнижие про Мальчика-со-Шрамом? Попробуйте эти книги.</p>
                    <a href="#" class="btn btn-info">Look at &xrArr;</a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid padding ">
    <br>
    <br>
    <div class="row padding">
        <div class="jumbotron offset-lg-2 col-lg-4">
            <h2>Алан Мур: история создателя «Хранителей»</h2>
            <p>Как мальчишка из Нортгемптона изменил мир комиксов.
                <br><br>
                Знаете, кто такой Алан Мур? Человек, изменивший мир комиксов. Да-да, это сказано без преувеличения: его называли своим вдохновителем
                Нил Гейман, Джосс Уидон и Дэймон Линделоф, он признан «лучшим создателем комиксов в истории» и «одним из самых значительных британских
                писателей последних пятидесяти лет». При этом родился он в Нортгемптоне, в дешёвом и опасном для чужаков райончике Берроуз.
            </p>
            <br>
            <a href="#" class="btn btn-danger">Read more</a>
        </div>
        <div class="col-lg-6">
            <br>
            <img src="img/news/news31.jpg" class="img-fluid">
        </div>

    </div>
</div>
<hr>

<%@include file="/WEB-INF/jspf/footer.jsp" %>
</body>
</html>
