package daoImplementation;

import dao.MemberDAO;
import model.Member;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {
    @Override
    public boolean registerMember(Member member) {
        return false;
    }

    @Override
    public Member getMemberById(int MemberId) {
        return null;
    }

    @Override
    public List<Member> getAllMembers() {
        return List.of();
    }

    @Override
    public boolean updateMember(Member member) {
        return false;
    }

    @Override
    public boolean deleteMember(int memberId) {
        return false;
    }

    @Override
    public int getTotalMembers() {
        return 0;
    }
}
