package engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class QuizQuestion {

    @NotBlank(message = "Title is mandatory")
    @Column
    private String title;

    @NotBlank(message = "Text is mandatory")
    @Column
    private String text;

    @Size(min = 2)
    @Column
    private String[] options;

    @Column
    private int[] answer;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    public QuizQuestion() {
//        id = 0;
        title = "";
        text = "";
        options = new String[] {};
        answer = new int[] {};
    }

    public QuizQuestion(String title, String text, String[] options, int[] answer) {
//        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    @JsonProperty(value = "answer")
    public int[] getAnswer() {
        return answer;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }

//    public void setAnswer(int answer) {
//        this.answer = new int[] { answer };
//    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
