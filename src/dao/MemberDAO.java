package dao;

import model.Member;
import java.util.List;

public interface MemberDAO {
    boolean registerMember(Member member);
    Member getMemberById(int MemberId);
    List<Member> getAllMembers();
    boolean updateMember(Member member);
    boolean deleteMember(int memberId);
    int getTotalMembers();
}
