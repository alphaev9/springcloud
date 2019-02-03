package com.alpha.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;
    /*@Autowired
    private UserFeignClient userFeignClient;*/

    @GetMapping("/user/{id}")
    @HystrixCommand(fallbackMethod = "fallBack")
    public User findById(@PathVariable Long id) {
        /*List<ServiceInstance> instances =
                discoveryClient.getInstances("microservice-provider");
        ServiceInstance instance = instances.get(0);
        URI uri = instance.getUri();*/

        return restTemplate.getForObject("http://microservice-provider/" + id, User.class);
//        return userFeignClient.findById(id);
    }

    public User fallBack(Long id) {
        User user = new User();
        user.setName("fail");
        user.setId(-1L);
        return user;
    }
}
