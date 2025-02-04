package com.vtx.vtx_authorization_server.utils;

public class App {
  public static void main(String[] args) {

    Person person = new Person();
    person.setName("John doe");
    person.setAge(22);


    Person target = new Person();

    PropertyMapper propertyMapper = PropertyMapper.getInstance();
    propertyMapper.from(person::getName).to(target::setName);
    propertyMapper.from(person::getAge).to(target::setAge);

    System.out.println(target);
  }
}

class Person {
  private String name;

  private int age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Person{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
  }
}
