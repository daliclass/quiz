<template>
    <section>
        <h1> SUMMARY </h1>
        <h2> {{ score }} / {{ maxScore }} </h2>
    </section>
</template>

<script>
    export default {
        props: {
            'maxScore': Number,
            'score': Number
        },
        data: function() {
            return {};
        }
    }
</script>
