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
    public boolean registerMember(Member newMember){
        for(Member existingMember : members){
            if(existingMember.getMemberId() == newMember.getMemberId()){
                return false;
            }
        }
        members.add(newMember);
        return true;
    }
    public Member searchMember(int memberId){
        for(Member member :  members){
            if(member.getMemberId()==memberId){
                return member;
            }
        }
        return null;
    }
    public List<Member>getAllMembers(){
        return members;
    }
    public boolean updateMember(Member updateMember){
        Member existingMember = searchMember(updateMember.getMemberId());
        if(existingMember == null){
            return false;
        }
        existingMember.setName(updateMember.getName());
        existingMember.setPhoneNumber(updateMember.getPhoneNumber());
        existingMember.setEmail(updateMember.getEmail());
        existingMember.setMembershipType(updateMember.getMembershipType());
        return true;
    }
    public boolean deleteMember(int memberId){
        Member existingMember = searchMember(memberId);
        if(existingMember == null){
            return false;
        }
        members.remove(existingMember);
        return true;
    }
    public int getTotalMembers(){
        return members.size();
    }
}
