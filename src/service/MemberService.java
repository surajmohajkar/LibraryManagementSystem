package service;

import model.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberService {
    private final List<Member> members;
    public MemberService(){
        members = new ArrayList<>();
        System.out.println("MemberService Initialized Successfully");
    }
    public List<Member> getMembers(){
        return members;
    }
}
