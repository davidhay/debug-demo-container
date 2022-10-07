package com.container.demo;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class DemoApplication implements CommandLineRunner {

	private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";

	@Autowired
	Environment env;

	@Value("${delay.minutes:1}")
	int delayMinutes;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) {
			log.info("delay.minutes [{}]", delayMinutes);
			log.info("env start");
			new TreeMap<>(System.getenv()).forEach((key,value) -> {
				log.info("env[{}]   [{}]", key, value);
			});
			log.info("env end");
	}

	int count = 1;

	//fixedDelay means next time between scheduled tasks - if you pause one, you pause them all
	@Scheduled(fixedDelayString = "${delay.minutes:1}", timeUnit = TimeUnit.MINUTES, initialDelay = 0)
	@SneakyThrows
	void writeATempFile() {
		String prefix = String.format("test-%d-", count);
		File temp = File.createTempFile(prefix, ".txt");
		try(FileWriter fw = new FileWriter(temp)) {
			String line = String.format("File[%d] data written @ [%s]", count, getTimestamp());
			fw.write(line);
		}
		log.info("Written To [{}]", temp.getCanonicalPath());
		count++;
	}

	private String getTimestamp() {
			LocalDateTime localDateTime = LocalDateTime.now(); //get current date time
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
			String formatDateTime = localDateTime.format(formatter);
			return formatDateTime;
	}
}
