package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
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
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

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
    public TaotaoResult createItem(TbItem tbItem,String desc, String itemParam) throws Exception {
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
        //添加商品描述信息
        TaotaoResult result = createInsertDesc(itemId, desc);
        if(result.getStatus() != 200){
            throw new Exception();
        }
        //添加规格参数
        result = insertItemParamItem(itemId,itemParam);
        if(result.getStatus() != 200){
            throw new Exception();
        }
        return TaotaoResult.ok();
    }

    private TaotaoResult createInsertDesc(Long itemId,String desc){
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());
        itemDescMapper.insert(tbItemDesc);
        return TaotaoResult.ok();
    }

    private TaotaoResult insertItemParamItem(Long itemId,String itemParam){
        //创建pojo
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        //想表中插入数据
        itemParamItemMapper.insert(itemParamItem);
        return TaotaoResult.ok();
    }
}
