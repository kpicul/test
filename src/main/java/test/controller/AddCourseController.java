package test.controller;

import test.DatabaseQuerries;
import test.Session;
import test.TestStateful;
import test.database.Member;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@ViewScoped
@Named
public class AddCourseController implements Serializable {
    @Inject
    private DatabaseQuerries db;

    @Inject
    private Session session;

    private Member user;

    private String name;

    private String description;

    @PostConstruct
    public void postConstruct(){
        user=session.getUser();
        emptyRedirect();
        checkRole();
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

    public void addCourse(){
        db.addCourse(this.name,this.description);
    }

    private void emptyRedirect(){
        if(user==null){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkRole(){
        if(!user.getRoleId().getName().equals("Admin")){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("warning.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
