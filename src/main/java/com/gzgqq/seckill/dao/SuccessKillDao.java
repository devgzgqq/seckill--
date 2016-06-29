package com.gzgqq.seckill.dao;

import com.gzgqq.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by weifengyan on 16/6/24.
 */
public interface SuccessKillDao {


    /**
     * 插入购买明细,可过滤重复
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);


    /**
     * 根据ID查询SuccessKilled并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(long seckillId);
}
