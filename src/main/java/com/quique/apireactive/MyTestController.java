package com.quique.apireactive;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class MyTestController {

    @GetMapping("/demo")
    public Mono<String> greetingMessage() {
        return computeMessage()
                .zipWith(getNameFromDB())
                .map(value -> {
                    return value.getT1() + value.getT2();
                });
        //return computeMessage();
    }

    private Mono<String> computeMessage() {
        return Mono.just("Hello ").delayElement(Duration.ofSeconds(5));
    }

    private Mono<String> getNameFromDB() {
        return Mono.just("Quique").delayElement(Duration.ofSeconds(5));
    }

    /////////////////////////// observable ///////////////////////////

//    @Autowired
//    private RestTemplate restTemplate;

    @GetMapping("/greet")
    public String greet() {
        Observable<String> nameObservable = Observable.fromCallable(this::getNameFromMS)
                .subscribeOn(Schedulers.newThread());
        Observable<String> greetingObservable = Observable.fromCallable(this::getGreetingFromMs)
                .subscribeOn(Schedulers.newThread());
        String response = Observable.zip(nameObservable, greetingObservable, this::merge).blockingFirst();
        return response;
    }

    private String merge(String nameObservable, String greetingObservable) {
        return "Hello " + nameObservable + ", " + greetingObservable;
    }

    private String getNameFromMS() {
        return "ncnr ";
        //return restTemplate.getForEntity("http://localhost:5050/getName", String.class).getBody();

    }

    private String getGreetingFromMs() {
        return "Good day ";
        //return restTemplate.getForEntity("http://localhost:6060/getGreeting", String.class).getBody();
    }
}
