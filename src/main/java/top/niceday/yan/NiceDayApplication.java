package top.niceday.yan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"top.niceday.yan"})
public class NiceDayApplication {

    public static void main(String[] args) {
        SpringApplication.run(NiceDayApplication.class, args);
    }

}
