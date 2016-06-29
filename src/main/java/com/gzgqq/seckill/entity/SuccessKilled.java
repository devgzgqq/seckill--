package com.gzgqq.seckill.entity;

import java.util.Date;

/**
 * Created by weifengyan on 16/6/24.
 */
public class SuccessKilled {

    private long seckillId;
    private long userPhone;
    private short stats;
    private Date createTime;

    /*
    * 多对一
    */
    private Seckill seckill;

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getStats() {
        return stats;
    }

    public void setStats(short stats) {
        this.stats = stats;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", stats=" + stats +
                ", createTime=" + createTime +
                '}';
    }
}
