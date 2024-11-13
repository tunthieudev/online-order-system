package com.example.TakeOrder.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.example.TakeOrder.dto.Order;
import com.example.TakeOrder.dto.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/api/take-order")
@RestController
public class TakeOrderController {
    private static final String productURL = "http://localhost:8080/api/product";
    private static final String orderURL = "http://localhost:8080/api/order";
    private static final String cartURL = "http://localhost:8080/api/cart";
    private static final String emailURL = "http://localhost:8080/api/email";
    @Autowired
    private RestTemplate rest;
    private ObjectMapper mapper = new ObjectMapper();

    @SuppressWarnings("unchecked")
    @PostMapping("")
    public void takeOrder(@RequestBody HashMap<String, Object> orderMap) {
        System.out.println(orderMap.toString());
        try {
            var items = (List<LinkedHashMap<String, Object>>) orderMap.get("items");
            ArrayList<Product> products = new ArrayList<>();
            for (var i : items) {
                int pId = Integer.parseInt(i.get("productId").toString());
                int pQuantity = Integer.parseInt(i.get("quantity").toString());
                int pPrice = Integer.parseInt(i.get("totalPrice").toString());
                Object isProductExists = get(productURL + "/" + pId, Object.class);
                if (isProductExists != null) {
                    Product p = new Product();
                    p.setId(pId);
                    p.setQuantity(pQuantity);
                    p.setTotalPrice(pPrice);
                    products.add(p);
                    post(productURL + "/take", p, null);
                }
            }

            Order order = new Order();
            order.setTotalPrice(Integer.parseInt(orderMap.get("totalPrice").toString()));
            order.setQuantity(Integer.parseInt(orderMap.get("totalProduct").toString()));
            order.setReceiverPhone(orderMap.get("phone").toString());
            order.setReceiverEmail(orderMap.get("email").toString());
            order.setReceiverAddress(orderMap.get("address").toString());
            order.setReceiverName(orderMap.get("name").toString());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            order.setDate(LocalDateTime.now().format(formatter));
            order.setStatus("PENDING");
            post(orderURL + "/save", order);

            delete(cartURL + "/delete", null);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("tuanvp34@gmail.com");
            message.setReplyTo(order.getReceiverEmail());
            message.setSubject("Đặt hàng thành công");
            message.setText("Bạn đã đặt hàng thành công với hóa đơn thanh toán:\n" + order.toString());
            post(emailURL + "/send", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <T> T get(String url, Class<T> valueType) throws Exception {
        return callService(url, null, valueType, HttpMethod.GET);
    }

    private <T> T post(String url, Object obj, Class<T> valueType) throws Exception {
        return callService(url, obj, valueType, HttpMethod.POST);
    }

    private void post(String url, Object obj) throws Exception {
        post(url, obj, null);
    }

    private void delete(String url, Object obj) throws Exception {
        callService(url, obj, null, HttpMethod.DELETE);
    }

    private <T> T callService(String url, Object obj, Class<T> valueType, HttpMethod method) throws Exception {
        HttpEntity<String> entity = null;
        if (obj != null) {
            String data = mapper.writer().withDefaultPrettyPrinter().writeValueAsString(obj);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            entity = new HttpEntity<String>(data, headers);
        }
        var newData = rest.exchange(url, method, entity, String.class).getBody();
        try {
            return mapper.readValue(newData, valueType);
        } catch (Exception e) {
            return null;
        }
    }
}
