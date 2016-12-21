<form class="text-center">
    <input type="text" ng-model="vm.choicetext" title="asd" required placeholder="Enter the question">
    <button type="button" ng-click="vm.newChoice()">Add Choice</button>
    <button type="button" ng-click="vm.answerChoice()" ng-disabled="vm.btndisable">Add answer Choice</button>
</form>
<div class="table-responsive">
    <table class="table table-bordered" >
        <thead>
        <tr class="info">
            <th style="text-align: center"> Choices</th>
            <th> </th>
        </tr>
        </thead>
        <tr>
        <tr ng-repeat="choice in vm.choices" bgcolor="#d3d3d3">
            <td style="text-align: center"> {{choice.text}}</td>
            <td style="text-align: center"> <a class="btn btn-xs btn-default">Edit</a></td>
        </tr>
        <tr><td colspan="2">Add new choice</td></tr>
    </table>
</div>