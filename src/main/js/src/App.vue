<template>
<div id="quiz-shell">
    <header id="control-bar">
        <h2 id="logo"> QQuiz </h2>
        <nav id="actions">
            <ul>
                <li><a href="google.com"> Play Game </a></li>
                <li><a> Road map </a></li>
            </ul>
        </nav>
    </header>
    <section id="content">
        <h1> Quote Quiz </h1>
        <p>
            A simple quiz to guess who said the quote!
        </p>
        <button>
            Play Quiz
        </button>
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
        color: #ffffff;
    }

    #logo {
        margin: 0;
        display: inline-block;
    }

    #actions {
        display: inline-block;
        float: right;
        padding-right: 2em;
    }

    #actions ul {
        list-style-type: none;
    }

    #actions ul li {
        list-style-type: none;
        display: inline;
        padding-left: 1em;
    }

    #control-bar {
        min-height: 5%;
        max-height: 10%;
    }

    #content {
        min-height: 90%;
        max-height: 90%;
    }
</style>
