<template>
    <section>
        <h2> "{{ question }}" </h2>
        <a v-for="answer in answers" class="answer" @click="answerQuestion(answer)"> {{ answer }} </a>
    </section>
</template>

<script>
    export default {
        props: {
            'question': String,
            'answers': Array,
            'answerQuestion': Function
        },
        data: function() {
            return {};
        }
    }
</script>

<style>
    .answer {
        background-color: none;
        padding: 2em;
        margin: 2em;
        border-width: 2px;
        border-color: #fff;
        border-style: solid;
        display: inline-block;
    }
</style>

