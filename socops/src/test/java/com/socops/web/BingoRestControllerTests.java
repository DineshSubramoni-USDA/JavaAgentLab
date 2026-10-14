package com.socops.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BingoRestController.class)
class BingoRestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Game setup supplies four complete player boards")
    void gameSetupSuppliesFourPlayerBoards() throws Exception {
        mockMvc.perform(get("/api/bingo/setup"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].length()").value(25))
                .andExpect(jsonPath("$[1].length()").value(25))
                .andExpect(jsonPath("$[2].length()").value(25))
                .andExpect(jsonPath("$[3].length()").value(25))
                .andExpect(jsonPath("$[0][12].freeCell").value(true))
                .andExpect(jsonPath("$[0][12].selected").value(true));
    }
}
