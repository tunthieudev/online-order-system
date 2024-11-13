package vn.hoidanit.laptopshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import vn.hoidanit.laptopshop.model.Order;
import vn.hoidanit.laptopshop.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public Order saveOrder(@RequestBody String oJson) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Order o = mapper.readValue(oJson, Order.class);
        return orderService.updateOrder(o);
    }
}
