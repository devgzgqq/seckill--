package com.gzgqq.seckill.service.impl;

import com.gzgqq.seckill.dao.SeckillDao;
import com.gzgqq.seckill.dao.SuccessKillDao;
import com.gzgqq.seckill.dto.Exposer;
import com.gzgqq.seckill.dto.SeckillExecution;
import com.gzgqq.seckill.entity.Seckill;
import com.gzgqq.seckill.entity.SuccessKilled;
import com.gzgqq.seckill.enums.SeckillStatEnum;
import com.gzgqq.seckill.exception.ReapeatKillExcepion;
import com.gzgqq.seckill.exception.SeckillCloseException;
import com.gzgqq.seckill.exception.SeckillException;
import com.gzgqq.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by weifengyan on 16/6/29.
 */

@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKillDao successKillDao;

    //用于混淆MD5
    private final String slat = "fja;lsfjoeijworn#&(*^$(^(#@&)#&(1214131290809";

    private String getMd5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {

        Seckill seckill = seckillDao.queryById(seckillId);

        if(seckill == null) {
            return new Exposer(false,seckillId);
        }

        Long startTime = seckill.getStartTime().getTime();
        Long endTime = seckill.getEndTime().getTime();

        Long nowTime = new Date().getTime();

        if(nowTime < startTime || nowTime > endTime) {
            return new Exposer(false, seckillId, nowTime, startTime, endTime );
        }
        //转化特定字符串
        String md5 = getMd5(seckillId);
        return new Exposer(true,md5,seckillId);
    }

    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, ReapeatKillExcepion, SeckillCloseException {

        if(md5 == null || !md5.equals(getMd5(seckillId))) {
            throw new SeckillException("秒杀的数据被重写了");
        }

        // 执行秒杀逻辑 减库存+记录
        Date nowTime = new Date();

        try {

            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);

            if(updateCount <= 0) {
                throw new SeckillCloseException("秒杀已经关闭!");
            } else {
                //记录购买行为
                int insertCount = successKillDao.insertSuccessKill(seckillId,userPhone);
                if(insertCount <= 0) {
                    throw new ReapeatKillExcepion("不能重复秒杀!");
                } else {
                    // 秒杀成功
                    SuccessKilled successKilled = successKillDao.queryByIdWithSeckill(seckillId);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS,successKilled);
                }
            }

        } catch(SeckillCloseException e1) {
            throw e1;
        } catch(ReapeatKillExcepion e2) {
            throw e2;}
        catch (Exception e) {
            logger.error(e.getMessage(),e);
            // 所有编译期异常 转化为运行期异常
            throw new SeckillException("秒杀异常" + e.getMessage());
        }
    }

}
