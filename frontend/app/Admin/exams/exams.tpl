
<form class="text-center" style="text-align: right">

    <input type="text" ng-model="vm.examname" title="asd" required placeholder="Enter the Exam title">
    <button type="button" ng-click="vm.newExam()">Create Exam</button>

</form><br />
<div class="container" class="table-responsive">
<table class="table table-bordered" >
    <thead>
    <tr class="info">
        <th style="text-align: center; font-size: larger" > Exam Name</th>
        <th> </th>
    </tr>
    </thead>
    <tbody>
    <tr ng-repeat="exam in vm.exams" bgcolor="#d3d3d3">
    <td style="text-align: center"> {{exam.text}}</td>
    <td style="text-align: center;"> <a class="btn btn-xs btn-default" style=" font-size: medium " href="#/xam/{{exam.id}}/question">+Question</a ></td>
    </tr>
    </tbody>
</table>
</div>
