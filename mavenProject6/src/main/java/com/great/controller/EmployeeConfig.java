package com.great.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@ComponentScan("com.gl.controller")
public class EmployeeConfig {
	@Autowired
	public ApplicationContext applicationContext ;
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver=new SpringResourceTemplateResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setApplicationContext(applicationContext);
		return resolver;
	}
		@Bean
		public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine=new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver());
		engine.setEnableSpringELCompiler(true);
		return engine;
	}
		@Bean
		public ThymeleafViewResolver thymeleafViewResolver() {
			ThymeleafViewResolver vr=new ThymeleafViewResolver();
			vr.setTemplateEngine(templateEngine());
			return vr;
			
		}
}


