package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.utils.TaotaoResult;

import java.util.List;

/**
 * Created by hao on 2018/6/20.
 */
public interface ContentCategoryService {

    List<EUTreeNode> getCategoryList(long parentId);
    TaotaoResult insertContentCategory(long parentId,String name);
    TaotaoResult deleteContentCategory(Long parentId,Long id);
    TaotaoResult updateContentCategory(long id,String name);
}
