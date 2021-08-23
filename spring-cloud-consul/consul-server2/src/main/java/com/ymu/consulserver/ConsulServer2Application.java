package com.ymu.consulserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsulServer2Application {

	@Value("${server.port}")
	private int port;

	public static void main(String[] args) {
		SpringApplication.run(ConsulServer2Application.class, args);
	}

	@GetMapping("/consul/server/port")
	public String getPort(){
		return String.format("服务端口{%s}", port);
	}

}
