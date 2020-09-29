package com.aybarsacar.expensetrackerapi;

import com.aybarsacar.expensetrackerapi.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class ExpenseTrackerApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExpenseTrackerApiApplication.class, args);
  }

  //  handling CORS
//  @Bean
//  public FilterRegistrationBean<CorsFilter> corsFilter() {
//
//    FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
//
////    configure
//    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//    CorsConfiguration config = new CorsConfiguration();
//
//    config.addAllowedOrigin("*");
//    config.addAllowedHeader("*");
//    source.registerCorsConfiguration("/**", config);
//
//    registrationBean.setFilter(new org.springframework.web.filter.CorsFilter(source));
//
//
//    registrationBean.setOrder(0);
//
//    return registrationBean;
//  }
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
      }
    };
  }

  @Bean
  public FilterRegistrationBean<AuthFilter> filterFilterRegistrationBean() {

    FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
    AuthFilter authFilter = new AuthFilter();

//    set the registration bean
    registrationBean.setFilter(authFilter);

//    apply it to all the category routes
    registrationBean.addUrlPatterns("/api/categories/*");

    return registrationBean;


  }

}
