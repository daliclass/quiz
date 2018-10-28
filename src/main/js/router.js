import Vue from 'vue';
import Router from 'vue-router';
import GameStart from './src/GameStart.vue';
import RoadMap from './src/RoadMap.vue';
import Quiz from './src/Quiz.vue';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            name: 'quiz-start',
            component: GameStart
        },
        {
            path: '/quiz',
            name: 'quiz',
            component: Quiz
        },
        {
            path: '/road-map',
            name: 'road-map',
            component: RoadMap
        }
    ]
});