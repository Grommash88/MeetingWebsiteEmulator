package com.grommash88.app.storage;

import com.grommash88.app.model.User;

public interface Storage {

  void shutdown();

  void addUser(User name);

  User getFirstUser();

  User getRandomUser();

  void moveUserToBegin(User user);

  void moveUserToEnd(User user);
}
