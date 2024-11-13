package vn.hoidanit.laptopshop.controller;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.hoidanit.laptopshop.model.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping("/take")
    public void takeProduct(@RequestBody HashMap<String, Object> p) {
        Product product = getProduct(Integer.parseInt(p.get("id").toString()));
        int amount = Integer.parseInt(p.get("quantity").toString());
        product.setQuantity(product.getQuantity() - amount);
        productService.updateProduct(product);
    }
}
