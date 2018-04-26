package test.database;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Entity
@TableGenerator(name="tab", initialValue=50, allocationSize=500)
public class Course {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
    private long id;

    @NotNull
    private String name;

    private String description;



    public long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String desc){
        this.description=desc;
    }




}
