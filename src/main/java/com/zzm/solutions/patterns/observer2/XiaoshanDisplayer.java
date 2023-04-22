package com.zzm.solutions.patterns.observer2;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/16 星期日
 */
public class XiaoshanDisplayer implements Observer {

  private Float temperature;

  public XiaoshanDisplayer(Subject subject) {
    subject.registerObserver(this);
  }

  @Override
  public void update(Float temperature) {
    this.temperature = temperature;
    displayWhether();
  }

  public void displayWhether() {
    System.out.println("Xiaoshan temperature:"+this.temperature);
  }
}
