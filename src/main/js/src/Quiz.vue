<template>
    <div>
        <question v-show="!finished"
            :question=turn.quote
            :answers=turn.potentialAnswers
            :answerQuestion=answerQuestion
        />
        <ScoreSummary v-show="finished"
            :maxScore=score.maxScore
            :score=score.score
        />
    </div>
</template>

<script>
    import Question from './Question.vue';
    import ScoreSummary from './ScoreSummary.vue';
    import QuizService from './QuizService.js';

    export default {
        data: function() {
            return {
                turn: {
                    quote: "quote",
                    potentialAnswers: ["foo"]
                },
                score: {
                    maxScore: 0,
                    score: 0
                }
            }
        },
        created: function() {
            let vm = this;
            QuizService
                .newGame()
                .then(
                    function(response) {
                        vm.turn = response.data;
                    }
                )
        },
        components: { Question, ScoreSummary },
        methods: {
            answerQuestion: function(answerQuestion) {
                const that = this;

                console.log(that.turn.questionNumber)

                QuizService
                    .answerQuestion({'gameUUID': that.turn.gameUUID, 'answer': answerQuestion})
                    .then(
                        function(response) {
                            that.turn = response.data;
                            if ( that.finished ) {
                                QuizService.gameSummary({'gameUUID': that.turn.gameUUID})
                                    .then(function(response){
                                        that.score = response.data
                                        console.log(response.data)
                                    });
                            }
                        }
                    );
            }
        },
        computed: {
            finished: function() {
                return this.turn.questionNumber == this.turn.numberOfQuestions;
            }
        }
    }
</script>