package cn.yumy.doc2swagger.core.models;

import lombok.Data;

@Data
public class ResponseModel {
    /**
     * 响应状态码
     */
    private int _statusCode;
    private ReturnModel _returnModel = new ReturnModel();

}
