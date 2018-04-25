
# Microservices with Spring Cloud and Netflix

This repository is an example of how to establish communication between microservices, enable load balancing, scaling up and down of microservices. You will also learn to centralize configuration of microservices with Spring Cloud Config Server. You will implement Eureka Naming Server and fault toleranct with Hystrix.


![calculated-amount 1 1](https://user-images.githubusercontent.com/33202856/39271731-741d849c-48f7-11e8-8431-02c57a4ff0ad.jpg)


## Application Architecture

![screenshot from 2018-04-26 00-58-16 1](https://user-images.githubusercontent.com/33202856/39271508-e8e6f28c-48f6-11e8-95df-e50fc6b94728.jpg)

![dia2 1](https://user-images.githubusercontent.com/33202856/39271560-10da77f0-48f7-11e8-94f6-061c202e4f48.jpg)


## Ports


| Application                      |          Port             |
| -------------------------------- | ------------------------- |
| Spring Cloud Config Server       |     8888                  |
| Cryptocurrency Exchange Service  |     8000, 8001, 8002, ..  |
| Netflix Eureka Naming Server     |     8761                  |
| Netflix Zuul API Gateway Server  |     8765                  |



## URLs

| Application                             |                     URL
| --------------------------------------- | -------------------------------------------------------------------------------------------------------------------     | 
| Cryptocurrency Conversion Service       |  http://localhost:8100/cryptocurrency-converter/name/Ripple/convertTo/usd/quantity/10                                   |
| Cryptocurrency Exchange Service         |  http://localhost:8000/cryptocurrency-exchange/name/bitcoin/convertTo/usd                                               |
| Eureka                                  |  http://localhost:8761/                                                                                                 |
| Zuul-Cryptocurrency Exchange Service    |  http://localhost:8765/cryptocurrency-exchange-service/cryptocurrency-exchange/name/bitcoin/convertTo/usd               |
| Zuul-Cryptocurrency Conversion Services |  http://localhost:8765/cryptocurrency-conversion-service/cryptocurrency-converter/name/Ripple/convertTo/usd/quantity/12 |   
                                            
                                            






## Microservices

The main objective of the micro-services implementation is to split up the application as separate service for each core and API service functionality and it should be deployed independently on cloud. Spring Cloud integrates the Netflix components in the spring environment in a very nice way using auto configuration and convention over configuration similar to how Spring Boot works



## Why Micro Services Architecture?

We chose micro services architecture to write each functionality as a separate service for core and API functionality and it helps us to achieve the continuous delivery and integration.




## Spring Cloud 

Spring Cloud provides tools for developers to quickly build some of the common patterns in distributed systems (e.g. configuration management, service discovery, circuit breakers, intelligent routing, micro-proxy, control bus, one-time tokens, global locks, leadership election, distributed sessions, cluster state)

You can read in detail about Spring Cloud here - http://projects.spring.io/spring-cloud/





## Spring Cloud Config

Spring Cloud config provides support for externalizing configuration in distributed systems. With the Config Server you have a central place to manage external properties for applications across all environments.

You can read in detail about Spring Cloud config here - http://cloud.spring.io/spring-cloud-config/




## Spring Cloud Netflix Overview

Spring Cloud Netflix provides Netflix OSS integrations for Spring Boot apps through autoconfiguration and binding to the Spring Environment and other Spring programming model idioms.

You can read in detail about Spring Cloud Netflix here - http://cloud.spring.io/spring-cloud-netflix/


## Eureka

Microservices is somewhat like SOA platform, that there are numerous services. Each Service when it comes online registers itself with Service Registry. When some other service wants to communicate with a already registered service, they would ask the Eureka Server the base url for that service. Multiple instances of the same service could register with Eureka, in that case Eureka could help in doing Load Balancing.


![eureka 1](https://user-images.githubusercontent.com/33202856/39271641-38ccf5b2-48f7-11e8-87c4-7905d664fd21.jpg)


## Feign

Feign is a declarative HTTP client, which seamlessly integrates with Ribbon and Hystrix. Actually, with one spring-cloud-starter-feign dependency and @EnableFeignClients annotation you have a full suite of a load balancer, circuit breaker, and HTTP client with a sensible ready-to-go default configuration.


## Ribbon

Netflix Ribbon can be used by service consumers to look up services at runtime. Ribbon uses the information available in Eureka to locate appropriate service instances. If more than one instance is found, Ribbon will apply load balancing to spread the requests over the available instances. Ribbon does not run as a separate service but instead as an embedded component in each service consumer.


## Hystrix

Hystrix is the implementation of a Circuit Breaker pattern, which gives a control over latency and failure from dependencies accessed over the network. The main idea is to stop cascading failures in a distributed environment with a large number of microservices. That helps to fail fast and recover as soon as possible — important aspects of fault-tolerant systems that self-heal.

Besides circuit breaker control, with Hystrix you can add a fallback method that will be called to obtain a default value in case the main command fails.

Moreover, Hystrix generates metrics on execution outcomes and latency for each command, that we can use to monitor system behavior.


![screenshot f2rom 2018-04-26 00-07-29 1](https://user-images.githubusercontent.com/33202856/39271680-549d1fba-48f7-11e8-8f68-e4bf01262903.jpg)


## Netflix Zuul – Edge Server

Zuul is our gatekeeper to the outside world, not allowing any unauthorized external requests pass through. Zulu also provides a well-known entry point to the micro services in the system landscape. Using dynamically allocated ports is convenient to avoid port conflicts and to minimize administration but it makes it of course harder for any given service consumer. Zuul uses Ribbon to look up available services and routes the external request to an appropriate service instance.


