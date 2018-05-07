package test.controller;


import test.DatabaseQuerries;
import test.Helper;
import test.database.Member;
import test.database.Role;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Named
@ViewScoped
public class AddUserController implements Serializable {

    @Inject
    private DatabaseQuerries db;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Date dateOfBirth;
    private String role;

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

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password) {
        if(!password.equals("")){
            this.password = Helper.getSHA(password, username);
        }
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void addUser(){
        Member user=new Member();
        user.setPassword(password);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLastName(lastName);
        user.setDateOfBirth(dateOfBirth);
        user.setRoleId(db.getRoleByname(role));
        db.createUser(user);
    }
}
