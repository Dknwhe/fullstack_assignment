package se.ecutb.cai.fullstack_todo.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    @Column(unique = true)
    private String itemTitle;

    private String description;
    private LocalDate deadline;
    private Boolean doneStatus;
    private double reward;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    private AppUser assignee;


    public TodoItem(String itemTitle, String description, LocalDate deadline, Boolean doneStatus, double reward, AppUser assignee) {
        this.itemTitle = itemTitle;
        this.description = description;
        this.deadline = deadline;
        this.doneStatus = doneStatus;
        this.reward = reward;
        this.assignee = assignee;
    }

    public TodoItem() {
    }

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

    public Boolean getDoneStatus() {
        return doneStatus;
    }

    public void setDoneStatus(Boolean doneStatus) {
        this.doneStatus = doneStatus;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public AppUser getAssignee() {
        return assignee;
    }

    public void setAssignee(AppUser assignee) {
        this.assignee = assignee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return itemId == todoItem.itemId &&
                Double.compare(todoItem.reward, reward) == 0 &&
                Objects.equals(itemTitle, todoItem.itemTitle) &&
                Objects.equals(description, todoItem.description) &&
                Objects.equals(deadline, todoItem.deadline) &&
                Objects.equals(doneStatus, todoItem.doneStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemTitle, description, deadline, doneStatus, reward);
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "itemId=" + itemId +
                ", itemTitle='" + itemTitle + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", doneStatus=" + doneStatus +
                ", reward=" + reward +
                '}';
    }
}
