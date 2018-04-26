package test.database;

import test.Helper;

import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.sql.Date;
import java.util.List;

@Entity

@TableGenerator(name="tab", initialValue=50, allocationSize=500)
@Named
//@Table(name="Member")
public class Member
{
    @Id
    //@GeneratedValue
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
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

    @Past(message = "Birth date must be in the past")
    private Date dateOfBirth;


    @NotNull
    @ManyToOne
    @JoinColumn(name="role_id",referencedColumnName = "id")
    private Role roleId;





    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id=id;
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
