package dao.impl;

import dao.BaseDAO;
import dao.MemberDAO;
import enums.MembershipType;
import model.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MemberDAOImpl extends BaseDAO implements MemberDAO {
    @Override
    public boolean registerMember(Member member) {
        String sql = """
            INSERT INTO member
            (
                member_id,
                member_name,
                phone_number,
                email,
                membership_type
            )
            VALUES (?, ?, ?, ?, ?)
            """;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, member.getMemberId());
            preparedStatement.setString(2, member.getName());
            preparedStatement.setString(3, member.getPhoneNumber());
            preparedStatement.setString(4, member.getEmail());
            preparedStatement.setString(5, member.getMembershipType().name());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Member getMemberById(int memberId) {
        String sql = """
            SELECT
                member_id,
                member_name,
                phone_number,
                email,
                membership_type
            FROM member
            WHERE member_id = ?
            """;

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToMember(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public boolean existsById(int memberId) {
        String sql = """
            SELECT 1
            FROM member
            WHERE member_id = ?
            """;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, memberId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                return resultSet.next();
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getTotalMembers() {
        return 0;
    }
    private Member mapResultSetToMember(ResultSet resultSet)  throws SQLException {
        return new Member(
                resultSet.getInt("member_id"),
                resultSet.getString("member_name"),
                resultSet.getString("phone_number"),
                resultSet.getString("email"),
                MembershipType.valueOf(resultSet.getString("membership_type")));
    }
}
