package hu.elte.feladatnyilvantarto.rest.domain;

public class NotificationResponse {
    private int id;
    private String type;
    private String message;
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationResponse(){}
    public NotificationResponse(int id,String type,String message,String time)
    {
        this.message=message;
        this.id = id;
        this.type= type;
        this.time=time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
