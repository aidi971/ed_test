package com.eddie.test.common.util;

import cn.hutool.core.collection.CollectionUtil;
import com.eddie.test.common.inter.TreeModel;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eddie
 * @date 2023/11/27 14:04
 * @description 树形结构工具类
 **/
public class TreeUtil {

    /**
     * 将 list 转为 树形结构list
     * @param list 继承了TreeModel的list
     * @return
     * @param <T>
     */
    public static <T extends TreeModel<T>> List<T> getTreeList(List<T> list){

        List<T> rootList = new ArrayList<>();
        //遍历列表
        for (T t : list) {
            //如果是父节点 递归寻找子节点
            if (checkIsRoot(t)){
                rootList.add(findChildren(t,list));
            }
        }
        return rootList;
    }

    /**
     * 自定义父节点返回对应子集
     * @param list 待递归列表
     * @param parentId 父级id
     * @return
     * @param <T>
     */
    public static <T extends TreeModel<T>> List<T> getTreeList(List<T> list,String... parentId){

        List<T> rootList = new ArrayList<>();
        //遍历列表
        for (T t : list) {
            //如果是父节点 递归寻找子节点
            if (checkIsRoot(t,parentId)){
                rootList.add(findChildren(t,list));
            }
        }
        return rootList;
    }

    /**
     * 树形结构转list
     * @param list
     * @return
     * @param <T>
     */
    public static <T extends TreeModel<T>> List<T> treeTranstToList(List<T> list){
        List<T> resultList = new ArrayList<>();
        // 遍历找子集
        for (T t : list) {
            //获取子节点
            List<T> childrenList = t.getChildrenList();
            resultList.add(t);
            //子节点不为空递归找子节点
            if (CollectionUtil.isNotEmpty(childrenList)){
                resultList.addAll(treeTranstToList(childrenList));
                // 清空列表
                t.getChildrenList().clear();
            }
        }
        return resultList;
    }

    /**
     * 递归找子节点
     * @param parent
     * @param list
     * @param <T>
     */
    public static <T extends TreeModel<T>> T findChildren(T parent, List<T> list) {
        //遍历原列表
        for (T t : list) {
            if (StringUtils.isNotEmpty(parent.getIndex())){
                //如果当前子集父节点id等于父节点id
                if (parent.getId().equals(t.getParentId()) && parent.getIndex().equals(t.getIndex())){
                    //往下遍历看子集是否还存在子集
                    parent.getChildrenList().add(findChildren(t, list));
                }
            }else {
                if (parent.getId().equals(t.getParentId())){
                    //往下遍历看子集是否还存在子集
                    parent.getChildrenList().add(findChildren(t, list));
                }
            }

        }
        return parent;
    }

    /**
     * 判断是否为父级节点
     * @param t
     * @return
     * @param <T>
     */
    private static <T extends TreeModel<T>> boolean checkIsRoot(T t) {
        boolean isRoot = false;
        //如果父节点为空 即为父节点
        if (StringUtils.isBlank(t.getParentId())){
            isRoot = true;
        }
        return isRoot;
    }

    /**
     * 根据自定义父级id判断是否为父级节点
     * @param t
     * @param parentId
     * @return
     * @param <T>
     */
    private static <T extends TreeModel<T>> boolean checkIsRoot(T t,String... parentId) {
        boolean isRoot = false;
        //如果父节点为空 即为父节点
        if (ArrayUtils.contains(parentId,t.getParentId())){
            isRoot = true;
        }
        return isRoot;
    }
}
