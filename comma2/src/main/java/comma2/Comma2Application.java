package comma2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("comma2")
@MapperScan(basePackages ="comma2.mapper")
public class Comma2Application {

	public static void main(String[] args) {
		SpringApplication.run(Comma2Application.class, args);
	}

}
