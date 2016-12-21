function ChoiceController(choiceService, $route) {
    var vm = this;
    var match=0;
    var correctchoiceId = -1;
    vm.$onInit = $onInit;
    vm.newChoice = newChoice;
    vm.refreshChoices = refreshChoices;
    vm.answerChoice = answerChoice;

    function $onInit() {
       var i=0;
        correctchoiceId = choiceService.viewAnswer(vm.examId, vm.questionId);
        if (correctchoiceId>0)
            vm.btndisable = true;
        else
            vm.btndisable = false;
       vm.examId = $route.current.params.eexamId;
        vm.questionId = $route.current.params.questionId;
        refreshChoices();
        choiceService.correctChoice( vm.examId, vm.questionId).then(function refreshedchoices(response) {
            vm.correctChoice = response.data;
            console.log(vm.correctChoice);
        });
    }

    function refreshChoices() {
        return choiceService.list( vm.examId, vm.questionId).then(function refreshedchoices(response) {
            vm.choices = response.data;
            console.log(vm.choices);
        });
    }
    function answerChoice() {
        return choiceService.correctChoice(vm.choicetext, vm.examId, vm.questionId)
            .then(function () {
            refreshChoices();
                return;})
            .catch(function () {
                alert('answer is already given...');
            })

    }
    //to create new choices
    function newChoice()
    {

        return choiceService.create(vm.choicetext, vm.examId, vm.questionId)
            .then(function(){
                refreshChoices();
                return;})
            .catch(function() {
                alert('That choice already exists.');
            });
    }
}