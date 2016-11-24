package net.lipecki.demo.cloud.utils;

import com.google.common.base.CaseFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Properties;

public class CommonServiceAppBuilder {

	public static final String APP_SUFFIX = "-app";

	private final Class<?> appClass;

	private String name;

	public CommonServiceAppBuilder(final Class<?> appClass) {
		this.appClass = appClass;

		final String classSimpleName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, appClass.getSimpleName());
		if (classSimpleName.endsWith(APP_SUFFIX)) {
			this.name = classSimpleName.substring(0, classSimpleName.length() - APP_SUFFIX.length());
		} else {
			this.name = classSimpleName;
		}
	}

	/**
	 * Sets service custom name.
	 * <p>
	 *     By default service name is build based on main app class simple name converted to lower hyphen and stripped from "app" suffix.
	 * </p>
	 * @param name
	 * @return
	 */
	public CommonServiceAppBuilder name(final String name) {
		this.name = name;
		return this;
	}

	public ConfigurableApplicationContext run(String... args) {
		final SpringApplication app = new SpringApplication(appClass);
		app.setDefaultProperties(getDefaultProperties());
		return app.run(args);
	}

	private Properties getDefaultProperties() {
		try {
			final Properties defaultProperties = new Properties();
			defaultProperties.load(CommonServiceAppBuilder.class.getResourceAsStream("/app-default.properties"));
			defaultProperties.put("spring.application.name", this.name);
			return defaultProperties;
		} catch (IOException e) {
			throw new RuntimeException("Can't load default app properties!", e);
		}
	}

}
