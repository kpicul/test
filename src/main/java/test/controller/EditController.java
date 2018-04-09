package test.controller;

import test.Helper;
import test.ManagedUser;
import test.database.Member;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EditController  {
    @Inject
    private ManagedUser mu;

    private Member user;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    //private Date dateOfBirth;

    @PostConstruct
    public void postConstruct(){
        user=mu.getForUsername("test");
        //System.out.println("POSTCONSTRUCT");
        firstName=user.getFirstName();
        lastName=user.getLastName();
        userName=user.getUsername();
        password=user.getPassword();
        //int as=777;
        //dateOfBirth=user.getDateOfBirth();*/
        System.out.println("test423");
    }

    public void confirm(){
        // Member user=mu.getForUsername("test");
    //    System.out.println("USERNAME: "+userName);
        mu.updateFirstName(user,firstName);
        mu.updateLasttName(user,lastName);
        mu.updateUserName(user,userName);
        mu.updatepassword(user,password);
        //mu.updateDateOfBirth(user,dateOfBirth);
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
        this.password=Helper.getSHA(password,user.getUsername());
        //this.password=password;
    }

    /*public Date getDateOfBirth() {
        return dateOfBirth;
    }*/

    /*public void setDateOfBirth(String dateOfBirth) {
        DateFormat df = new SimpleDateFormat("dd.mm.yyyy");
        try {
            this.dateOfBirth = new java.sql.Date(df.parse(dateOfBirth).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/




}
