package com.alibou.CustomerService.Handler;

import java.util.Map;

public record ErrorResponce (
        Map<String,String> errors
){
}
