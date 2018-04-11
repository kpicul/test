package test.database;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String name;

    private String description;

    @ManyToMany
    @JoinTable(name="Teaches",
    joinColumns = @JoinColumn(name="course_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="member_id", referencedColumnName = "id"))
    private List<Member> teachers;

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
