package test.output;

import test.database.Course;
import test.database.Member;
import test.database.Teaches;
import test.database.Year;

import java.util.List;

public class ResultTeaches {



    private Course course;

    private Member teacher;

    private Year year;

    private Teaches glue;

    public ResultTeaches(Object[]object){
        course=(Course)object[0];
        teacher=(Member)object[1];
        year=(Year)object[2];
        glue=(Teaches)object[3];
    }
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Member getTeacher() {
        return teacher;
    }

    public void setTeacher(Member teacher) {
        teacher = teacher;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Teaches getGlue() {
        return glue;
    }

    public void setGlue(Teaches glue) {
        this.glue = glue;
    }

    public String getCourseName(){
        return this.course.getName();
    }

    public String getYearYear(){
        return this.year.getYear();
    }

    public String getTeacherUserName(){
        return this.teacher.getUsername();
    }

    public String getTeacherName(){
        return this.teacher.getFirstName()+" "+this.teacher.getLastName();
    }

    public long getTeacherId(){
        return this.teacher.getId();
    }

    public long getYearId(){
        return this.year.getId();
    }

    public long getCourseId(){
        return this.course.getId();
    }

    public long getGlueId(){
        return this.glue.getId();
    }

}
