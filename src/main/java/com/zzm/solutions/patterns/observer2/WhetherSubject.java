package com.zzm.solutions.patterns.observer2;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/16 星期日
 */
public class WhetherSubject implements Subject {

  private List<Observer> observers = Lists.newArrayList();

  private Float temperature;

  @Override
  public void registerObserver(Observer observer) {
    this.observers.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    this.observers.remove(observer);
  }

  @Override
  public void notifyObservers() {

    if (CollectionUtils.isEmpty(observers)) {
      return;
    }
    for (Observer o : observers) {
      o.update(temperature);
    }
  }

  public Float getTemperature() {
    return temperature;
  }

  public void setTemperature(Float temperature) {
    this.temperature = temperature;
    whetherChange();
  }

  private void whetherChange() {
    notifyObservers();
  }
}
