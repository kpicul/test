package test;

import test.database.Member;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class Session implements Serializable {
    private Member user;
}
