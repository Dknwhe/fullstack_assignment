package se.ecutb.cai.fullstack_todo.dto;


import javax.validation.constraints.*;


public class TodoItemForm {

    @NotBlank(message = "Title*")
    @Size(min = 2, message = "Minimum 2 letters")
    private String itemTitle;

    @NotBlank(message = "Description*")
    private String description;

    @NotBlank
    private String deadline;

    private boolean doneStatus = false;

    @DecimalMin(value = "0.0", inclusive = false)
    private double reward;


    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean isDoneStatus() {
        return doneStatus;
    }

    public void setDoneStatus(boolean doneStatus) {
        this.doneStatus = doneStatus;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

}
