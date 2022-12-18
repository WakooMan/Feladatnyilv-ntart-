package hu.elte.feladatnyilvantarto.webdomain.other;

public class CurrentTicket {
    private String url;
    private String name;


    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CurrentTicket(){}
    public CurrentTicket(String name,String url, int id)
    {
        this.name=name;
        this.url=url;
        this.id=id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
