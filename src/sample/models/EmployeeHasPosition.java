package sample.models;

public class EmployeeHasPosition {
    private User userId;
    private Position positionId;

    public EmployeeHasPosition() {
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Position getPositionId() {
        return positionId;
    }

    public void setPositionId(Position positionId) {
        this.positionId = positionId;
    }

    public EmployeeHasPosition(User userId, Position positionId) {
        this.userId = userId;
        this.positionId = positionId;
    }
}
