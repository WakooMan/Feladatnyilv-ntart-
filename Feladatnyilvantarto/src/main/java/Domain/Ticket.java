package Domain;


import java.time.LocalDateTime;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Ticket {

    private String name;
    private String description;
    private User assigner;
    private ArrayList<User> assignees;
    private final LocalDateTime date;
    private LocalDateTime deadline;
    private boolean checkbox;
    private final Group group;
    private final Priority priority;
    private final ArrayList<Comment> comments;
    private final ArrayList<TimeMeasure> timeMeasures;
    public Ticket(Group group, Priority priority) {
        this.group= group;
        this.priority=priority;
        this.comments = new ArrayList<>();
        this.timeMeasures = new ArrayList<>();
        this.date=LocalDateTime.now();
    }

    public Group getGroup() {
        return group;
    }

    public Priority getPriority() {
        return priority;
    }

    public ArrayList<Comment> getComments() {
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

    public ArrayList<User> getAssignees() {
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

    public ArrayList<TimeMeasure> getTimeMeasures(){
        return timeMeasures;
    }
    public ArrayList<TimeMeasure> getUserTimeMeasures(User user){
        ArrayList<TimeMeasure> userTimeMeasure = new ArrayList<>();
        for (TimeMeasure time : getTimeMeasures()){
            if (time.getUser().equals(user)){
                userTimeMeasure.add(time);
            }
        }
        return userTimeMeasure;
    }

    public void addAssignee(User assignee){
        getAssignees().add(assignee);
    }
    public void deleteAssignee(User assignee){
        getAssignees().remove(assignee);
    }


}
