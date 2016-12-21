package se.example2.softhouse.core;


        import com.fasterxml.jackson.annotation.JsonProperty;
        import org.hibernate.validator.constraints.NotEmpty;

        import java.util.ArrayList;
/**
 * Created by charan on 9/6/2016.
 */
public class Choice {
    @JsonProperty
    private Long id;

    @JsonProperty
    @NotEmpty
    private String text;

    @JsonProperty
    @NotEmpty
    private Long questionId;

    @JsonProperty
    @NotEmpty
    private  Long isCorrect;

    public Long getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Long isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }
}
