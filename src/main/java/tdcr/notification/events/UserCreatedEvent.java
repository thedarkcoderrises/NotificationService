package tdcr.notification.events;

public class UserCreatedEvent {
    public UserCreatedEvent (){}

    private String userId;

    public UserCreatedEvent(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "userId: "+userId;
    }
}
