package hu.elte.feladatnyilvantarto.webdomain;

public class CurrentTicket {
    private String url;
    private String name;

    public CurrentTicket(){}
    public CurrentTicket(String name,String url)
    {
        this.name=name;
        this.url=url;
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
