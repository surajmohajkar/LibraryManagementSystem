package interfaces;

import model.Member;

import java.util.List;

public interface MemberServiceInterface {
    void registerMember(Member newMember);
    Member searchMember(int memberId);
    List<Member> getAllMembers();
    boolean updateMember(Member updatedMember);
    boolean deleteMember(int memberId);
    int getTotalMembers();
}