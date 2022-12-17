package hu.elte.feladatnyilvantarto.webdomain.form;

import hu.elte.feladatnyilvantarto.domain.User;

public class AddGroupRequest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
