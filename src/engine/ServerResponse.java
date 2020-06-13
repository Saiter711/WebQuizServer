package engine;

public class ServerResponse {
    private String feedback;
    private boolean success;

    public ServerResponse() {

    }

    public ServerResponse(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public String getFeedback() {
        return feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
