package model;

import model.utils.BugStatus;

public class Bug extends Entity<Integer> {
    private String name;
    private String description;
    private String status;
    private String fileAddress;

    public Bug(){}

    public Bug(String name, String description, String status, String fileAddress) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.fileAddress = fileAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    @Override
    public String toString() {
        return "Bug{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", fileAddress='" + fileAddress + '\'' +
                '}';
    }
}
