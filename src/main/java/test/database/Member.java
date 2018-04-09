package test.database;

import test.Helper;

import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Named
//@Table(name="Member")
public class Member
{
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String password;

    @NotNull
    @Column(unique = true)
    private String username;

    private Date dateOfBirth;

    @NotNull
    @OneToOne
    @JoinColumn(name="role_id",referencedColumnName = "id")
    private Role roleId;

    public long getId(){
        return this.id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName(){
        return this.firstname;
    }

    public String getLastName() {
        return this.lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id){
        this.id=id;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setDateOfBirth(Date date){
        this.dateOfBirth=date;
    }
    public Date getDateOfBirth(){
        return this.dateOfBirth;
    }
    public void setRoleId(Role id){
        this.roleId=id;
    }

    public Role getRoleId(){
        return this.roleId;
    }
}