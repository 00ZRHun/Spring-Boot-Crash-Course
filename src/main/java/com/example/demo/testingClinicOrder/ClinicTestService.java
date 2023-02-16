package com.example.demo.testingClinicOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Service
public class ClinicTestService {

    @Autowired
    private ClinicTestRepository itemRepo;

    // === CRUD ===
    // 1. Read all entries of clinic test
    public List<ClinicTest> getAllClinicTests() {
        return itemRepo.findAll();
    }

    // 2. Read entry of clinic test with particular id
    public ClinicTest getClinicTest(UUID id) {
        return itemRepo.findById(id)
                .orElse(null);
    }

    // 3. Create new entry of clinic test
    public ClinicTest addClinicTest(ClinicTest item) {
        item.setCode(getNextCode());   // use generated code
        return itemRepo.save(item);
    }

    // 4. Update entry of clinic test with particular id
    public ClinicTest updateClinicTest(ClinicTest item) {
        ClinicTest itemGet = getClinicTest(item.getId());
        item.setCreatedAt(itemGet.getCreatedAt());
        item.setCode(itemGet.getCode());   // use back code
        return itemRepo.save(item);
    }

    // 5. Delete entry of clinic test with particular id
    public ClinicTest deleteClinicTest(UUID id) {
        ClinicTest itemTemp = getClinicTest(id);
        itemRepo.deleteById(id);
        return itemTemp;
    }

    // 6. Delete entries of clinic test with particular ids (mass destroy)
    public List<ClinicTest> deleteClinicTests(List<UUID> ids) {
        List<ClinicTest> itemTemps = new ArrayList<>();
        for (UUID id : ids) {
            itemTemps.add(getClinicTest(id));
        }
        itemRepo.deleteAllById(ids);
        return itemTemps;
    }

    // Reusable / small function(s)
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

}
