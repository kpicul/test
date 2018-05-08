package test;


import test.database.Member;

import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

@Stateful
public class TestStateful {

    private Member member;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
