package com.workintech.dependencyInjection.controller;

import com.workintech.dependencyInjection.dto.DeveloperResponse;
import com.workintech.dependencyInjection.model.Developer;
import com.workintech.dependencyInjection.model.DeveloperFactory;
import com.workintech.dependencyInjection.tax.DeveloperTax;
import com.workintech.dependencyInjection.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping(path="/developers")
public class DeveloperController {
   private  Map<Integer, Developer>developers;
    private Taxable taxable;

    @Autowired
    public DeveloperController(DeveloperTax developerTax) {
        this.taxable = developerTax;
    }
    @PostConstruct
    private void init() {
       developers=new HashMap<>();
    }
    @GetMapping
    public List<Developer> getAllDevelopers() {
        return developers.values().stream().toList();
    }
    @GetMapping("/{id}")
    public DeveloperResponse getDeveloperById(@PathVariable("id") Integer id) {
        return new DeveloperResponse(this.developers.get(id),"succeed",HttpStatus.OK.value());
    }

    @PostMapping
    public DeveloperResponse save(@RequestBody Developer developer) {
       Developer createdDeveloper= DeveloperFactory.createDeveloper(developer,taxable);
       if(createdDeveloper != null){
           developers.put(createdDeveloper.getId(),createdDeveloper);
       }
       return new DeveloperResponse(createdDeveloper,"Succeed", HttpStatus.OK.value());
    }


    @PutMapping("/{id}")
    public DeveloperResponse updateDeveloper(@PathVariable Integer id, @RequestBody Developer developer) {
        Developer existingDeveloper = developers.get(id);
        if (existingDeveloper != null) {
          developer.setId(id);
          Developer updatedDeveloper=DeveloperFactory.createDeveloper(developer,taxable);
          this.developers.put(updatedDeveloper.getId(),updatedDeveloper);
          return new DeveloperResponse(updatedDeveloper,"succeed",HttpStatus.OK.value());
        }
        else{
            return new DeveloperResponse(null,"developer exist",HttpStatus.NOT_FOUND.value());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteDeveloper(@PathVariable int id) {
        developers.remove(id);
    }
}
