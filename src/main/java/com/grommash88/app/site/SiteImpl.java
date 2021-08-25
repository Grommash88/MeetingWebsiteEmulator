package com.grommash88.app.site;

import com.grommash88.app.model.User;
import com.grommash88.app.properties.Props;
import com.grommash88.app.storage.Storage;
import com.grommash88.app.util.AppLogger;
import com.grommash88.app.util.Msgs;
import java.util.Random;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SiteImpl implements Site {

  private static final int PAYMENT_RANGE = 10;
  private static final Props props = Props.INSTANCE;

  private final Random random = new Random();
  private final Storage storage;

  private int userId = 1;

  @Override
  public void addUsers(int count) {

    for (int i = 0; i < count; i++) {
      User user = User.getUser(userId, props.getRegistrationDate());
      storage.addUser(user);
      userId++;
    }
  }

  @Override
  public void showUsers(long millis) {
    try {
      long endTine = System.currentTimeMillis() + millis;
      while (System.currentTimeMillis() < endTine) {

        for (int i = 0; i < random.nextInt(PAYMENT_RANGE); i++) {
          User nonPayUser = storage.getFirstUser();
          showUser(nonPayUser, false);
          storage.moveUserToEnd(nonPayUser);
        }
        User payUser = storage.getRandomUser();
        storage.moveUserToBegin(payUser);
        showUser(payUser, true);
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    } finally {
      storage.shutdown();
    }
  }

  private void showUser(User user, boolean paid) throws InterruptedException {

    if (paid) {
      AppLogger.logMessage(String.format(Msgs.MSG_USER_PAID.getMsg(), user.getId()));
    }
    AppLogger.logMessage(String.format(Msgs.MSG_SHOW_USER.getMsg(), user.getId()));
    storage.moveUserToEnd(user);
    Thread.sleep(paid ? props.getPaidShowTime() : props.getFreeShowTime());
  }
}
