package bean;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {
    private String temp;
    private String humi;
    private String flame;

    public String getFlame() {
        return flame;
    }

    public void setFlame(String flame) {
        this.flame = flame;
    }

    private List<Data> dataList;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumi() {
        return humi;
    }

    public void setHumi(String humi) {
        this.humi = humi;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public Data() {
    }

    public Data(String temp, String humi,String flame) {
        this.temp = temp;
        this.humi = humi;
        this.flame = flame;
    }
}
