# logbook-mdc

A minimal project showing how Logbook honors SLF4J's MDC, in response
to [issue 1625](https://github.com/zalando/logbook/issues/1625).

## How to run

In a terminal, run the following command:

```bash
./mvnw clean spring-boot:run
```

In a separate terminal, run the following command:

```bash
curl localhost:8080/hello
```

Notice the log output in the first terminal:

```
2023-09-20 17:38:32.540 [http-nio-8080-exec-1] TRACE org.zalando.logbook.Logbook - [] {"origin":"remote","type":"request","correlation":"f8c75cfcb28b297e","protocol":"HTTP/1.1","remote":"127.0.0.1","method":"GET","uri":"http://localhost:8080/hello","host":"localhost","path":"/hello","scheme":"http","port":"8080","headers":{"accept":["*/*"],"host":["localhost:8080"],"user-agent":["curl/8.0.1"]}}
2023-09-20 17:38:32.553 [http-nio-8080-exec-1] INFO  io.msdousti.mdc.HelloController - [traceId=8634834348347834661212] Hello from Spring Boot!
2023-09-20 17:38:32.570 [http-nio-8080-exec-1] TRACE org.zalando.logbook.Logbook - [traceId=8634834348347834661212] {"origin":"local","type":"response","correlation":"f8c75cfcb28b297e","duration":64,"protocol":"HTTP/1.1","status":200,"headers":{"Content-Length":["5"],"Content-Type":["text/plain;charset=UTF-8"],"Date":["Wed, 20 Sep 2023 15:38:32 GMT"]},"body":"Hello"}
```

* The first line is from Logbook, logging the request. At that time, MDC was empty.
* The second line is from the HelloController. The HelloController populates MDC with the traceId
  by `MDC.put("traceId", "8634834348347834661212")`;
* The third line is from Logbook, logging the response. At that time, MDC had the traceId, and it is logged as expected.


