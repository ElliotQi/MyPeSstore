package org.csu.mypetstore.domain;

import java.io.Serializable;

public class Mylog implements Serializable {
    private String username;
    private String currenttime;
    private String behavior;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) { this.username = username; }

    public String getCurrenttime() {
        return currenttime;
    }
    public void setCurrenttime(String currenttime) { this.currenttime = currenttime; }

    public String getBehavior() {
        return behavior;
    }
    public void setBehavior(String behavior) { this.behavior = behavior; }
}
