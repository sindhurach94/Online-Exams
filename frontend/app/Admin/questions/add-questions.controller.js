function QuestionsController(questionsService, $route) {
    var vm = this;


    vm.$onInit = $onInit;
    vm.newQuestion = newQuestion;
    vm.refreshQuestions = refreshQuestions;

    function $onInit() {

        refreshQuestions();
        vm.xamId = $route.current.params.examId;
        console.log(vm.xamId);

    }

    function refreshQuestions() {
        return questionsService.list().then(function refreshedquestions(response) {
            vm.questions = response.data;
        });
    }
    //to create new questions
    function newQuestion()
    {

        if(!vm.questionname)
            return;
        if(vm.questions.indexOf(vm.questionname)==-1)
        {

           questionsService.create(vm.questionname);
            refreshQuestions();
            vm.questionname ="";
        }
        else{
            return;
        }
    }

}