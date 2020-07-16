package controller;

import bean.Data;
import bean.GetData;
import mqtt.MyMqtt;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.EmployeeDao;
import service.Timing;



@Controller
public class FanController {
    MyMqtt myMqtt = new MyMqtt();
    public FanController() throws MqttException {

        myMqtt.Connection(); //连接emqx
        Timing.timer(); //启动定时  每10秒向数据库存一条数据
    }

    /**
     * 获取EMQX上的数据
     */
    @ResponseBody
    @RequestMapping(value = "/get_data", method = RequestMethod.GET)
    public Data get_data() {

        Data data = new Data("connection_ok","connection","0");
        return data;
    }

    /**
     * 查询数据库最后10条记录 并向Andorid发送数据
     *
     * @return 返回的数据对象
     */
    @ResponseBody
    @RequestMapping(value = "/set_data", method = RequestMethod.GET)
    public Data show() {
        Data data = new Data();
        data.setDataList(new EmployeeDao().queryAll()); //查询数据库最后10条记录
        return data;
    }


    /**
     * 开灯
     *
     * @return 返回的数据对象
     */
    @ResponseBody
    @RequestMapping(value = "/led_on", method = RequestMethod.GET)
    public Data LED_Opne() {
        Data data = new Data("成功","成功","成功");
        myMqtt.sedMessage("SW_LED","LED_ON");
        return data;
    }
    /**
     * 开灯
     *
     * @return 返回的数据对象
     */
    @ResponseBody
    @RequestMapping(value = "/led_off", method = RequestMethod.GET)
    public Data LED_Close() {
        Data data = new Data("成功","成功","成功");
        myMqtt.sedMessage("SW_LED","LED_OFF");  //参数一 ： 主题  参数二 ：内容
        return data;
    }
}
