package com.gzgqq.seckill.dao;

import com.gzgqq.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by weifengyan on 16/6/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;
    @Test
    public void testReduceNumber() throws Exception {
        long id = 1005;
        int num = seckillDao.reduceNumber(id, new Date());
        System.out.println(num);
    }

    @Test
    public void testQueryById() throws Exception {
        long id = 1005;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void testQueryAll() throws Exception {

        List<Seckill> lists = seckillDao.queryAll(0, 5);

        for (Seckill item: lists) {
            System.out.printf(item.getName());
        }
    }
}