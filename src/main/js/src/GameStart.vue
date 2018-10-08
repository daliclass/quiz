<template>
    <div>
        <h1> Quote Quiz </h1>
        <p>
            A simple quiz to guess who said the quote!
        </p>

        <router-link class="button" :to="{name:'quiz'}">
            play now
        </router-link>
    </div>
</template>

<style>
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