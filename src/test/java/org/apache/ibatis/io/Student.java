package org.apache.ibatis.io;

public class Student {
  private int id;
  private String name;

  public Student() {
    System.out.println("create...student");
  }

  public Student(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public  String getStrings() {
    return "success";
  }
}
