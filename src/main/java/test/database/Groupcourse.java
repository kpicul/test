package test.database;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@TableGenerator(name="tab", initialValue=50, allocationSize=500)
public class Groupcourse {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="teaches_id",referencedColumnName = "id")
    private Teaches teachesid;

    @NotNull
    @ManyToOne
    @JoinColumn(name="group_id",referencedColumnName = "id")
    private Group groupid;

    public long getId() {
        return id;
    }

    public Teaches getTeachesid() {
        return teachesid;
    }

    public void setTeachesid(Teaches teachesid) {
        this.teachesid = teachesid;
    }

    public Group getGroupid() {
        return groupid;
    }

    public void setGroupid(Group groupid) {
        this.groupid = groupid;
    }
}
