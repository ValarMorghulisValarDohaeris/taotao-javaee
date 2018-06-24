package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * Created by hao on 2018/6/20.
 */
public interface ContentService {
    EUDataGridResult selectContent(int page, int rows,long categoryId);
    TaotaoResult insertContent(TbContent content);
}
