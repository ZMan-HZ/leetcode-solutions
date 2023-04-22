package com.zzm.solutions.patterns.observer1;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/16 星期日
 */
public class BarObserverDisplay implements Observer, Displayer {

  private String dbData;

  public BarObserverDisplay(Subject subject) {
    subject.registerObserver(this);
  }

  @Override
  public void update(String data) {
    this.dbData = data;
    display(data);
  }

  @Override
  public void display(String data) {
    System.out.println("BarObserverDisplay:" + data);
  }

  @Override
  public void save(String data) {}
}
