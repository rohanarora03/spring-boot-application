package com.hello.world;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    public static void main(String[] args) {
//        System.out.println("hello");
        SpringApplication.run(Main.class, args);
    }

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @GetMapping
    public List<Customer> getCustomers(){
//        return List.of(); // Empty List
        return customerRepository.findAll();
    }

    record NewCustomerRequest(String name,
                              String email,
                              Integer age){}

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setAge(request.age());
        customer.setEmail(request.email());
        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id,
                               @RequestBody NewCustomerRequest request){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()) {
            if(request.age()!=null)     customer.get().setAge(request.age());
            if(request.email()!=null)   customer.get().setEmail(request.email());
            if(request.name()!=null)    customer.get().setName(request.name());
            if(request!=null)           customerRepository.save(customer.get());
        }
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
    /* Update: Commented example: 31-Jan-2024
    @GetMapping("/greet")
    public GreetResponse greet(){
        GreetResponse response = new GreetResponse("Hello",
                List.of("Java", "Python", "SQL"),
                new Person("Alex", 28, 30000));
        return response;
    }
     */

    //Records are classes that allow us to achieve immutability. The below record line is equal to having the
    // Class GreetResponse code we've written below it.
//    record GreetResponse(String greet){}

    /* Update: Commented example: 31-Jan-2024
    record Person (String name, int age, double savings){}
    record GreetResponse(String greet,
                         List<String> favProgrammingLang,
                         Person person){}

     */

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
