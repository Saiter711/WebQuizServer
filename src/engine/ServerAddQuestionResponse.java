package engine;

public class ServerAddQuestionResponse {
    private int id;
    private String title, text;
    private String[] options;

    public ServerAddQuestionResponse(int id, String title, String text, String[] options) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options.clone();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
