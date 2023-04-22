package com.zzm.solutions.patterns.template;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/16 星期日
 */
public class OnlineBank extends AbstractBank {

  public OnlineBank() {}

  @Override
  protected boolean validateUser() {
    return false;
  }

  @Override
  protected String welcome() {
    return null;
  }

  @Override
  protected String loginPage() {
    return null;
  }
}
