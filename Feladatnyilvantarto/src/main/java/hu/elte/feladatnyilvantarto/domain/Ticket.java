package hu.elte.feladatnyilvantarto.domain;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    @ManyToOne(cascade = {CascadeType.MERGE})
    private User assigner;
    @ManyToMany(cascade = {CascadeType.MERGE})
    private List<User> assignees;
    private final LocalDateTime date;
    private LocalDateTime deadline;
    private boolean checkbox;
    @ManyToOne(cascade = {CascadeType.MERGE})
    private  Group group;
    private Priority priority;
    @OneToMany(orphanRemoval = true, mappedBy = "ticketIn")
    private  List<Comment> comments;
    @OneToMany(orphanRemoval = true, mappedBy="ticket")
    private List<TimeMeasure> timeMeasures;
    public Ticket(Group group, String priority) {
        this.group= group;
        this.priority=Priority.valueOf(priority);
        this.comments = new ArrayList<>();
        this.timeMeasures = new ArrayList<>();
        this.assignees = new ArrayList<>();
        this.date=LocalDateTime.now();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public Ticket() {
        this.date=LocalDateTime.now(); this.assignees = new ArrayList<>();
    }

    public Group getGroup() {
        return group;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(String priority){
        this.priority= Priority.valueOf(priority);
    }
    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment c){
        getComments().add(c);
    }
    public void deleteComment(Comment c){
        getComments().remove(c);
    }

    /**
     *
     * @param c Comment to be replaced
     * @param e New comment
     */
    public void replaceComment(Comment c, Comment e){
        getComments().add(getComments().indexOf(c), e);
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
    public User getAssigner() {
        return assigner;
    }
    public void setAssigner(User assigner) {
        this.assigner = assigner;
    }

    public List<User> getAssignees() {
        return assignees;
    }
    public void setAssignees(ArrayList<User> assignees) {
        this.assignees = assignees;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public List<TimeMeasure> getTimeMeasures(){
        return timeMeasures;
    }
    public List<TimeMeasure> getUserTimeMeasures(User user){
        List<TimeMeasure> userTimeMeasure = new ArrayList<>();
        for (TimeMeasure time : getTimeMeasures()){
            if (time.getUser().equals(user)){
                userTimeMeasure.add(time);
            }
        }
        return userTimeMeasure;
    }

    public void addAssignee(User assignee){
        assignees.add(assignee);
    }
    public void deleteAssignee(User assignee){
        assignees.remove(assignee);
    }


}
