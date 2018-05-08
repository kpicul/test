package test.controller;


import test.DatabaseQuerries;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class AddGroupController implements Serializable {
    @Inject
    private DatabaseQuerries db;

    private String groupName;
    private int groupYear;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupYear() {
        return groupYear;
    }

    public void setGroupYear(int groupYear) {
        this.groupYear = groupYear;
    }

    public void addGroup(){
        db.addGroup(groupName,groupYear);
    }
}
