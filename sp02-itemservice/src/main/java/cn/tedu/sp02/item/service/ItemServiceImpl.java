package cn.tedu.sp02.item.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public List<Item> getItems(String orderId) {
        //创建存储Item对象的集合List
        ArrayList<Item> list = new ArrayList<Item>();
        //向集合中添加数据
        list.add(new Item(1, "商品 1",1));
        list.add(new Item(2, "商品 2",2));
        list.add(new Item(3, "商品 3",3));
        list.add(new Item(4, "商品 4",4));
        list.add(new Item(5, "商品 5",5));
        //返回集合
        return list;
    }

    @Override
    public void decreaseNumbers(List<Item> list) {
        //遍历集合
        for(Item item : list) {
            //打印输出日志
            log.info("减少库存 - "+item);
        }
    }
}
