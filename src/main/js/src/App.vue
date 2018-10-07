<template>
<div id="quiz-shell">
    <header id="control-bar">
        <h2 id="logo"> QQuiz </h2>
        <nav id="actions">
            <ul>
                <li><a> Road map </a></li>
            </ul>
        </nav>
    </header>
    <section id="content">
        <h1> Quote Quiz </h1>
        <p>
            A simple quiz to guess who said the quote!
        </p>
        <a class="button">
            play now
        </a>
    </section>
</div>
</template>

<style>
    html, body {
        height: 100%;
        margin: 0;
    }

    #quiz-shell {
        margin: 0;
        height: 100%;
        background: linear-gradient(15deg, #1B1464, #0652DD);
    }

    h1, h2, h3, p, a {
        font-family: "Lucida Sans Unicode", "Lucida Grande", sans-serif;
        color: #fff;
    }

    #logo {
        margin: 0;
        display: inline-block;
    }

    #actions {
        display: inline-block;
        float: right;
    }

    #actions ul {
        list-style-type: none;
    }

    #actions ul li {
        list-style-type: none;
        display: inline;
    }

    #control-bar {
        min-height: 5%;
        max-height: 10%;
        padding-left: 1em;
        padding-right: 1em;
    }

    #content {
        text-align: center;
        padding-top: 13%;
    }

    .button {
        background-color: none;
        padding: 2em;
        margin: 2em;
        border-width: 2px;
        border-color: #fff;
        border-style: solid;
        display: inline-block;
    }
</style>
