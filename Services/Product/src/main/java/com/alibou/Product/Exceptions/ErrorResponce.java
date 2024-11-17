package com.alibou.Product.Exceptions;

import java.util.Map;

public record ErrorResponce(
        Map<String,String> errors
) {
}
