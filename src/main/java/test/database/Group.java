package test.database;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Groups")
@TableGenerator(name="tab", initialValue=50, allocationSize=500)
public class Group{

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
    private long id;

    @NotNull
    private String Name;

    @NotNull
    private int year;


    private long getId(){
        return this.id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


}
