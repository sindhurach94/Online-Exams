function ExamsController(examsService) {
    var vm = this;


    vm.$onInit = $onInit;
    vm.newExam = newExam;
    vm.refreshExams = refreshExams;

    function $onInit() {
    //vm.exams ={"id":33,"text":"q1"}
        vm.refreshExams();

    }
    function refreshExams() {
        return examsService.list().then(function refreshedExams(response) {
            vm.exams = response.data;
        });
    }
    //to create new exam
    function newExam()
    {
        //examService.get();
        if(!vm.examname)
            return;
        if(vm.exams.indexOf(vm.examname)==-1)
        {
            examsService.create(vm.examname);
            //vm.exams.push(vm.examname);
            refreshExams();
            vm.examname ="";
        }
        else{
            return;
        }
    }

}