package com.ecommerce.controllers;

import com.ecommerce.services.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/")
@ApiOperation("Architecture")
public class TestController {

    public final OrderService orderService;

    public TestController(OrderService orderService) {
        this.orderService = orderService;
    }
}
