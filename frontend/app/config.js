function config($routeProvider)
{
    $routeProvider
        .when('/',{
            template:'<addquestion name="siva"></addquestion>'
        })
        .when('/register',{
            template:'<registration></registration>'
        })
        .when('/xam',{
            template:'<exams> </exams>'
        })
        //.when('/exam/:xamId', { template: '<addquestion test="$resolve.examId"></addquestion>', resolve: examResolver })
        .when('/xam/:examId/question', { template:'<questions></questions>' })
        .when('/xam/:eexamId/question/:questionId/choice', { template:'<choices></choices>' });

    console.log($routeProvider);
// http://fear.com/exam/6

}