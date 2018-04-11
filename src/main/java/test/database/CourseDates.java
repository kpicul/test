package test.database;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
public class CourseDates {
    @Id
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="course_id",referencedColumnName = "id")
    private Course courseId;

    @NotNull
    @ManyToOne
    @JoinColumn(name="year_id",referencedColumnName = "id")
    private Year yearId;

    public long getId(){
        return this.id;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public Year getYearId() {
        return yearId;
    }

    public void setYearId(Year yearId) {
        this.yearId = yearId;
    }
}
