package com.example.demo.testingClinicOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Service
public class ClinicOrderService {

    @Autowired
    private ClinicOrderRepository itemRepo;

    // === CRUD ===
    // 1. Read all entries of clinic order
    public List<ClinicOrder> getAllClinicOrders() {
        return itemRepo.findAll();
    }

    // 2. Read entry of clinic order with particular id
    public ClinicOrder getClinicOrder(UUID id) {
        return itemRepo.findById(id)
                .orElse(null);
    }

    // 3. Create new entry of clinic order
    public ClinicOrder addClinicOrder(ClinicOrder item) {
        item.setCode(getNextCode());   // use generated code
        return itemRepo.save(item);
    }

    // 4. Update entry of clinic order with particular id
    public ClinicOrder updateClinicOrder(ClinicOrder item) {
        ClinicOrder itemGet = getClinicOrder(item.getId());
        item.setCreatedAt(itemGet.getCreatedAt());
        item.setCode(itemGet.getCode());   // use back code
        return itemRepo.save(item);
    }

    // 5. Delete entry of clinic order with particular id
    public ClinicOrder deleteClinicOrder(UUID id) {
        ClinicOrder itemTemp = getClinicOrder(id);
        itemRepo.deleteById(id);
        return itemTemp;
    }

    // 6. Delete entries of clinic order with particular ids (mass destroy)
    public List<ClinicOrder> deleteClinicOrders(List<UUID> ids) {
        List<ClinicOrder> itemTemps = new ArrayList<>();
        for (UUID id : ids) {
            itemTemps.add(getClinicOrder(id));
        }
        itemRepo.deleteAllById(ids);
        return itemTemps;
    }

    // Reusable / small function(s)
    // Read all entries of clinic order between particular date range
    public List<ClinicOrder> getAllClinicOrders(LocalDateTime createdAtStart, LocalDateTime createdAtEnd) {
        List<ClinicOrder> allClinics;

        if (createdAtStart != null && createdAtEnd != null) {
            allClinics = itemRepo.findAllByCreatedAtBetween(createdAtStart, createdAtEnd);
        } else {
            allClinics = itemRepo.findAll();
        }

        return allClinics;
    }

    // Generate next code by increment current max code by 1
    public String getNextCode() {
        // select max value for code col (handle null)
        // version 1 - order 'code' row by desc & get the top one
        /*String maxCode = itemRepo.findAll(Sort.by("code")).get(0).getCode();   // OPT: findTop()
        System.out.println("(itemRepo.findAll(Sort.by(\"code\"))) = " + (itemRepo.findAll(Sort.by("code"))));
        System.out.println("(itemRepo.findAll(Sort.by(\"code\")).get(0)) = " + (itemRepo.findAll(Sort.by("code")).get(0)));
        System.out.println("(itemRepo.findAll(Sort.by(\"code\")).get(0).getCode()) = " + (itemRepo.findAll(Sort.by("code")).get(0).getCode()));
        System.out.println("maxCode = " + maxCode);*/
        // version 2 - direct get by sql
        Integer maxCode = itemRepo.findMaxCode();

        Integer code = maxCode != null ? maxCode + 1 : 1;   // 0+1 == 1 // OPT: Integer.parseInt(maxCode)

        // increment by one & left pad code in 10 length
        return String.format("%010d", code++);
    }

    /*// Read entry of clinic order's test order with particular id
    public Page<ClinicOrder> getTestOrderByIdWithPagination(UUID id, int pageSize) {
        ClinicOrder clinicOrder = getClinicOrder(id);
        int pageNo = clinicOrder.getClinicTests().size() / pageSize;
        Pageable paging = (Pageable) PageRequest.of(pageNo, pageSize);

        //return itemRepo.findTestOrderById(id, paging);
        return itemRepo.findTestOrderById(id, PageRequest.of(pageNo-1, pageSize));
    }*/

}
