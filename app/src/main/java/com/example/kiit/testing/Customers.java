package com.example.kiit.testing;

public class Customers {
    String firstName;
    Boolean isSelected ;

    public Customers(String firstName, Boolean isSelected) {
        this.firstName = firstName;
        this.isSelected = isSelected;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
