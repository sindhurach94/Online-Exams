function registerController(registerService) {
    vm.$onInit = $onInit;
    vm.validate = validate;
    function validate() {
        if (document.Registration.password.value.length <= 7) {
            alert("password should be minimum 8 characters");
            return;
        }
        var email = document.Registration.emailid.value;
        atpos = email.indexOf("@");
        dotpos = email.lastIndexOf(".");
        if (email == "" || atpos < 1 || ( dotpos - atpos < 2 )) {
            alert("Please enter correct email ID");
            return false;
        }

        if (( Registration.ocuupation[0].checked == false ) && ( Registration.occupation[1].checked == false )) {
            alert("Please choose your occupation: teacher or student ");
            return false;
        }
    }

}