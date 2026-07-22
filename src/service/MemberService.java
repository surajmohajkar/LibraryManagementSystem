package service;

import exception.DuplicateMemberException;
import exception.MemberNotFoundException;
import model.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberService {
    private final List<Member> members;
    public MemberService() {
        members = new ArrayList<>();
    }
    public void registerMember(Member newMember) {
        for (Member existingMember : members) {
            if (existingMember.getMemberId() == newMember.getMemberId()) {
                throw new DuplicateMemberException("Member ID already exists.");
            }
        }
        members.add(newMember);
    }
    public Member searchMember(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        throw new MemberNotFoundException("Member with ID " + memberId + " not found.");
    }

    public List<Member> getAllMembers() {
        return members;
    }

    public boolean updateMember(Member updatedMember) {

        Member existingMember = searchMember(updatedMember.getMemberId());
        existingMember.setName(updatedMember.getName());
        existingMember.setPhoneNumber(updatedMember.getPhoneNumber());
        existingMember.setEmail(updatedMember.getEmail());
        existingMember.setMembershipType(updatedMember.getMembershipType());
        return true;
    }

    public boolean deleteMember(int memberId) {
        Member existingMember = searchMember(memberId);
        members.remove(existingMember);
        return true;
    }
    public int getTotalMembers() {
        return members.size();
    }
}