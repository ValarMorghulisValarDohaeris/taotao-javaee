package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by hao on 2018/6/11.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long itemId) {
//        TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);

        TbItemExample example = new TbItemExample();
        //添加查询条件
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> tbItems = itemMapper.selectByExample(example);
        if(tbItems != null && tbItems.size() > 0){
            return tbItems.get(0);
        }
        return null;
    }

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        //查询商品列表
        TbItemExample example = new TbItemExample();
        //分页处理
        PageHelper.startPage(page,rows);
        List<TbItem> tbItems = itemMapper.selectByExample(example);
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(tbItems);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        //总记录数
        result.setTotal(pageInfo.getTotal());
        result.setRows(tbItems);
        return result;
    }

    @Override
    public TaotaoResult createItem(TbItem tbItem) {
        //item补全
        //生成商品ID
        Long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        //设置status
        tbItem.setStatus((byte) 1);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        //插入到数据库
        itemMapper.insert(tbItem);
        return TaotaoResult.ok();
    }
}
