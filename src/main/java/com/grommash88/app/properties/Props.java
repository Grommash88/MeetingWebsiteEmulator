package com.grommash88.app.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;

@Getter
public enum Props {

  INSTANCE;

  public static final String PATH_TO_PROPERTIES = "src/main/resources/application.properties";
  private final double userScoreAfterPayment;

  private final String host;
  private final String port;
  private final String key;

  private final int freeShowTime;
  private final int paidShowTime;

  private final Date dateOfEndOfRegistrationOnTheSite;
  private final Date dateOfStartOfRegistrationOnTheSite;

  Props() {
    Properties props = new Properties();

    try (InputStream in = new FileInputStream(PATH_TO_PROPERTIES)) {
      props.load(in);
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.host = props.getProperty("redis.host");
    this.port = props.getProperty("redis.port");
    this.key = props.getProperty("redis.key");

    this.paidShowTime = Integer.parseInt(props.getProperty("site.paid_show_time_ms"));
    this.freeShowTime = Integer.parseInt(props.getProperty("site.free_show_time_ms"));

    dateOfEndOfRegistrationOnTheSite = Calendar.getInstance().getTime();
    dateOfStartOfRegistrationOnTheSite = getStartDate();
    userScoreAfterPayment = 0.0;
  }

  private Date getStartDate() {

    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -7);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    return calendar.getTime();
  }

  public Date getRegistrationDate() {

    long random = ThreadLocalRandom.current()
        .nextLong(dateOfStartOfRegistrationOnTheSite.getTime(),
            dateOfEndOfRegistrationOnTheSite.getTime());
    return new Date(random);
  }

}