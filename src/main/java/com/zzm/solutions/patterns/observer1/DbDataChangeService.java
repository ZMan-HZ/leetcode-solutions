package com.zzm.solutions.patterns.observer1;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/16 星期日
 */
public class DbDataChangeService {

  private DbDataSubject subject;

  public DbDataChangeService(DbDataSubject subject) {
    this.subject = subject;
  }

  public void save(String data) {
    subject.setData(data);
  }
}
