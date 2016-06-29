package com.gzgqq.seckill.exception;

/**
 * 重复秒杀异常
 * Created by weifengyan on 16/6/28.
 */
public class ReapeatKillExcepion extends SeckillException{
    public ReapeatKillExcepion(String message) {
        super(message);
    }

    public ReapeatKillExcepion(String message, Throwable cause) {
        super(message, cause);
    }
}
