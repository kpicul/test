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



    @ManyToOne
    @JoinColumn(name="groupcourse_id",referencedColumnName = "id")
    private Groupcourse groupcourseid;



    @Column(columnDefinition = "boolean default 'false'")
    private boolean finished;

    public long getId(){
        return this.id;
    }

    public Member getStudentId(){
        return this.studentId;
    }

    public void setStudentId(Member memid){
        this.studentId=memid;
    }


    public boolean isFinished(){
        return this.finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Groupcourse getGroupcourseid() {
        return groupcourseid;
    }

    public void setGroupcourseid(Groupcourse groupcourseid) {
        this.groupcourseid = groupcourseid;
    }
}
