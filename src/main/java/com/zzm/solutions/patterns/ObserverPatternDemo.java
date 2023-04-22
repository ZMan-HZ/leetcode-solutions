package com.zzm.solutions.patterns;

import com.zzm.solutions.patterns.observer1.BarObserverDisplay;
import com.zzm.solutions.patterns.observer1.DbDataSubject;
import com.zzm.solutions.patterns.observer1.PieObserverDisplay;
import com.zzm.solutions.patterns.observer1.TableObserverDisplay;
import com.zzm.solutions.patterns.observer2.WhetherSubject;
import com.zzm.solutions.patterns.observer2.XiaoshanDisplayer;
import com.zzm.solutions.patterns.observer2.XihuDisplayer;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/16 星期日
 */
public class ObserverPatternDemo {

  public static void main(String[] args) {
    //
    WhetherSubject subject = new WhetherSubject();
    XihuDisplayer xihu = new XihuDisplayer(subject);
    XiaoshanDisplayer xiaoshan = new XiaoshanDisplayer(subject);

    subject.setTemperature(22.5f);
    subject.setTemperature(24.5f);
    subject.setTemperature(26.5f);

    System.out.println("----------------------------");

    DbDataSubject dbDataSubject = new DbDataSubject();
    TableObserverDisplay table = new TableObserverDisplay(dbDataSubject);
    PieObserverDisplay pie = new PieObserverDisplay(dbDataSubject);
    BarObserverDisplay bar = new BarObserverDisplay(dbDataSubject);

    dbDataSubject.setData("123");

    table.save("144444");
  }
}
