package test;

import test.database.Member;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;


@SessionScoped
public class Session implements Serializable {
    private Member user;

    public Member getUser(){
        return this.user;
    }

    public void setUser(Member user){
        this.user=user;
    }
}
