package sample.java;

public class EmployeeHasPosition {
    private Employee employeeId;
    private Position positionId;

    public EmployeeHasPosition() {
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Position getPositionId() {
        return positionId;
    }

    public void setPositionId(Position positionId) {
        this.positionId = positionId;
    }

    public EmployeeHasPosition(Employee employeeId, Position positionId) {
        this.employeeId = employeeId;
        this.positionId = positionId;
    }
}
