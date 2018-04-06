package test.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Performance {

    @Id
    @GeneratedValue
    private long id;

    private long memberId;

    private long courseId;

    private long yearId;

    private int studyYear;

    public long getId(){
        return this.id;
    }

    public long getMemberId(){
        return this.memberId;
    }

    public void setMemberId(Long memid){
        this.memberId=memid;
    }

    public long getCourseId(){
        return this.courseId;
    }

    public void setCourseId(Long courseId){
        this.courseId=courseId;
    }

    public long getYearId(){
        return this.yearId;
    }

    public void setYearId(Long yearId){
        this.yearId=yearId;
    }

    public int getStudyYear(){
        return this.studyYear;
    }

    public void setStudyYear(int studyYear){
        this.studyYear=studyYear;
    }
}
