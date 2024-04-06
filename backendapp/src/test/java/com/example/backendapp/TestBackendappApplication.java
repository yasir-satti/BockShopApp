package com.example.backendapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
//import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestBackendappApplication {

//	@Bean
//	@ServiceConnection
//	MSSQLServerContainer<?> sqlServerContainer() {
//		return new MSSQLServerContainer<>(DockerImageName.parse("mcr.microsoft.com/mssql/server:latest"));
//	}

	public static void main(String[] args) {
		SpringApplication.from(BackendappApplication::main).with(TestBackendappApplication.class).run(args);
	}

}
