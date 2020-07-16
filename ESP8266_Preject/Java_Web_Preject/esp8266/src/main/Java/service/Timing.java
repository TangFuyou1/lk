package service;


import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时执行任务
 */
public class Timing   {
    public static boolean ismysql = true;
    public static   void timer() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23); // 控制时
        calendar.set(Calendar.MINUTE,8);       // 控制分
        calendar.set(Calendar.SECOND, 0);       // 控制秒
        Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的12：00：00
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
              //  System.out.println(jsondata);
                ismysql = true;
            }
        }, time, 1000*60*60);// 这里设定将延时每天固定执行
    }

}
