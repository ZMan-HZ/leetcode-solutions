package com.zzm.solutions.patterns.observer1;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/16 星期日
 */
public class TableObserverDisplay implements Observer, Displayer {

  private String dbData;

  private DbDataChangeService changeService;

  public TableObserverDisplay(Subject subject) {
    subject.registerObserver(this);
    changeService = new DbDataChangeService((DbDataSubject) subject);
  }

  @Override
  public void update(String data) {
    this.dbData = data;
    display(data);
  }

  @Override
  public void display(String data) {
    System.out.println("TableObserverDisplay: " + dbData);
  }

  @Override
  public void save(String data) {
    changeService.save(data);
  }
}
