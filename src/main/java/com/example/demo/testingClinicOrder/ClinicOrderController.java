package com.example.demo.testingClinicOrder;

import com.example.demo.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/clorder")
public class ClinicOrderController {

    @Autowired
    private ClinicOrderService itemService;

    @Autowired
    private ClinicTestService clinicTestService;
    @Autowired
    private ClinicOrderService clinicOrderService;

    // === debugging START ===
    // Get all orders
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<ClinicOrder>> getAllOrders() {
        try {
            // === debugging START ===
            List<ClinicOrder> allClinicOrders = itemService.getAllClinicOrders();

            System.out.println("DEBUGGING: allClinicOrders = " + allClinicOrders);

            allClinicOrders.forEach((o) -> {

                System.out.println("clinic order id :: " + o.getId());
                System.out.println("o.getClinicTests() = " + o.getClinicTests());

                o.getClinicTests().forEach((test -> {
                    System.out.println("test :: " + test.getId());
                    //System.out.println("test = " + test);
                    System.out.println("test.getAmount() = " + test.getAmount());
                }));
            });
            // === debugging END ===

            if (allClinicOrders.size() > 0) {
                return new ResponseEntity<>(allClinicOrders, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Add new order (2)
    @PostMapping(value = "/saveOrder2", consumes = {"application/json"}, produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<ClinicOrder> saveOrder2() {
        try {
            // create Order object
            ClinicOrder clinicOrder = new ClinicOrder();
            clinicOrder.setAmount(123);
            ClinicOrder clinicOrderSaved = clinicOrderService.addClinicOrder(clinicOrder);
            System.out.println("clinicOrderSaved = " + clinicOrderSaved);

            ClinicTest clinicTest = new ClinicTest();
            clinicTest.setAmount(10);
            clinicTest.setDateOfBirth(LocalDate.parse("2222-12-22"));
            clinicTest.setClinicOrder(clinicOrderService.getClinicOrder(UUID.fromString("b43bfef7-3eb2-43fb-bfe7-2cb76abbbcfa")));
            clinicTest.setClinicOrder(clinicOrderSaved);
            ClinicTest clinicTestUpdated = clinicTestService.addClinicTest(clinicTest);
            System.out.println("clinicTestUpdated = " + clinicTestUpdated);

            ClinicTest clinicTest2 = new ClinicTest();
            clinicTest2.setAmount(20);
            clinicTest2.setDateOfBirth(LocalDate.parse("2222-12-22"));
            clinicTest2.setClinicOrder(clinicOrderService.getClinicOrder(UUID.fromString("b43bfef7-3eb2-43fb-bfe7-2cb76abbbcfa")));
            clinicTest2.setClinicOrder(clinicOrderSaved);
            ClinicTest clinicTestUpdated2 = clinicTestService.addClinicTest(clinicTest2);
            System.out.println("clinicTestUpdated2 = " + clinicTestUpdated2);

            getAllOrders();
            /*// === debugging START ===
            List<ClinicOrder> allClinicOrders = itemService.getAllClinicOrders();

            System.out.println("DEBUGGING: allClinicOrders = " + allClinicOrders);

            allClinicOrders.forEach((o) -> {

                System.out.println("clinic order id :: " + o.getId());
                System.out.println("o.getClinicTests() = " + o.getClinicTests());

                o.getClinicTests().forEach((test -> {
                    System.out.println("test :: " + test.getId());
                    //System.out.println("test = " + test);
                    System.out.println("test.getName() = " + test.getName());
                }));
            });
            // === debugging END ===*/

            return (clinicOrderSaved==null) ? new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED) : new ResponseEntity<>(clinicOrderSaved, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // === debugging END ===

}
