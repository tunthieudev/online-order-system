package vn.hoidanit.laptopshop.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.model.Order;
import vn.hoidanit.laptopshop.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    
    public List<Order> getAllOrder() {
        return this.orderRepository.findAll();
    }

    public Order getOrder(int id) {
        return this.orderRepository.findById(id).get();
    }

    public List<Order> getOrderByUser(int id) {
        return this.orderRepository.findByUserId(id);
    }

    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }

    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

}
