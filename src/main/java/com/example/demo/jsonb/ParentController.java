package com.example.demo.jsonb;

import org.hibernate.annotations.Any;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//@RequiredArgsConstructor   // 1) add @RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/parent")
public class ParentController {

    // 3) add @Autowired without final
    /*@Autowired
    private ParentRepo parentRepo;*/
    private final ParentRepo parentRepo;
    // 2) add constructor parameter
    public ParentController(ParentRepo parentRepo) {
        this.parentRepo = parentRepo;
    }

    @GetMapping()
    public List<Parent> getAllBjson() {
        return parentRepo.findAll();
    }

    @GetMapping("/testingBjson")
    public Map<java.lang.String, java.lang.Object> testingBjson() {
        parentRepo.save(new Parent(
                        "parent1",
                        Arrays.asList(new Child("child1"), new Child("child2")),
                        new Bio("bio1")
                )
        );

        Integer id = 1;
        Parent result = parentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parent " + id + " is not found!"));
        List<Child> children = result.getChildren();
        Bio bio = result.getBio();

        return Map.of("parent", result,
                "children", children,
                "bio", bio);
    }

    @PostMapping
    public Map<java.lang.String, java.lang.Object> insertBjson(@RequestBody Parent parent) {
        System.out.println("INPUT: parent = " + parent);
        Parent result = parentRepo.save(parent);

        return Map.of("parent", result,
                "children", result.getChildren(),
                "bio", result.getBio());

    }


}
