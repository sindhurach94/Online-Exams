function ChooseQuestion() {
    var am = this;
    var q = [];

    am.$onInit = $onInit;
    am.chosenQuestion = chosenQuestion;

    function $onInit() {
        am.db_questions=["asd?", "das?"];
        am.multipleSelectt = [];
    }
    
    function chosenQuestion(q) {
        alert("hello");
        alert(q);
        am.multipleSelectt.push(q);
        alert(am.multipleSelectt);
    }

}