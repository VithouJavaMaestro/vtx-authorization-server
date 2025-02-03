package com.vtx.vtx_authorization_server.utils;

public class App {
  public static void main(String[] args) {

    Person source = new Person();
    source.setFirstname("john");
    source.setLastname("doe");

    Person target = new Person();

    final var converter = PropertyConversions.applyWhenNonNullProperty();

    converter.from(source::getFirstname).to(target::setFirstname);
    converter.from(source::getLastname).to(target::setLastname);

    System.out.println(target);
  }
}


class Person {
  private String firstname;

  private String lastname;

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  @Override
  public String toString() {
    return "Person{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }
}
