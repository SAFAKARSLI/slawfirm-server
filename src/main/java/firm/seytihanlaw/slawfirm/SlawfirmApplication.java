package firm.seytihanlaw.slawfirm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication()
public class SlawfirmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlawfirmApplication.class, args);
	}
}
