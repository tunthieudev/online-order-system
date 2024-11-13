package vn.hoidanit.laptopshop.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.model.Cart;
import vn.hoidanit.laptopshop.repository.CartRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getCart() {
        return cartRepository.findAll();
    }

    public void deleteCart() {
        cartRepository.deleteAll();
    }
}
