package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;

/**
 * Created by hao on 2018/6/11.
 */
public interface ItemService {

    TbItem getItemById(long itemId);

    EUDataGridResult getItemList(int page,int rows);
}
