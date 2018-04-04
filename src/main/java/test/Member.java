package test;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Named
public class Member
{
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String password;

    @NotNull
    private String username;

    public Long getId(){
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
        password=Helper.toHash(password);
    }
}
