package service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 文件名: Mode1
 * 创建者: 友
 * 创建日期: 2020/6/22 19:23
 * 邮箱: 1738743304.com !
 * 描述: TODO
 */
public class Mode1 {
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    /**
     * 获取全程线程池对象
     * @return
     */
    public static ExecutorService getExecutorService()
    {
        return  executorService;
    }
}
