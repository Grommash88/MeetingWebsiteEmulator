package com.grommash88.app.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;

@Getter
public class User implements Serializable {

  private final int id;

  private final Date registrationDate;

  private User(int id, Date date) {
    this.id = id;
    this.registrationDate = date;
  }

  public static User getUser(int userId, Date registrationDate) {

    return new User(userId, registrationDate);
  }
}