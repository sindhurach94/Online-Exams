<div class="total_form" style="background-color:white;">
            <div>
                <form>

                        <label for="multipleSelect">List of questions: </label><br>
                        <select name="multipleSelect" id="multipleSelect" ng-model="data.multipleSelect" multiple >

                                <option style="border: 1px solid black;  width:250px; text-decoration:none;"  ng-repeat="x in am.db_questions" >{{x}}</option>

                        </select><br>
                        multipleSelect = {{data.multipleSelect}}

                <div style="text-align: right">
                    <button ng-click="am.chosenQuestion(data.multipleSelect)">addd</button>

                </div>
            </form>

            {{am}}
        </div>
</div>