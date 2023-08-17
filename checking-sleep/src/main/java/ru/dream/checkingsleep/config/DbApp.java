package ru.dream.checkingsleep.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "${packages.common.component}", "${app.packages}" })
public class DbApp implements ApplicationRunner {


}
