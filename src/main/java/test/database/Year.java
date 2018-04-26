package test.database;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@TableGenerator(name="tab", initialValue=50, allocationSize=500)
public class Year {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
    private long id;

    @NotNull
    private String year;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    public long getId(){
        return this.id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
