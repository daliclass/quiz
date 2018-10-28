import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://0.0.0.0:7001',
    withCredentials: false,
    headers: {
    }
});

export default {
    newGame: function() {
        return apiClient.get("/game/new");
    },
    answerQuestion: function(body) {
        return axios({
            method:'post',
            url:  'http://0.0.0.0:7001' + "/question/answer",
            data: body
        })
    },
    gameSummary: function(body) {
        return apiClient.post("/game/summary", body);
    }
}