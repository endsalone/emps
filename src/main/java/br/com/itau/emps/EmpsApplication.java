package br.com.itau.emps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "br.com.itau.emps.core.entities")
@EnableJpaRepositories(basePackages = "br.com.itau.emps.data.repositories")
public class EmpsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmpsApplication.class, args);
    }
}
