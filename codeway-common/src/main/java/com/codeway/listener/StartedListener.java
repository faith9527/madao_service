package com.codeway.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * The method executed after the application is started.
 *
 * @author GuoGuang
 */
@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StartedListener implements ApplicationListener<ApplicationStartedEvent> {

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		try {
			this.printStartInfo();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private void printStartInfo() throws UnknownHostException {

		Environment env = applicationContext.getEnvironment();
		String ip = InetAddress.getLocalHost().getHostAddress();
		String port = env.getProperty("server.port");
		log.info(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE, "Application started at         ", "http://" + ip + ":" + port));
		log.info(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE, "Application api doc was enabled at  ", "http://" + ip + ":" + port, "/swagger-ui.html"));
		log.info(AnsiOutput.toString(AnsiColor.BRIGHT_YELLOW, "Application has started successfully!"));
	}

}
