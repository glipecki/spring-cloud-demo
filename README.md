# spring-cloud-demo
Spring Cloud based microservices demo

## Prezentacja
- Podstawowa usługa - wprowadzenie do Spring Boot (_basic-srv-a_)
  - Aplikacja Spring Boot (Spring Boot initializer - wsparcie IntelliJ)
  - Prosty endpoint REST
  - Uruchamianie i deployment (jako samowykonywalny JAR)
- Minimalny DevOps (_basic-srv-b_)
  - Monitorowanie stanu usługi - Actuator (Actuator Docs)
  - Zdalna kontrola usługi - Remote Shell
- API Gateway (_srv-gateway-static_)
  - Zuul - statyczne mapowanie
- Service Discovery (_srv-discovery_)
  - Eureka Server
  - Consul, Zookeeper @ materiały
- Routing (_srv-01, srv-02, srv-03, srv-gateway_)
  - Zuul - dynamiczne mapowanie
  - Ribbon
  - Feign
- Monitorowanie systemu
  - Sleuth
  - Zipkin
- Circut Breaker
  - Hystrix
  - Hystrix Dashboard
  - Turbine
- Scentralizowana konfiguracja
  - Config Server
- Inne
  - spring cluster
  - spring stream
  - cloud bus
  - cloud security
  - cloud oauth2
  - cloud task
  - cloud aws 

## Demo

### basic-srv-a
Najprostsza usługa z wykorzystaniem Spring Boot, gotowa do uruchomienia jako samowykonywalna instancja.

#### Uruchomienie
```bash
glipecki@piec:~/dev/projects/spring-cloud-demo$ bash basic-srv-a.sh 

...

2016-11-24 19:31:15.728  INFO 30105 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 9090 (http)
2016-11-24 19:31:15.730  INFO 30105 --- [           main] net.lipecki.demo.cloud.BasicSrvAApp      : Started BasicSrvAApp in 1.659 seconds (JVM running for 1.891)
```

#### Testowe wywołanie
```bash
glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -sX GET http://127.0.0.1:9090/hello/John | python -m json.tool
{
    "guyToIgnore": "John",
    "message": "Hi!"
}
```

### basic-srv-b
Uruchomienie monitoringu usługi (actuator).

#### Uruchomienie
```bash
glipecki@piec:~/dev/projects/spring-cloud-demo$ bash basic-srv-b.sh 

...

2016-11-24 19:49:09.132  INFO 1793 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/]}" onto public java.lang.String net.lipecki.demo.cloud.demo.DemoRestController.demo()
2016-11-24 19:49:09.324  INFO 1793 --- [           main] o.s.b.a.e.mvc.EndpointHandlerMapping     : Mapped "{[/autoconfig || /autoconfig.json],methods=[GET],produces=[application/json]}" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()
2016-11-24 19:49:09.324  INFO 1793 --- [           main] o.s.b.a.e.mvc.EndpointHandlerMapping     : Mapped "{[/health || /health.json],produces=[application/json]}" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.HealthMvcEndpoint.invoke(java.security.Principal)
2016-11-24 19:49:09.324  INFO 1793 --- [           main] o.s.b.a.e.mvc.EndpointHandlerMapping     : Mapped "{[/beans || /beans.json],methods=[GET],produces=[application/json]}" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()
2016-11-24 19:49:09.325  INFO 1793 --- [           main] o.s.b.a.e.mvc.EndpointHandlerMapping     : Mapped "{[/dump || /dump.json],methods=[GET],produces=[application/json]}" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()
2016-11-24 19:49:09.325  INFO 1793 --- [           main] o.s.b.a.e.mvc.EndpointHandlerMapping     : Mapped "{[/mappings || /mappings.json],methods=[GET],produces=[application/json]}" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()
2016-11-24 19:49:09.325  INFO 1793 --- [           main] o.s.b.a.e.mvc.EndpointHandlerMapping     : Mapped "{[/heapdump || /heapdump.json],methods=[GET],produces=[application/octet-stream]}" onto public void org.springframework.boot.actuate.endpoint.mvc.HeapdumpMvcEndpoint.invoke(boolean,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse) throws java.io.IOException,javax.servlet.ServletException
2016-11-24 19:49:09.326  INFO 1793 --- [           main] o.s.b.a.e.mvc.EndpointHandlerMapping     : Mapped "{[/configprops || /configprops.json],methods=[GET],produces=[application/json]}" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()
2016-11-24 19:49:09.327  INFO 1793 --- [           main] o.s.b.a.e.mvc.EndpointHandlerMapping     : Mapped "{[/env/{name:.*}],methods=[GET],produces=[application/json]}" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EnvironmentMvcEndpoint.value(java.lang.String)
2016-11-24 19:49:09.327  INFO 1793 --- [           main] o.s.b.a.e.mvc.EndpointHandlerMapping     : Mapped "{[/env || /env.json],methods=[GET],produces=[application/json]}" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()
2016-11-24 19:49:09.328  INFO 1793 --- [           main] o.s.b.a.e.mvc.EndpointHandlerMapping     : Mapped "{[/trace || /trace.json],methods=[GET],produces=[application/json]}" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()
2016-11-24 19:49:09.329  INFO 1793 --- [           main] o.s.b.a.e.mvc.EndpointHandlerMapping     : Mapped "{[/info || /info.json],methods=[GET],produces=[application/json]}" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()
2016-11-24 19:49:09.330  INFO 1793 --- [           main] o.s.b.a.e.mvc.EndpointHandlerMapping     : Mapped "{[/metrics/{name:.*}],methods=[GET],produces=[application/json]}" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.MetricsMvcEndpoint.value(java.lang.String)
2016-11-24 19:49:09.330  INFO 1793 --- [           main] o.s.b.a.e.mvc.EndpointHandlerMapping     : Mapped "{[/metrics || /metrics.json],methods=[GET],produces=[application/json]}" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()
2016-11-24 19:49:09.449  INFO 1793 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 9091 (http)
2016-11-24 19:49:09.452  INFO 1793 --- [           main] net.lipecki.demo.cloud.BasicSrvBApp      : Started BasicSrvBApp in 2.184 seconds (JVM running for 2.425)
```

#### Testowe wywołanie

```bash
glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -sX GET http://127.0.0.1:9091/ && echo
glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -sX GET http://127.0.0.1:9091/health | python -m json.tool
glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -sX GET http://127.0.0.1:9091/mappings | python -m json.tool
glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -sX GET http://127.0.0.1:9091/info | python -m json.tool
glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -sX GET http://127.0.0.1:9091/env | python -m json.tool
glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -sX GET http://127.0.0.1:9091/trace | python -m json.tool
glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -sX GET http://127.0.0.1:9091/trace | python -m json.tool
glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -sX GET http://127.0.0.1:9091/metrics | python -m json.tool
```

Dokumentacja: http://localhost:9091/docs/

```bash
glipecki@piec:~/dev/projects/spring-cloud-demo$ ssh -p 2000 user@localhost
Password authentication
Password: 

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.4.1.RELEASE)

> 
```

### Platforma demo
Zestaw wszystkich pozostałych omawianych modułów uruchamiany jako platforma.

#### Uruchomienie
```bash
glipecki@piec:~/dev/projects/spring-cloud-demo$ docker-compose -f infrastructure/docker-compose.yaml up -d
Creating network "infrastructure_default" with the default driver
Creating infrastructure_rabbitmq_1

glipecki@piec:~/dev/projects/spring-cloud-demo$ ./run-all.sh
Building...
...ok

Cleaning logs...

Starting apps...

Staring srv-01 (port=srv-01, instanceId=9101)...
...started with logs in logs/srv-01.1.log and PID: 12946

Staring srv-02 (port=srv-02, instanceId=9102)...
...started with logs in logs/srv-02.1.log and PID: 12963

Staring srv-03 (port=srv-03, instanceId=9103)...
...started with logs in logs/srv-03.1.log and PID: 12983

...

Waiting for ctrl-c

```

#### Testowe wywołania
```bash
glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -X GET http://localhost:9101/ && echo
Srv01

glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -X GET http://localhost:9102/ && echo
Srv02

glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -X GET http://localhost:9103/ && echo
Srv03
```

### srv-gateway-static

Konfiguracja mapowań: zuul.routes.* @ application.yaml

#### Testowe wywołania
```bash
glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -X GET http://localhost:9199/srv01 && echo
Srv01

glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -X GET http://localhost:9199/srv02 && echo
Srv02

glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -X GET http://localhost:9199/srv03 && echo
Srv03
```

### srv-discovery
Serwer service discovery.

Dashboard: http://localhost:9201/

Konfiguracja serwera i klientów:
- eureka.client.serviceUrl.defaultZone @ application.yaml / app-default.properties
- eureka.instance.instanceId @ application.yaml / app-default.properties
- spring.application.name @ bootstrap.yaml
- konfiguracja kontekstu serwera @ net.lipecki.demo.cloud.discovery.SrvDiscoveryApp
- konfiguracja kontekstu klienckiego @ net.lipecki.demo.cloud.utils.CommonServiceApp

### srv-gateway
Gateway API w oparciu o service discovery.

#### Testowe wywołania
```bash
glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -sX GET localhost:9100/routes | python -m json.tool
{
    "/srv01/**": "srv01",
    "/srv02/**": "srv02",
    "/srv03/**": "srv03"
}

glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -sX GET localhost:9100/srv01/ && echo
Srv01

glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -sX GET localhost:9100/srv02/ && echo
Srv02

glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -sX GET localhost:9100/srv03/ && echo
Srv03
```

### Routing w oparciu o service discovery
Kod: net.lipecki.demo.cloud.srv01.SampleRestController.lookForFriends

#### Przykład wywołania
```bash
glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -X GET localhost:9100/srv01/look-for-friends && echo
I'm Srv01 and i see: Srv02
```

### Śledzenie żądań pomiędzy usługami

Sleuth format: ServiceId, TraceId, SpanId
W praktyce:
- ServiceId - nazwa usługi
- TraceId - identyfikator żądania klienta, wspólny dla wszystkich usług
- SpanId - identyfikator pojedynczego żądania na poziomie pojedynczej usługi

Dashboard zipkin: http://localhost:9202/

#### Przykład wywołania
```bash
glipecki@piec:~/dev/projects/spring-cloud-demo$ curl -X GET localhost:9100/srv01/look-for-friends && echo
I'm Srv01 and i see: I'm Srv02 and i see: Srv03

glipecki@piec:~/dev/projects/spring-cloud-demo$ grep "Something called me" logs/*
logs/srv-01.1.log:2016-11-24 21:55:36.286  INFO [srv01,bbdae202d194a402,8ed6f7e32cc4895d,false] 5994 --- [nio-9101-exec-1] n.l.d.cloud.srv01.SampleRestController   : Something called me :)
logs/srv-02.1.log:2016-11-24 21:55:36.735  INFO [srv02,bbdae202d194a402,3e256a67a521e7b1,false] 6014 --- [nio-9102-exec-1] n.l.d.cloud.srv02.SampleRestController   : Something called me :)
logs/srv-03.1.log:2016-11-24 21:55:37.175  INFO [srv03,bbdae202d194a402,8388362763f605c1,false] 6035 --- [nio-9103-exec-1] n.l.d.cloud.srv03.SampleRestController   : Something called me :)
```

### Circut breaker

Adres Hystrix dashboard (z turbine): http://localhost:9204/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A9203%2F&title=Cloud%20demo

### Scentralizowana konfiguracja

_TODO_

## FAQ
Adres konsoli RabbitMQ po uruchomieniu przez docker compose: http://localhost:15672/
Testy ruchu do usług: $ ab -n 10000 http://localhost:9100/srv01/look-for-friends

## Materiały
- http://cloud.spring.io/spring-cloud-netflix/spring-cloud-netflix.html
- https://cloud.spring.io/spring-cloud-consul/
- https://cloud.spring.io/spring-cloud-zookeeper/
- https://github.com/spring-cloud/spring-cloud-sleuth
- http://ryanjbaxter.com/cloud/spring%20cloud/spring/2016/07/07/spring-cloud-sleuth.html