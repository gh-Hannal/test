package com.example.apple.calculator;

/**
 * Created by apple on 2018/4/16.
 */

public class Success {
    private int isSuccess;//状态标志位 0为成功 1为除零错 2是tan无定义
    private Double value;
    private Boolean limit_precision;
    Success(int bool,Double num,Boolean limit){
        isSuccess = bool;
        value = num;
        limit_precision = limit;
    }
    public Double getValue(){
        return value;
    }
    public int getState(){  return isSuccess;  }
    public Boolean getLimit_precision(){ return limit_precision;}
}
