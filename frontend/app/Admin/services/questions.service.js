
function questionsService($http, $interpolate, $route) {
    /**
     * This is the base URL to our API in the backend.
     * @type {String}
     */
    var toListURL = 'http://localhost:8080/api/exam/'+$route.current.params.examId+'/question';

    /**
     * This is the detail URL to our API in the backend. It handles requests with path parameters.
     * E.g. /api/note/5/
     * @type {Function}
     */
    var toDetailURL = $interpolate('http://localhost:8080/api/exam/'+$route.current.params.examId+'/question');


    return {
        list: list,
        create: create

    };

    /**
     * Sends a GET request to the API.
     * @returns {Object}
     */
    function list() {
        return $http.get(toListURL);
    }

    /**
     * Sends a POST request to the API
     * @returns {Object}
     */
    function create(text) {
        return $http.post(toListURL, { text: text });
    }

}