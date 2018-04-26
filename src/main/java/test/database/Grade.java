package test.database;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@TableGenerator(name="tab", initialValue=50, allocationSize=500)
public class Grade {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
    private long id;

    @NotNull
    @Min(value=1, message = "Grade can't be lower than 1")
    @Max(value=5, message = "Grade can't be higher than 5")
    private int grade;

    @NotNull
    @ManyToOne
    @JoinColumn(name="performance_id",referencedColumnName = "id")
    private Performance performanceId;

    public long getId(){
        return this.id;
    }

    public int getGrade(){
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Performance getPerformanceId(){
        return this.performanceId;
    }

    public void setPerformanceId(Performance performanceId){
        this.performanceId=performanceId;
    }
}
