package com.example.demo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/order")
public class OrderController {   // TestingOneToManyUnidirectionalMapping

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    // Add new order
    @PostMapping(value = "/saveOrder", consumes = {"application/json"}, produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Order> saveOrder() {
        try {
            // create Order object
            Order order = new Order();

            OrderItem orderItem = new OrderItem();
            orderItem.setImageUrl("image_url.png");
            orderItem.setPrice(new BigDecimal(100));
            /*// add orderitem to order
            order.add(orderItem);*/
            OrderItem orderItemSaved = orderItemRepository.save(orderItem);
            order.add(orderItemSaved);

            OrderItem orderItem2 = new OrderItem();
            orderItem2.setImageUrl("image_url.png");
            orderItem2.setPrice(new BigDecimal(200));
            /*// add orderItem2 to order
            order.add(orderItem2);*/
            OrderItem orderItemSaved2 = orderItemRepository.save(orderItem2);
            order.add(orderItemSaved2);

            order.setOrderTrackingNumber("1000");
            order.setStatus("IN PROGRESS");
            // total amount of the order
            order.setTotalPrice(order.getTotalAmount());

            // Quantity of the order items
            order.setTotalQuantity(2);

            Order itemCreated = orderRepository.save(order);

            // update main table
            orderItemSaved.setOrder(itemCreated);
            OrderItem orderItemUpdated = orderItemRepository.save(orderItemSaved);
            orderItemSaved2.setOrder(itemCreated);
            OrderItem orderItemUpdated2 = orderItemRepository.save(orderItemSaved2);
            System.out.println("orderItemUpdated = " + orderItemUpdated);
            System.out.println("orderItemUpdated2 = " + orderItemUpdated2);

            return (itemCreated==null) ? new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED) : new ResponseEntity<>(itemCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Add new order (2)
    @PostMapping(value = "/saveOrder2", consumes = {"application/json"}, produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Order> saveOrder2() {
        try {
            // create Order object
            Order order = new Order();

            order.setOrderTrackingNumber("1000");
            order.setStatus("IN PROGRESS");
            // total amount of the order
            order.setTotalPrice(order.getTotalAmount());

            // Quantity of the order items
            order.setTotalQuantity(2);

            Order itemCreated = orderRepository.save(order);

            ///
            //order.add(orderItem);
            OrderItem orderItem = new OrderItem();
            orderItem.setImageUrl("image_url.png");
            orderItem.setPrice(new BigDecimal(100));
            orderItem.setOrder(itemCreated);
            OrderItem orderItemSaved = orderItemRepository.save(orderItem);
            System.out.println("orderItemSaved = " + orderItemSaved);
//            OrderItem orderItemSaved = orderItemRepository.save(orderItem);
            // add orderitem to order
//            order.add(orderItemSaved);

            //order.add(orderItem2);
            OrderItem orderItem2 = new OrderItem();
            orderItem2.setImageUrl("image_url.png");
            orderItem2.setPrice(new BigDecimal(200));
            orderItem2.setOrder(itemCreated);
            OrderItem orderItemSaved2 = orderItemRepository.save(orderItem2);
            System.out.println("orderItemSaved2 = " + orderItemSaved2);
//            OrderItem orderItemSaved2 = orderItemRepository.save(orderItem2);
            // add orderItem2 to order
//            order.add(orderItemSaved2);

            getAllOrders();

            return (itemCreated==null) ? new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED) : new ResponseEntity<>(itemCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update order by id
    @PatchMapping("/updateOrder")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ResponseEntity<Order> updateOrder() {
        try {
            Order order = orderRepository.findById(1L).get();
            order.setStatus("DELIVERED");
            Order itemUpdated = orderRepository.save(order);
            System.out.println("itemUpdated = " + itemUpdated);

            getAllOrders();

            return (itemUpdated==null) ? new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED) : new ResponseEntity<>(itemUpdated, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all orders
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            List<Order> items = orderRepository.findAll();

            System.out.println("DEBUGGING: items = " + items);

            items.forEach((o) -> {

                System.out.println("order id :: " + o.getId());
                System.out.println("o.getOrderItems() = " + o.getOrderItems());

                o.getOrderItems().forEach((orderItem -> {
                    System.out.println("orderItem :: " + orderItem.getId());
                    //System.out.println("orderItem = " + orderItem);
                    System.out.println("orderItem.getPrice() = " + orderItem.getPrice());
                }));
            });

            if (items.size() > 0) {
                return new ResponseEntity<>(items, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get individual order by order tracking number
    @GetMapping("/findByOrderTrackingNumber")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Order> findByOrderTrackingNumber() {
        try {
            Order itemGet = orderRepository.findByOrderTrackingNumber("1000");

            // add fetch type as EAGER
//        order.getOrderItems().forEach((o) -> {
//            System.out.println(o.getId());
//        });

            if (itemGet != null) {  // item presents
                return new ResponseEntity<>(HttpStatus.OK);   // convert entity to DTO
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete order by id
    @DeleteMapping("/deleteOrder")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ResponseEntity<?> deleteOrder() {
        try {
            orderRepository.deleteById(1L);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
