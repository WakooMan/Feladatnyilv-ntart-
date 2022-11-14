package Domain;

import java.time.LocalDateTime;

public class Comment {
private User from;
private Ticket in;
private String message;
private LocalDateTime date;

public Comment(User from,Ticket in,String message)
{
    this.from = from;
    this.in = in;
    this.message = message;
    date = LocalDateTime.now();
}

public User getFrom()
{
    return from;
}
public void setFrom(User usr)
{
    from = usr;
}

public Ticket getIn()
{
    return in;
}
public void setIn(Ticket tck)
{
    in = tck;
}

public String getMessage()
{
    return message;
}

public void setMessage(String msg)
{
    message = msg;
}
public LocalDateTime getDate()
{
    return date;
}
public void setDate(LocalDateTime date)
{
    this.date = date;
}

}
