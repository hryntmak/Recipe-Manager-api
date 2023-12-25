package cz.cvut.fit.tjv.recipe_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipeApi {
    public static void main(String[] args) {
        SpringApplication.run(RecipeApi.class, args);
//        ApplicationContext springContainer = new AnnotationConfigApplicationContext("cz.cvut.fit.tjv.sn_api");
//
//
////        UserRepository ur = springContainer.getBean(UserRepository.class);
//
//        UserService us = springContainer.getBean(UserService.class);
//
//        us.readAll();
    }
}
