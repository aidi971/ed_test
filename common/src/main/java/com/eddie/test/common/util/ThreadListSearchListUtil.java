package com.eddie.test.common.util;

import com.eddie.test.common.inter.ThreadModel;
import com.eddie.test.common.inter.ThreadSearchModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author eddie
 * @date 2023/11/27 14:11
 * @description 多线程处理大数据量列表工具类
 **/
public class ThreadListSearchListUtil {
    // 后续可以动态传参
    // 一个线程最大处理数据量
    private static final int THREAD_COUNT_SIZE = 1000;
    private static final Logger logger = LoggerFactory.getLogger(ThreadListSearchListUtil.class);


    /**
     * 多线程列表搜索
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends ThreadModel> List<T> getList(Class<?> clazz) {
        ThreadSearchModel<T> mapper = (ThreadSearchModel<T>) BeanFactoryUtil.getBean(clazz);

        // 记录开始时间
        long start = System.currentTimeMillis();

        // 计算表总数
        Integer totalCount = mapper.getTotalCount();

        // new和表总数一样长的ArrayList
        List<T> threadList = new ArrayList<>(totalCount);

        // 暂定以5000条数据为一个线程，总数据大小除以5000 再向上取整
        int round = (int) Math.ceil((double) totalCount / THREAD_COUNT_SIZE);

        // 创建一个临时存储List的Map，以线程名为k，用做List排序
        Map<Integer, List<T>> temporaryMap = new HashMap<>();

        // 程序计数器
        final CountDownLatch count = new CountDownLatch(round);

        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(round);

        // 分配数据
        for (int i = 0; i < round; i++) {
            int startLen = i * THREAD_COUNT_SIZE;
            int k = i + 1;
            executor.execute(() -> {
                //多线程查找数据
                List<T> tList = mapper.subList(startLen, THREAD_COUNT_SIZE);
                // 根据线程保存对应的列表
                temporaryMap.put(k, tList);
                logger.info("正在处理线程【" + k + "】的数据，数据大小为：" + tList.size());
                // 计数器-1(唤醒阻塞线程)
                count.countDown();
            });

        }
        try {
            // 阻塞线程（主线程等待所有子线程一起执行业务）
            count.await();
            //获取结束时间
            long end = System.currentTimeMillis();
            logger.info("数据查询耗时:" + (end - start) + "ms");
            // 通过循环遍历临时map，把map的值有序放进List里
            temporaryMap.keySet().forEach(k -> {
                threadList.addAll(temporaryMap.get(k));
            });

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            // 清除临时Map,释放内存
            temporaryMap.clear();
            // 终止线程池
            // 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。若已经关闭，则调用没有其他作用。
            executor.shutdown();
        }
        logger.info("list长度为：" + threadList.size());
        return threadList;
    }


//    // 以下为大致用法
//    public static void main(String[] args) {
//
//        List<Test> list = ThreadListSearchListUtil.getList(ThreadTestMapper.class);
//    }
//
//    private class Test implements ThreadModel<Test>{
//        // TODO: 2023/11/27
//        // 自定义属性
//    }
//    private interface ThreadTestMapper extends ThreadSearchModel<Test>{
//        // 以下方法返回对应mapper的方法
//        @Override
//        default List<Test> getThreadSearchList() {
//            return null;
//        }
//
//        @Override
//        default List<Test> getThreadLikeList(Map<String, Object> param) {
//            return null;
//        }
//
//        @Override
//        default Integer getTotalCount() {
//            return null;
//        }
//
//        @Override
//        default List<Test> subList(Integer startLen, Integer threadCount) {
//            return null;
//        }
//    }

}
