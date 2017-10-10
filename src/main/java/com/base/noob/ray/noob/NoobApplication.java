package com.base.noob.ray.noob;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Slf4j
@SpringBootApplication
@Controller
public class NoobApplication {

	public static void main(String[] args) {

		log.info("#########################################################################################");
		log.info("**** set default uncaught exception handler ****");
		setGlobalUncaughtExceptionHandler();

		log.info("**** start NoobApplication ****");
		SpringApplication.run(NoobApplication.class, args);
	}


	private static void setGlobalUncaughtExceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				log.error("####UnCaughtException", e);
			}
		});
	}
}
