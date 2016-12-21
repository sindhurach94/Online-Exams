package se.example2.softhouse.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by charan on 9/21/2016.
 */
public class StudentExam {
    @JsonProperty
    private Long id;

    @JsonProperty
    private long userId;

    @JsonProperty
    private long examId;

    @JsonProperty
    private long questionId;

    @JsonProperty
    private long selectedId;

    @JsonProperty
    private long marks;



    public StudentExam()
    {

    }
    public StudentExam(long userId,long examId,long questionId,long selectedId,long marks)
    {
        this.userId=userId;
        this.examId=examId;
        this.questionId=questionId;
        this.selectedId=selectedId;
        this.marks=marks;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public long getMarks() {
        return marks;
    }

    public void setMarks(long marks) {
        this.marks = marks;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(long selectedId) {
        this.selectedId = selectedId;
    }
}
