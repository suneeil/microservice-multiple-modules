package com.microservice.apigateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;
import java.util.function.Predicate;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

        return builder
                .routes()
                .route( p -> p.path("/get")//if any request comes to http://localhost:8765/get
                        .filters(f -> f   //add headers and param
                                .addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80")) //redirect here
                .route(p -> p.path("/currency-exchange/**") //if any request to is made to /currency-exchange/..
                        .uri("lb://currency-exchange")) // '//currency-exchange' is the registration name on eureka server >> 'lb' is load balance>> redirect
                .route(p -> p.path("/currency-conversion/**") //if any request to is made to /currency-conversion/..
                        .uri("lb://currency-conversion")) //'lb' >> load balance
                .route(p -> p.path("/currency-conversion-feign/**") //if any request to is made to /currency-conversion-feign/..
                        .uri("lb://currency-conversion"))//'lb' >> load balance
                .route(p -> p.path("/currency-conversion-new/**") //if any request to is made to /currency-conversion-new
                        .filters(f -> f
                                .rewritePath("/currency-conversion-new/(?<segment>.*)",
                                        "/currency-conversion-feign/${segment}")) //redirect to currency-conversion-feign
                        .uri("lb://currency-conversion"))//'lb' >> load balance
                .build();
// what ever follows /currency-conversion-new/(?<segment>.*) >>> need to append it to currency-conversion-feign/
    }
}
