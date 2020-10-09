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

<h1><fmt:message key="AAAAAa" /></h1>

<!-- Slider-->
<div id="carousel" class="carousel slide" data-ride="carousel" id="slides">

    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="img/wal12.jpg">
            <div class="carousel-caption">
                <h1 class="display-2">Library</h1>
                <button type="button" class="btn btn-warning btn-lg">Enter</button>
            </div>
        </div>
    </div>
    <a class="carousel-control-prev" href="#carousel" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carousel" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>

<!--News-->
<div class="container-fluid padding">
    <br>
    <br>
    <div class="row padding">
        <div class="offset-lg-2 col-lg-4">
            <h2>ТОП-11 самых ожидаемых книг октября</h2>
            <p>Чем на улице холоднее, тем приятнее достать любимые шерстяные носочки с дыркой на пятке и залечь в берлоге, наслаждаясь осенним чтением.
                Тем более что в этом октябре издатели порадовали нас сразу несколькими громкими новинками. Сейчас расскажем!
                <br><br>
                Особенно счастье привалило любителям триллеров и детективов: новый Ю Несбё, новая Тана Френч, новая Лив Константин.
                Нам продолжать, или вы уже убежали заказывать книги в интернет-магазине? Если хочется чего-то умного и смешного, обратите внимание
                на «Гиперболу с половиной». Этот сборник мемов-комиксов от очень остроумной американской художницы Элли Брош писательница Элизабет Гилберт
                назвала «Книгой года», а Билл Гейтс – своим личным антидепрессантом. Мы близки к тому, чтобы с ними согласиться, ведь чем мрачнее новостная
                повестка, тем больше хочется читать что-то неглупое и обнадёживающее.
                <br><br>
                Впрочем, чем сто раз услышать, лучше перейти в нашу галерею и самим увидеть, какие новинки стоит выискивать глазами на полках магазинов в этом месяце.
                news.. </p>
            <br>
            <a href="#" class="btn btn-danger">Read more</a>
        </div>
        <div class="col-lg-6">
            <br>
            <img src="img/news/news1.jpg" class="img-fluid">
        </div>

    </div>
</div>
</body>
</html>
