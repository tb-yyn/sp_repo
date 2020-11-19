package cn.tedu.sp02.item.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import cn.tedu.web.util.JsonResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j //日志注解
@RestController //标识controller层
public class ItemController {

    //注入sp01-common中的接口
    @Autowired
    private ItemService itemService;

    //获取application.yml中的port属性
    @Value("${server.port}")
    private int port;

    /**
     * 根据订单id查询商品信息
     * @param orderId
     * @return
     * @throws InterruptedException 
     */
    @GetMapping("/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId) throws InterruptedException {
        //打印日志信息
        log.info("server.port="+port+", orderId="+orderId);
        
        ///--设置随机延迟
		if(Math.random()<0.6) { 
			long t = new Random().nextInt(5000);
			log.info("item-service-"+port+" - 暂停 "+t);
			Thread.sleep(t);
		}
        //调用service中的方法，返回Item  List集合
        List<Item> items = itemService.getItems(orderId);
        //通过JsonResult返回结果
        return JsonResult.ok(items).msg("port="+port);
    }
    /**
     * 减少库存数量
     * @param items
     * @return
     */
    @PostMapping("/decreaseNumber")
    public JsonResult decreaseNumber(@RequestBody List<Item> items) {
        //调用service中的方法
        itemService.decreaseNumbers(items);
        //通过JsonResult返回结果
        return JsonResult.ok();
    }
}
