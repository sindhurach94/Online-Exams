function ChoiceFormController(choiceService) {
    var vm = this;

    vm.$onInit = $onInit;
    vm.choiceWasSubmitted = choiceWasSubmitted;
    vm.removeItem = removeItem;
    vm.submitQuestion = submitQuestion;
    vm.refreshF = refreshF;

    function $onInit() {
        vm.choices = [];
    }

    function choiceWasSubmitted() {
        console.log("A choice was submitted. The text of it is: " + vm.choiceText);

        vm.errortext = "";
        //vm.choices.push(vm.choiceText)

        if (!vm.choiceText) {
            return;
        }
        if (vm.choices.indexOf(vm.choiceText) == -1) {
            vm.choices.push(vm.choiceText);
           //( alert(vm.choices);
            vm.choiceText = "";
            vm.choiceList = "hyf";
        } else {
            vm.errortext = "The choice is already in your list.";
            vm.choiceText = "";
        }
    }
    function submitQuestion(){
        if(!vm.answer)
        {return;}
        if(vm.choices.indexOf(vm.answer)== -1)
            alert("Please include the answer in the list of choices..");

        else
        {
            alert("You have chosen answer "+vm.answer+"for the questions "+vm.question+"");
            vm.answer = vm.choices.indexOf(vm.answer);
          //choiceService.update(vm.questions, vm.choices, vm.answer).then(vm.refreshF())
            vm.refreshF();
        }

    }
    function removeItem(x){
            vm.errortext = "";
            vm.choices.splice(x, 1);
        }
    function refreshF(){
        vm.question = "";
        vm.choices = [];
        vm.answer= "";
        vm.choiceText ="";
    }
    // this is just for basic understanding
    /*function $onInit() {
        console.log("A new instance of ChoiceFormController has been created.")
        choiceService.list().then(function(response) {
            //console.log("got", response);
            vm.choices = response.data;
        })
    }*/
}