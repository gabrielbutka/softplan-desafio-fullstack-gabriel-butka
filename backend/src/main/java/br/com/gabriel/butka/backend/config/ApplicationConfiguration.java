package br.com.gabriel.butka.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.gabriel.butka.backend.repository")
public class ApplicationConfiguration {

    // nada

}
