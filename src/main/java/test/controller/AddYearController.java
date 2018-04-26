package test.controller;

import test.DatabaseQuerries;
import test.Session;
import test.database.Member;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;

@ViewScoped
@Named
public class AddYearController implements Serializable {
    @Inject
    private DatabaseQuerries db;

    @Inject
    private Session session;

    private String yearName;

    private Member user;

    private Date startDate;

    private Date endDate;

    @PostConstruct
    public void postConstruct(){
        user=session.getUser();
        emptyRedirect();
        checkRole();
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void addYear(){
        db.addYear(yearName,startDate,endDate);
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
