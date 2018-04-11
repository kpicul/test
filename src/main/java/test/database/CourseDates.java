package test.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
public class courseDates {
    @Id
    private long id;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;


    @OneToOne
    private Year yearId;

    public long getId(){
        return this.id;
    }

    public Date getStartDate(){
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate(){
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
