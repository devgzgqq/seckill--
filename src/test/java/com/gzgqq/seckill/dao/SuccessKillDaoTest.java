package com.gzgqq.seckill.dao;

import com.gzgqq.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by weifengyan on 16/6/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKillDaoTest {

    @Resource
    private SuccessKillDao successKillDao;

    @Test
    public void testInsertSuccessKill() throws Exception {
        long id = 1005L;
        int num = successKillDao.insertSuccessKill(id, 15002977234L);
        System.out.println(num);
    }

    @Test
    public void testQueryByIdWithSeckill() throws Exception {

        long id = 1005L;
        SuccessKilled successKilled = successKillDao.queryByIdWithSeckill(id);
        System.out.println(successKilled.getSeckill().getName());
    }
}