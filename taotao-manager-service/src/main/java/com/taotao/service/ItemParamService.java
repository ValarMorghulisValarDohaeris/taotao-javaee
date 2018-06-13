package com.taotao.service;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItemParam;

/**
 * Created by hao on 2018/6/13.
 */
public interface ItemParamService {
    TaotaoResult getItemParamByCid(long cid);

    TaotaoResult insertItemParam(TbItemParam itemParam);
}
