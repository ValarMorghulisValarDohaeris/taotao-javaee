package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;

import java.util.List;

/**
 * Created by hao on 2018/6/12.
 */
public interface ItemCatService {

    List<EUTreeNode> getCatList(long parentId);

}
