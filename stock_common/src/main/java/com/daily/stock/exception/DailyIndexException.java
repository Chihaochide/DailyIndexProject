package com.daily.stock.exception;

import com.daily.stock.dtos.AppHttpCodeEnum;
import com.daily.stock.dtos.ResponseCode;
import lombok.Getter;

@Getter
public class DailyIndexException extends RuntimeException {

    private Integer status; // 状态码

    public DailyIndexException(Integer status,String msg){
        super(msg);
        this.status = status;
    }
    public DailyIndexException(ResponseCode codeEnum){
        super(codeEnum.getMessage());
        this.status = codeEnum.getCode();
    }


}
