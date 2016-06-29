package com.gzgqq.seckill.exception;

/**
 * 秒杀相关异常
 * Created by weifengyan on 16/6/28.
 */
public class SeckillException extends RuntimeException{
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
