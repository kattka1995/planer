package common;


import static common.TaskStatusesInterface.NOT_DONE;

public class Task {
    private int id;
    private String description;
    private String status;

    public Task(String description) {
        this.status = NOT_DONE;
        this.description = description;

    }
    public Task(){}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
