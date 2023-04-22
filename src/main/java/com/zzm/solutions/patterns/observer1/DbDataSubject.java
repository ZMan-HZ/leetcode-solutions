package com.zzm.solutions.patterns.observer1;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/16 星期日
 */
public class DbDataSubject implements Subject {

  private List<Observer> observers = Lists.newArrayList();

  private String data;

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
    dataChanged();
  }

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
    for (Observer observer : observers) {
      observer.update(data);
    }
  }

  private void dataChanged() {
    notifyObservers();
  }
}
