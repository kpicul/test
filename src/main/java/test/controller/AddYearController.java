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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    public String getStartDate() {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String date=null;
        if(startDate != null){
            date=df.format(startDate);
        }
        return date;
    }

    public void setStartDate(String startDate) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.startDate = new java.sql.Date(df.parse(startDate).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getEndDate() {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String date=null;
        if(endDate != null){
            date=df.format(endDate);
        }
        return date;
    }

    public void setEndDate(String endDate) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.endDate = new java.sql.Date(df.parse(endDate).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
