package com.grommash88.app;

import com.grommash88.app.site.Site;
import com.grommash88.app.site.SiteImpl;
import com.grommash88.app.storage.RedisStorage;

public class Main {

  private static final int USERS_COUNT = 20;
  private static final long TIME_TO_EMULATE_THE_SITE_IN_MILLS_SECONDS = 30_000;

  public static void main(String[] args) {
    Site site = new SiteImpl(new RedisStorage());
    site.addUsers(USERS_COUNT);
    site.showUsers(TIME_TO_EMULATE_THE_SITE_IN_MILLS_SECONDS);
  }
}
