package mqtt;



import bean.Data;
import bean.GetData;
import com.google.gson.Gson;
import controller.FanController;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import service.EmployeeDao;
import service.Mode1;
import service.Timing;

import java.util.Calendar;

/**
 * 文件名: MymqttCallback
 * 创建者: 友
 * 创建日期: 2020/7/8 10:41
 * 邮箱: 1738743304.com !
 * 描述: TODO
 */
public class MymqttCallback implements MqttCallback {
    public void connectionLost(Throwable cause) {
        Mode1.getExecutorService().execute(new Runnable() {
            @Override
            public void run() {
                MyMqtt myMqtt = null;//重连
                try {
                    myMqtt = new MyMqtt();
                    myMqtt.Connection();
                } catch (MqttException e) {
                    e.printStackTrace();
                }

            }
        });
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        System.out.println("接收消息主题:" + topic);
        System.out.println("接收消息Qos:" + message.getQos());
        System.out.println("接收消息内容:" + new String(message.getPayload()));
        String jsondata = new String(message.getPayload());  //获取到的json数据

        Data data = new Gson().fromJson(jsondata,Data.class); //将json数据转化为data对象  解析json数据




            System.out.println("wendu:" + data.getTemp());
             System.out.println("shidu:" + data.getHumi());
             System.out.println("Flame:" + data.getFlame());
            if (Timing.ismysql)  //判断是否有10s
            {
                EmployeeDao saveMysql = new EmployeeDao();
                saveMysql.save(data);  //存储信息
                Timing.ismysql = false;//标志位重置为false 等待下一个10s
            }

    }
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }
}
