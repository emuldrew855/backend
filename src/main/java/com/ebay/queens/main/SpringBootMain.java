package com.ebay.queens.main;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

import com.ebay.queens.demo.*;


@SpringBootConfiguration
@SpringBootApplication
public class SpringBootMain {


	public static void main(String[] args) {
		SpringApplication.run(SpringBootMain.class, args);
	}

}