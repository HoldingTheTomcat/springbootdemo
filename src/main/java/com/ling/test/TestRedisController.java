package com.ling.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/4/27
 */

@RestController
public class TestRedisController {

    
    @Autowired
    private RedisTemplate redisTemplate;
    
    @RequestMapping("testSet")
    public void testSet(){
        
        redisTemplate.opsForSet().add("test:set2", "asdf");
        
        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add("set1", "22");
        set.add("set1", "33");
        set.add("set1", "44");

        redisTemplate.opsForSet().add("red_123", "1", "2", "3");
        
        //根据key查看集合中是否存在指定数据
        Boolean isRed_123 = redisTemplate.opsForSet().isMember("red_123", "1");

        //返回集合set1中的所有成员
        Set<String> resultSet = redisTemplate.opsForSet().members("set1");
        System.out.println("resultSet:" + resultSet);
    }

    @RequestMapping("testMap")
    public void testMap() {
        // 方式1 设值
        redisTemplate.opsForHash().put("hash1", "name1", "lms1");
        redisTemplate.opsForHash().put("hash1", "name2", "lms2");
        redisTemplate.opsForHash().put("hash1", "name3", "lms3");
        Map<String, String> hash1Map = redisTemplate.opsForHash().entries("hash1");
        
        //方式2 设值
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");
        map.put("key6", 1);
        map.put("key7", "2.0");
        redisTemplate.opsForHash().putAll("map1", map);
        
        //取出map1
        Map<String, Object> resultMap = redisTemplate.opsForHash().entries("map1");
        //取出map的: value
        List<String> reslutMapList = redisTemplate.opsForHash().values("map1");
        //取出map的: key
        Set<String> resultMapSet = redisTemplate.opsForHash().keys("map1");
        //取出指定map，指定key，对应的value
        String value = (String) redisTemplate.opsForHash().get("map1", "key1");

        //multiGet方法，根据key和多个hashkey找出对应的多个值
        Collection<Object> keys = new ArrayList<>();
        keys.add("key1");
        keys.add("key2");
        List mapValue = redisTemplate.opsForHash().multiGet("map1", keys);
        System.out.println(mapValue); //结果：value1、value2
        
        //hasKey方法，确定hashkey是否存在
        System.out.println(redisTemplate.opsForHash().hasKey("map1", "key1"));
        
        //delete方法，删除key对应的hash的hashkey及其value
        redisTemplate.opsForHash().delete("map1", "key1");

        //increment方法，对key和hashkey对应的值进行增加操作
        //增加长整形（无法对浮点数据使用本方法）
        System.out.println(redisTemplate.opsForHash().increment("map1", "key6", 1));
        //增加浮点型（可以对整形数据使用本方法）
        System.out.println(redisTemplate.opsForHash().increment("map1", "key7", 1.0));
        
        System.out.println("value:" + value);
        System.out.println("resultMapSet:" + resultMapSet);
        System.out.println("resultMap:" + resultMap);
        System.out.println("resulreslutMapListtMap:" + reslutMapList);

    }
    
    @RequestMapping("testList")
    public void testList(){

        List<String> template;

        //向集合中添加元素
        redisTemplate.opsForList().leftPush("list", "a");
        redisTemplate.opsForList().leftPush("list", "b");
        redisTemplate.opsForList().leftPush("list", "c");

        redisTemplate.opsForList().leftPushAll("list2", "w", "x", "y");
        
        List<String> list1 = new ArrayList<String>();
        list1.add("a1");
        list1.add("a2");
        list1.add("a3");

        List<String> list2 = new ArrayList<String>();
        list2.add("b1");
        list2.add("b2");
        list2.add("b3");
        
        redisTemplate.opsForList().leftPush("listkey1", list1);
        redisTemplate.opsForList().rightPush("listkey2", list2);

        // 在集合的指定位置插入元素, 如果指定位置已有元素，则覆盖，没有则新增，超过集合下标 + n则会报错。
        redisTemplate.opsForList().set("presentList", 3, "15");
        template = redisTemplate.opsForList().range("presentList", 0, -1);
        System.out.print("通过set(K key, long index, V value)方法在指定位置添加元素后:" + template);

        // 向已存在的集合中添加元素
        redisTemplate.opsForList().rightPushIfPresent("list", "d"); // 向已存在的集合list,向最右边添加元素
        List<String> listPresent = redisTemplate.opsForList().range("list", 0, -1);
        System.out.println("通过rightPushIfPresent(K key, V value)方法已存在的集合向最右边添加元素:" + listPresent);
        
        // 把最后一个参数值放到指定集合的第一个出现中间参数的前面，如果中间参数值存在的话。
        redisTemplate.opsForList().leftPush("list", "a", "n"); // 把n放在集合中，第一个出现a的位置的前面
        template = redisTemplate.opsForList().range("list", 0, -1);
        System.out.println("通过leftPush(K key, V pivot, V value)方法把值放到指定参数值前面:" + template); //[c, b, n, a]

        // 获取集合长度
        long listLength = redisTemplate.opsForList().size("list");
        System.out.println("通过size(K key)方法获取集合list的长度为:" + listLength);

        // 查看所有的元素
        List<Object> list = redisTemplate.opsForList().range("list", 0, -1); //list集合：全部元素
        System.out.println("通过range(K key, long start, long end)方法获取指定范围的集合值:" + list); //[c, b, a]
        
        // 获取集合指定位置的元素
        String listValue = redisTemplate.opsForList().index("list", 1) + "";//获得list集合的，index=1 位置的元素的值
        System.out.println("通过index(K key, long index)方法获取指定位置的值:" + listValue); //b


        // 移除集合中的左边第一个元素
        Object popValue = redisTemplate.opsForList().leftPop("list");
        System.out.print("通过leftPop(K key)方法移除的元素是:" + popValue);
        template = redisTemplate.opsForList().range("list", 0, -1);
        System.out.println(",剩余的元素是:" + template);  // [x, w, c, b, n, a, d]

        // 移除集合中右边的元素，同时在左边加入一个元素。
        popValue = redisTemplate.opsForList().rightPopAndLeftPush("list", "12");
        System.out.print("通过rightPopAndLeftPush(K sourceKey, K destinationKey)方法移除的元素是:" + popValue);
        template = redisTemplate.opsForList().range("list", 0, -1);
        System.out.println(",剩余的元素是:" + template);

        /**
         * 从存储在键中的列表中删除等于值的元素的第一个计数事件。
         * count > 0：删除等于从左到右移动的值的第一个元素；
         * count< 0：删除等于从右到左移动的值的第一个元素；
         * count = 0：删除等于value的所有元素。
         */
        long removeCount = redisTemplate.opsForList().remove("list", 0, "w");
        list = redisTemplate.opsForList().range("list", 0, -1);
        System.out.println("通过remove(K key, long count, Object value)方法移除元素数量:" + removeCount);
        System.out.println("剩余的元素:" + list);

        // 截取集合元素长度，保留长度内的数据。
        redisTemplate.opsForList().trim("list", 0, 5);
        list = redisTemplate.opsForList().range("list", 0, -1);
        System.out.println("通过trim(K key, long start, long end)方法截取后剩余元素:" + list);
        
        //删除集合元素
        /**
         * 不管是leftPush还是rightPush都可以用leftPop或者rightPoP任意一种获取到其中的值，不过就是获取的遍历方向不一样。
         * 有学过数据结构的人都知道里面循环链表是可以前后遍历的
         * 其实就是遍历方向不同，所以效率也不同。所以最好leftPush用leftPoP遍历，rightPush用rightPoP遍历
         * pop之后，就没了
         */
        List<String> resultList1 = (List<String>) redisTemplate.opsForList().leftPop("listkey1");
        List<String> resultList2 = (List<String>) redisTemplate.opsForList().rightPop("listkey2");
        System.out.println("resultList1:" + resultList1);
        System.out.println("resultList2:" + resultList2);

        // 移除集合中左边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出
        // leftPop(K key, long timeout, TimeUnit unit)

    }
    
    @RequestMapping("testOpsForListExpire")
    public void testOpsForListExpire(){

        redisTemplate.opsForList().leftPushAll("list212", "w", "x", "y");
        redisTemplate.expire("list212", 20, TimeUnit.SECONDS);
    }
}
