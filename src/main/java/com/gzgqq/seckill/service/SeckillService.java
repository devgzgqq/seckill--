package com.gzgqq.seckill.service;


import com.gzgqq.seckill.dto.Exposer;
import com.gzgqq.seckill.dto.SeckillExecution;
import com.gzgqq.seckill.entity.Seckill;
import com.gzgqq.seckill.exception.ReapeatKillExcepion;
import com.gzgqq.seckill.exception.SeckillCloseException;
import com.gzgqq.seckill.exception.SeckillException;

import java.util.List;

/**
 * 业务接口:站在"使用者"的角度去设计接口
 * 三个方面:方法定义力度,参数,返回类型
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();


    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开始时输出秒杀接口地址,否者输出系统时间或秒杀时间.
     * @param seckillId
     * @return
     */
    Exposer exportSeckillUrl(long seckillId);


    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, ReapeatKillExcepion, SeckillCloseException;
}
