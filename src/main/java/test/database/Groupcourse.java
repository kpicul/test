package test.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Groupcourse {
    @Id
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
