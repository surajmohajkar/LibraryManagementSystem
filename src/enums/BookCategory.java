package enums;

public enum BookCategory {

    PROGRAMMING,
    SCIENCE,
    FICTION,
    HISTORY,
    BIOGRAPHY,
    TECHNOLOGY,
    MATHEMATICS,
    OTHER;

    @Override
    public String toString() {
        return switch (this) {
            case PROGRAMMING -> "Programming";
            case SCIENCE -> "Science";
            case FICTION -> "Fiction";
            case HISTORY -> "History";
            case BIOGRAPHY -> "Biography";
            case TECHNOLOGY -> "Technology";
            case MATHEMATICS -> "Mathematics";
            case OTHER -> "Other";
        };
    }
}