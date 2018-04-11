package test.database;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Performance {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="student_id",referencedColumnName = "id")
    private Member studentId;

    @NotNull
    @ManyToOne
    @JoinColumn(name="teacher_id",referencedColumnName = "id")
    private Member teachertId;

    @ManyToOne
    @JoinColumn(name="cdates_id",referencedColumnName = "id")
    private CourseDates cdates_id;


    private int studyYear;

    public long getId(){
        return this.id;
    }

    public Member getStudentId(){
        return this.studentId;
    }

    public void setStudentId(Member memid){
        this.studentId=memid;
    }

    public Member getTeachertId() {
        return teachertId;
    }

    public void setTeachertId(Member teachertId) {
        this.teachertId = teachertId;
    }

    public int getStudyYear(){
        return this.studyYear;
    }

    public void setStudyYear(int studyYear){
        this.studyYear=studyYear;
    }

    public CourseDates getCdates_id() {
        return cdates_id;
    }

    public void setCdates_id(CourseDates cdates_id) {
        this.cdates_id = cdates_id;
    }
}
