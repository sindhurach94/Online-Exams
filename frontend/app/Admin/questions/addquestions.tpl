
<form class="text-center">

    <input type="text" ng-model="vm.questionname" title="asd" required placeholder="Enter the question">
    <button type="button" ng-click="vm.newQuestion()">Create Question</button>
</form><br />
<div class="container" class="table-responsive">
    <table class="table table-bordered" >
        <thead>
        <tr class="info">
            <th style="text-align: center"> Question Name</th>
            <th> </th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="questions in vm.questions" bgcolor="#d3d3d3">
            <td style="text-align: center"> {{questions.text}}</td>
            <td style="text-align: center"> <a class="btn btn-xs btn-default" href="#/xam/{{vm.xamId}}/question/{{questions.id}}/choice">+Choice</a></td>


    </table>
</div>
