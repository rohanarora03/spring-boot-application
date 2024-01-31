package com.hello.world;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
//        System.out.println("hello");
        SpringApplication.run(Main.class, args);
    }
//        @GetMapping("/")
//        public String greet(){
//            return "Hello";
//    }

//    @GetMapping("/greet")
//    public GreetResponse greet(){
//        return new GreetResponse("Hello"); // returns a JSON response {"greet":"Hello"} on http://localhost:8080/greet
//    }

    // Java objects to JSON objects example
    @GetMapping("/greet")
    public GreetResponse greet(){
        GreetResponse response = new GreetResponse("Hello",
                List.of("Java", "Python", "SQL"),
                new Person("Alex", 28, 30000));
        return response;
    }

    //Records are classes that allow us to achieve immutability. The below record line is equal to having the
    // Class GreetResponse code we've written below it.
//    record GreetResponse(String greet){}

    record Person (String name, int age, double savings){}
    record GreetResponse(String greet,
                         List<String> favProgrammingLang,
                         Person person){}

    // Jackson library is the reason why we're able to get a JSON response through this class
//    class GreetResponse{
//        private final String greet;
//
//        GreetResponse(String greet){
//            this.greet = greet;
//        }
//
//        // Having this getter is relly important inorder for us to get the correct value that we pass into the Constructor
//        // during object creation. Removing this method would result in no JSON response at localhost.
//        public String getGreet() {
//            return greet;
//        }
//
//        @Override
//        public String toString() {
//            return "GreetResponse{" +
//                    "greet='" + greet + '\'' +
//                    '}';
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            GreetResponse that = (GreetResponse) o;
//            return Objects.equals(greet, that.greet);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(greet);
//        }
//    }
}
