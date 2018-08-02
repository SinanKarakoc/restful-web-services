package com.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    //Dynamic Filtering
    @GetMapping("/filtering")
    public MappingJacksonValue getSomeBean() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        BeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("field1","field2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        mapping.setFilters(filters);

        
        return mapping;
    }
// Static Filtering
//    @GetMapping("/filtering")
//    public SomeBean getSomeBean(){
//        return new SomeBean("val","val2","val3");
//    }
   //Dynamic
   @GetMapping("/filtering-list")
   public MappingJacksonValue getListOfSomeBeans(){
       List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"),
               new SomeBean("value12", "value22", "value32"));
       SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
               .filterOutAllExcept("field2","field3");
       FilterProvider filters = new SimpleFilterProvider()
               .addFilter("SomeBeanFilter",filter);
       MappingJacksonValue mapping = new MappingJacksonValue(list);
       mapping.setFilters(filters);

       return mapping;
   }
//Static
//    @GetMapping("/filtering-list")
//    public List<SomeBean> getListOfSomeBeans() {
//        return Arrays.asList(new SomeBean("value1", "value2", "value3"),
//                new SomeBean("value12", "value22", "value32"));
//    }
}
