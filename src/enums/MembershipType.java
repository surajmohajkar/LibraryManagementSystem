package enums;

public enum MembershipType {

    STUDENT,
    FACULTY,
    PREMIUM,
    GENERAL;

    @Override
    public String toString() {
        return switch (this) {
            case STUDENT -> "Student";
            case FACULTY -> "Faculty";
            case PREMIUM -> "Premium";
            case GENERAL -> "General";
        };
    }
}