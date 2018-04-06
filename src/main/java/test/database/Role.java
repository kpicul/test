package test.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    public int getId(){
        return this.id;
    }
}
