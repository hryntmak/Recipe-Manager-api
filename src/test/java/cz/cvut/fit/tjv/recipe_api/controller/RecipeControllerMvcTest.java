package cz.cvut.fit.tjv.recipe_api.controller;

import cz.cvut.fit.tjv.recipe_api.domain.Recipe;
import cz.cvut.fit.tjv.recipe_api.service.RecipeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(RecipeController.class)
public class RecipeControllerMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RecipeService recipeService;

    @Test
    void createSuccess() throws Exception {
        Recipe testRecipeIn = new Recipe();
        testRecipeIn.setName("love");
        var testRecipeOut = new Recipe();
        testRecipeOut.setName(testRecipeIn.getName());
        testRecipeOut.setId(211L);
        Mockito.when(recipeService.create(testRecipeIn)).thenReturn(testRecipeOut);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"name\" : \"love\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(211)));
    }

    @Test
    void createFail() throws Exception {
        Recipe testRecipeIn = new Recipe();
        testRecipeIn.setId(122L);
        testRecipeIn.setName("piece");
        Mockito.when(recipeService.create(testRecipeIn)).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"id\" : 122,\n" +
                                "  \"name\" : \"piece\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }
}
