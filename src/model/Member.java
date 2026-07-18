package model;

import enums.MembershipType;

public class Member {

    private int memberId;
    private String name;
    private String phoneNumber;
    private String email;
    private MembershipType membershipType;

    public Member(int memberId,
                  String name,
                  String phoneNumber,
                  String email,
                  MembershipType membershipType) {

        this.memberId = memberId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.membershipType = membershipType;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", membershipType=" + membershipType +
                '}';
    }
}