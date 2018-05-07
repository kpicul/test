package test.controller;

import test.Helper;
import test.DatabaseQuerries;
import test.Session;
import test.database.Member;
import javax.annotation.PostConstruct;

import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
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
public class EditController  implements Serializable {
    @Inject
    private DatabaseQuerries mu;

    @Inject
    private Session session;

    private Member user;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private Date dateOfBirth;

    private String displayedDate;


    @PostConstruct
    public void postConstruct(){
        user=session.getUser();
        emptyRedirect();
        //System.out.println("POSTCONSTRUCT");
        firstName=user.getFirstName();
        lastName=user.getLastName();
        userName=user.getUsername();
        password=user.getPassword();
        //int as=777;
        dateOfBirth=user.getDateOfBirth();
    }

    public void confirm(){

        user.setUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        mu.updateFirstName(user,firstName);
        mu.updateLasttName(user,lastName);
        mu.updateUserName(user,userName);
        if(password!=null){
            mu.updatepassword(user,password);
            user.setPassword(password);
        }
        mu.updateDateOfBirth(user,dateOfBirth);
    }
    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName=firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        System.out.println("SET lastname: "+lastName);
        this.lastName=lastName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        //System.out.println("SET username: "+userName);
        this.userName = userName;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password) {
        //System.out.println("SET password: "+password);
        if(password!=""){
            this.password = Helper.getSHA(password, user.getUsername());
        }
        //this.password=password;
    }


    public String getDateOfBirth() {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String date=null;
        if(dateOfBirth != null){
            date=df.format(dateOfBirth);
        }
        return date;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.dateOfBirth = new java.sql.Date(df.parse(dateOfBirth).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public void back(){
        if(user.getRoleId().getName().equals("Admin")){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("admin1.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if(user.getRoleId().getName().equals("Student")){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("student1.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(user.getRoleId().getName().equals("Teacher")){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("teacher1.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
