package com.alibou.OrderService.Handler;

import java.util.Map;

public record ErrorResponce (
        Map<String,String> errors
){
}
