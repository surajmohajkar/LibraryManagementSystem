package service;

import dao.MemberDAO;
import dao.impl.MemberDAOImpl;
import exception.DuplicateMemberException;
import exception.MemberNotFoundException;
import interfaces.MemberServiceInterface;
import model.Member;
import java.util.List;

public class MemberService implements MemberServiceInterface {
    private final MemberDAO memberDAO;
    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }
    @Override
    public void registerMember(Member newMember) {
        if (memberDAO.existsById(newMember.getMemberId())) {
            throw new DuplicateMemberException("Member ID already exists.");
        }
        memberDAO.registerMember(newMember);
    }
    @Override
    public Member searchMember(int memberId) {
        Member member = memberDAO.getMemberById(memberId);
        if (member == null) {
            throw new MemberNotFoundException("Member with ID " + memberId + " not found.");
        }
        return member;
    }
    @Override
    public List<Member> getAllMembers() {
        return memberDAO.getAllMembers();
    }
    @Override
    public boolean updateMember(Member updatedMember) {
        if (!memberDAO.existsById(updatedMember.getMemberId())) {
            throw new MemberNotFoundException("Member with ID " + updatedMember.getMemberId() + " not found.");
        }
        return memberDAO.updateMember(updatedMember);
    }
    @Override
    public boolean deleteMember(int memberId) {
        if (!memberDAO.existsById(memberId)) {
            throw new MemberNotFoundException("Member with ID " + memberId + " not found.");
        }
        return memberDAO.deleteMember(memberId);
    }
    @Override
    public int getTotalMembers() {
        return memberDAO.getTotalMembers();
    }
}