package test.database;

import javax.persistence.*;

@Entity
@TableGenerator(name="tab", initialValue=50, allocationSize=500)
public class Teaches {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
    private long id;

    @ManyToOne
    @JoinColumn(name="member_id",referencedColumnName = "id")
    private Member memberid;


    @ManyToOne
    @JoinColumn(name="course_id",referencedColumnName = "id")
    private Course course;

    @ManyToOne
    @JoinColumn(name="year_id",referencedColumnName = "id")
    private Year yearid;

    public Member getMemberid() {
        return memberid;
    }

    public void setMemberid(Member memberid) {
        this.memberid = memberid;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Year getYearid() {
        return yearid;
    }

    public void setYearid(Year yearid) {
        this.yearid = yearid;
    }
}
