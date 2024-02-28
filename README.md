**Recipe Service**

A simplified recipe service where you can add ingredients, recipes and dishes. A recipe for a dish can be created from ingredients by specifying the complexity of the recipe and the time for its preparation in minutes.

![Conceptual_scheme]([https://gitlab.fit.cvut.cz/hryntmak/bi-tjv_semestral_work/-/raw/master/image_2023-10-26_19-07-30.png](https://github.com/hryntmak/Recipe-Manager/blob/master/image_2023-10-26_19-07-30.png?raw=true))

> _**Coplex querimes:**_
> 
> - Write recipes cheaper than the entered price.


> _**Additional functionality:**_
>
> When adding ingredients to a recipe, if the final number of ingredients is more than 7, complexity will be change to MEDIUM if it was originally specified LOW, and with 12 or more ingredients to HIGH if it was originally specified as LOW or MEDIUM.


# Příprava a spuštění
## Instalace

**Aplikace se skládá ze 2 částí:**

- API: https://github.com/hryntmak/Recipe-Manager-api
- Client: https://github.com/hryntmak/Recipe-Manager-client

### Požadavky 
- JVM
- Gradle

## Spuštění aplikace
### API
V adresáři `Recipe-Manager`
```
./gradlew bootRun
```
### Client
V adřesáři `Recipe-Manager-client`
```
./gradlew bootRun
```
Aplikace bude dostupná na adrese: http://localhost:9091



