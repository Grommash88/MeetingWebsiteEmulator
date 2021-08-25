package com.grommash88.app.storage;

import com.grommash88.app.model.User;
import com.grommash88.app.properties.Props;
import com.grommash88.app.util.AppLogger;
import com.grommash88.app.util.Msgs;
import java.util.Date;
import java.util.Random;
import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

public class RedisStorage implements Storage {

  private static final Props PROPS = Props.INSTANCE;
  private static final Random RANDOM = new Random();


  private RedissonClient redisson;
  private RScoredSortedSet<User> queueOfSiteUsersToBeShownOnTheMainPage;

  public RedisStorage() {
    init();
  }

  private void init() {

    Config config = new Config();
    String address = String.format("redis://%s:%s",
        PROPS.getHost(), PROPS.getPort());
    config.useSingleServer().setAddress(address);

    try {

      redisson = Redisson.create(config);
      RKeys rKeys = redisson.getKeys();
      queueOfSiteUsersToBeShownOnTheMainPage = redisson.getScoredSortedSet(PROPS.getKey());
      rKeys.delete(PROPS.getKey());
    } catch (RedisConnectionException Exc) {

      AppLogger.logErrMsg(Msgs.FAIL_CONNECTION.getMsg());
      AppLogger.logException(Exc);
    }
  }

  @Override
  public void shutdown() {

    redisson.getKeys().delete(queueOfSiteUsersToBeShownOnTheMainPage);
    boolean isStorageEmpty = redisson.getKeys().countExists(PROPS.getKey()) == 0;
    AppLogger.logMessage(String.format(Msgs.DELETE_USERS.getMsg(),
        (isStorageEmpty ? "Done" : "Error")));
    redisson.shutdown();
  }

  @Override
  public void addUser(User user) {

    queueOfSiteUsersToBeShownOnTheMainPage.add(user.getRegistrationDate().getTime(), user);
    AppLogger.logMessage(String.format(Msgs.ADD_USERS.getMsg(), user.getId(),
        user.getRegistrationDate(), queueOfSiteUsersToBeShownOnTheMainPage
            .contains(user) ? "Done" : "Error!"));
  }

  @Override
  public User getFirstUser() {

    return queueOfSiteUsersToBeShownOnTheMainPage.first();
  }

  @Override
  public User getRandomUser() {

    int userRank = RANDOM.nextInt(20);
    int arraySize = queueOfSiteUsersToBeShownOnTheMainPage.valueRange(userRank, userRank).size();
    User[] users = queueOfSiteUsersToBeShownOnTheMainPage.valueRange(userRank, userRank)
        .toArray(new User[arraySize]);
    return users[0];
  }

  @Override
  public void moveUserToBegin(User user) {

    queueOfSiteUsersToBeShownOnTheMainPage.add(PROPS.getUserScoreAfterPayment(), user);
  }

  @Override
  public void moveUserToEnd(User user) {

    queueOfSiteUsersToBeShownOnTheMainPage.add(new Date().getTime(), user);
  }
}
