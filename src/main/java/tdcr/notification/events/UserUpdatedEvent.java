package tdcr.notification.events;

public class UserUpdatedEvent {

    private String userId;
    private String name;

    public UserUpdatedEvent(){}

    public UserUpdatedEvent(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "userId: "+userId+",name: "+name;
    }
}
