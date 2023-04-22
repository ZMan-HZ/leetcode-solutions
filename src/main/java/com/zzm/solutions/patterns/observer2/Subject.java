package com.zzm.solutions.patterns.observer2;

/**
 * 天气预报的主题 天气预报的主题
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/16 星期日
 */
public interface Subject {

  /**
   * 注册观察者
   *
   * @param observer
   */
  void registerObserver(Observer observer);

  /**
   * 移除观察者
   *
   * @param observer
   */
  void removeObserver(Observer observer);

  /** 通知 */
  void notifyObservers();
}
