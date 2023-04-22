package com.zzm.solutions.patterns.template;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/16 星期日
 */
public abstract class AbstractBank {

  private String userName;

  private String password;

  AbstractBank() {}

  public AbstractBank(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean login() {
    boolean valid = validateUser();
    if (valid) {
      welcome();
    } else {
      loginPage();
    }
    return true;
  }

  protected abstract boolean validateUser();

  protected abstract String welcome();

  protected abstract String loginPage();
}
