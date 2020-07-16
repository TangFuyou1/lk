package bean;

/**
 * 文件名: GetData
 * 创建者: 友
 * 创建日期: 2020/7/14 21:43
 * 邮箱: 1738743304.com !
 * 描述: TODO
 */
public class GetData {
    private static Data data;

    public static Data getData() {
        return data;
    }

    public static void setData(Data data) {
        GetData.data = data;
    }
}
