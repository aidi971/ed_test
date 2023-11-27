package com.eddie.test.common.inter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * @author eddie
 * @date 2023/11/27 13:57
 * @description 树形结构工具类
 **/
public interface TreeModel<T> {

    /**
     * 获取当前id
     *
     * @return
     */
    @JsonIgnore
    String getIndex();

    /**
     * 获取当前id
     *
     * @return
     */
    @JsonIgnore
    String getId();

    /**
     * 获取父级id
     *
     * @return
     */
    @JsonIgnore
    String getParentId();

    /**
     * 保存子集
     *
     * @return
     */
    @JsonIgnore
    List<T> getChildrenList();

}
