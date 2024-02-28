**Recipe Service**

A simplified recipe service where you can add ingredients, recipes and dishes. A recipe for a dish can be created from ingredients by specifying the complexity of the recipe and the time for its preparation in minutes.

![Conceptual_scheme](https://github.com/hryntmak/Recipe-Manager/blob/master/image_2023-10-26_19-07-30.png?raw=true)

> _**Coplex querimes:**_
> 
> - Write recipes cheaper than the entered price.


> _**Additional functionality:**_
>
> When adding ingredients to a recipe, if the final number of ingredients is more than 7, complexity will be change to MEDIUM if it was originally specified LOW, and with 12 or more ingredients to HIGH if it was originally specified as LOW or MEDIUM.


# Preparation and launch
## Installation

**The application consists of 2 parts:**

- API: https://github.com/hryntmak/Recipe-Manager-api
- Client: https://github.com/hryntmak/Recipe-Manager-client

### Requirements 
- JVM
- Gradle

## Launch the app
### API
In the directory `Recipe-Manager-api`
```
./gradlew bootRun
```
### Client
In the directory `Recipe-Manager-client`
```
./gradlew bootRun
```
The application will be available at: http://localhost:9091



