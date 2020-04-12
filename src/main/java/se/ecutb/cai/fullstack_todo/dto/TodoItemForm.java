package se.ecutb.cai.fullstack_todo.dto;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class TodoItemForm {

    @Positive
    private int itemId;

    @NotBlank(message = "Title*")
    @Size(min = 2, message = "Minimum 2 letters")
    private String itemTitle;

    @NotBlank(message = "Description*")
    private String description;

    @FutureOrPresent(message = "Required*")
    private LocalDate deadline;

    private boolean doneStatus;

    @DecimalMin(value = "0.0", inclusive = false)
    private double reward;

    public int getItemId() {
        return itemId;
    }

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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
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
