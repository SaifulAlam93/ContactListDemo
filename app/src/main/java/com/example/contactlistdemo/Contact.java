package com.example.contactlistdemo;

public class Contact {
    private String name,address,contactno;
    public Contact(){}
    public Contact(String name, String address, String contactno) {
        this.name = name;
        this.address = address;
        this.contactno = contactno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }
}
