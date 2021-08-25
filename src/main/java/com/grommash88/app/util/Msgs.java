package com.grommash88.app.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Msgs {

  ADD_USERS("Add user \"%s\" registration date \"%s\"... %s"),
  DELETE_USERS("Delete users... %s"),
  FAIL_CONNECTION("Не удалось подключиться к Redis"),
  MSG_USER_PAID("Пользователь %s оплатил платную услугу"),
  MSG_SHOW_USER("На главной странице показываем пользователя %s");

  private final String msg;
}
