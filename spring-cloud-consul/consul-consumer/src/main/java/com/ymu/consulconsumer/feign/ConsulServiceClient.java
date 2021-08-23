package com.ymu.consulconsumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "consul-service")
public interface ConsulServiceClient {

    @GetMapping("/consul/server/port")
    String getPort();
}
