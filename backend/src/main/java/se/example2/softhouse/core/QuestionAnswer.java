package se.example2.softhouse.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by charan on 9/6/2016.
 */
public class QuestionAnswer {
    @JsonProperty
    private long id;

    @JsonProperty
    @NotEmpty
    private long questionId;

    @JsonProperty
    @NotEmpty
    private long choiceId;

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getQuestionId() {
        return questionId;
    }


    public long getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(long choiceId) {
        this.choiceId = choiceId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
