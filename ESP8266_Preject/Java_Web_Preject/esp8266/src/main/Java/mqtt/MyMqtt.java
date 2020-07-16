package mqtt;

import mqtt.MymqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * 文件名: MyMqtt
 * 创建者: 友
 * 创建日期: 2020/7/8 9:19
 * 邮箱: 1738743304.com !
 * 描述: TODO
 */
public class MyMqtt {
    String pubTopic = "chuan";
    String content = "Hello World";
    int qos = 0;
    String broker = "tcp://134.175.221.21:1883";
    String clientId = "emqx_tes2322g";
    MemoryPersistence persistence ;
    MqttClient client;
    MqttConnectOptions connOpts; // MQTT 连接类
    MqttMessage message;  //发布消息类

    public MyMqtt() throws MqttException {
        try {
            persistence = new MemoryPersistence();
            client = new MqttClient(broker, clientId, persistence); //参数一：连接路径  参数二： 显示的名称 参数三：MemoryPersistence对象
            // MQTT 连接选项
            connOpts = new MqttConnectOptions();
            connOpts.setUserName("emqx_tes2322g");      //账号
            connOpts.setPassword("emqx_test_password".toCharArray());   //密码
            // 保留会话
            connOpts.setCleanSession(true);
            // 设置回调
            client.setCallback(new MymqttCallback());
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    /**
     * 建立连接
     */
    public void Connection() {

        System.out.println("Connecting to broker: " + broker);
        try {
            // 建立连接
            client.connect(connOpts);
            // 订阅
            client.subscribe("chuan");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消息发送
     * @param data 发送的内容
     */
    public void sedMessage(String pubTopic ,String data)
    {
        if (client ==null)
        {
            return;
        }
        message = new MqttMessage(data.getBytes());
        message.setQos(qos);  //质量等级 0
        // 消息发布所需参数
        try {
            client.publish(pubTopic, message);  //发布 参数一： 主题  参数二：MqttMessage对象
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
}
