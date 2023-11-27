package com.eddie.test.common.inter;

import java.util.List;
import java.util.Map;

/**
 * @author eddie
 * @date 2023/11/27 14:00
 * @description 多线程查询方法接口
 **/
public interface ThreadSearchModel<T> {

    /**
     * 全查询
     *
     * @return
     */
    List<T> getThreadSearchList();

    /**
     * 模糊查询
     *
     * @return
     */
    List<T> getThreadLikeList(Map<String, Object> param);

    /**
     * 总数
     *
     * @return
     */
    Integer getTotalCount();

    /**
     * 多线程根据limit查询数据
     *
     * @param startLen
     * @param threadCount
     * @return
     */
    List<T> subList(Integer startLen, Integer threadCount);
}
