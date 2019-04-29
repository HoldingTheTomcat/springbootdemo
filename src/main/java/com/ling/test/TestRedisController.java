package com.ling.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add("set1", "22");
        set.add("set1", "33");
        set.add("set1", "44");
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

        /**
         * 不管是leftPush还是rightPush都可以用leftPop或者rightPoP任意一种获取到其中的值，不过就是获取的遍历方向不一样。
         * 有学过数据结构的人都知道里面循环链表是可以前后遍历的
         * 其实就是遍历方向不同，所以效率也不同。所以最好leftPush用leftPoP遍历，rightPush用rightPoP遍历
         */
        List<String> resultList1 = (List<String>) redisTemplate.opsForList().leftPop("listkey1");
        List<String> resultList2 = (List<String>) redisTemplate.opsForList().rightPop("listkey2");
        System.out.println("resultList1:" + resultList1);
        System.out.println("resultList2:" + resultList2);

    }
}
