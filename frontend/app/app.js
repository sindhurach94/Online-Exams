angular.module('app', ["ngRoute"])
    .factory("choiceService", choiceService)
    .factory("examsService", examsService)
    .factory("questionsService",questionsService)

    //choosing the old_questions form
    .component('choosequestion', {
        templateUrl: 'app/old_questions/choosequestion.tpl',
        controller: ChooseQuestion,
        controllerAs: 'am',

        bindings: {
            data: '@'
        }
    })
    // addquestionForm becomes <old_questions> in HTML
    .component('addquestion', {
        templateUrl: 'app/old_questions/add-questions.tpl',
        controller: ChoiceFormController,
        controllerAs: 'vm',

        bindings: {
            name: '@',
            test: '@'
        }
    })
    .component('exams', {
        templateUrl: 'app/Admin/exams/exams.tpl',
        controller: ExamsController,
        controllerAs: 'vm'
    })
    .component('questions',{
        templateUrl: 'app/Admin/questions/addquestions.tpl',
        controller: QuestionsController,
        controllerAs: 'vm'

    })
    .component('choices',{
        templateUrl: 'app/Admin/choices/add-choices.tpl',
        controller: ChoiceController,
        controllerAs: 'vm'
    })
    .component('registration',{
        templateUrl: 'app/Admin/Registration/registration.tpl',
        controller: registerController,
        controllerAs: 'vm'
    })
    .config(config);