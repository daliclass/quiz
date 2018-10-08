<template>
    <div>
        <question
            question="Success is not final, failure is not fatal: it is the courage to continue that counts."
            :answers="['Winston S. Churchill','Virginia Woolf','Albert Einstein','Anais Nin']"
        />
    </div>
</template>

<script>
    import Question from './Question.vue';

    export default {
        components: { Question }
    }
</script>