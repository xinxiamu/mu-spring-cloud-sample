package com.ymu.consulconsumer;

import com.ymu.consulconsumer.feign.ConsulServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.ymu.consulconsumer.feign"})
@RestController
public class ConsulConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsulConsumerApplication.class, args);
	}

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ConsulServiceClient consulServiceClient;

	//轮询负载访问consul-server1、consul-server2
	//consul-server1、consul-server2是同一个服务，为了模拟负载访问，所以搞成两个，端口不同。
	@GetMapping("/getServerPort")
	public String getServerPort() {
		return restTemplate.getForObject("http://consul-service/consul/server/port", String.class);
	}

	//feign接口方式请求服务接口
	//TODO 加入熔断
	@GetMapping("/getServerPortFeign")
	public String getServerPortFeign() {
		return consulServiceClient.getPort();
	}

}
