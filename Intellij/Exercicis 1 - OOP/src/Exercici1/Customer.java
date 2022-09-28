package Exercici1;

public class Customer {
    private String name;
    private boolean member;
    private String memberType;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        if (typeIsValid(memberType)) {
            this.memberType = memberType;
            this.member = true;
        }
    }

    private boolean typeIsValid(String memberType) {
        if (memberType.toLowerCase().equals("silver")) return true;
        if (memberType.toLowerCase().equals("gold")) return true;
        if (memberType.toLowerCase().equals("premium")) return true;
        return false;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder().append(this.getName());
        s.append(" con membresia ").append(this.memberType).append(".");
        return s.toString();
    }
}
