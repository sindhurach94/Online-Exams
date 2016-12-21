package se.example2.softhouse.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by charan on 9/7/2016.
 */
public class Qfull {
   @JsonProperty
   private long questionId;

    @JsonProperty
    private String questiontext;

   @JsonProperty
    private List<Choice> choices;

    @JsonProperty
    private long selectedId;

    @JsonProperty
    private long userId;


    public Qfull()
    {

    }

    public  Qfull(Question question,List<Choice> choices){

        this.questionId=question.getId();
        this.questiontext=question.getText();
        this.choices = choices;

    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setQuestiontext(String questiontext) {
        this.questiontext = questiontext;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getQuestiontext() {
        return questiontext;
    }


    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }


    public long getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(long selectedId) {
        this.selectedId = selectedId;
    }


}
