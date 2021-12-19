package pl.polsl.UnoApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigurationProperties
@SpringBootApplication
public class UnoApiBE {

    public static void main(String[] args) {
        SpringApplication.run(UnoApiBE.class, args);
    }
}
