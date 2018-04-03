package test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Member")
public class Person
{
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;

    public Long getId(){
        return this.id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
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
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setPassword(String password){
        password=Helper.toHash(password);
    }
}
