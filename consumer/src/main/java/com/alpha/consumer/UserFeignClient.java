package com.alpha.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservice-provider")
public interface UserFeignClient {
    @GetMapping(value = "/{id}")
    User findById(@PathVariable("id") Long id);
}
