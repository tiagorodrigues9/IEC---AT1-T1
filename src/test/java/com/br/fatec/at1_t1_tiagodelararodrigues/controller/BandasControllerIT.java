package com.br.fatec.at1_t1_tiagodelararodrigues.controller;

import com.br.fatec.at1_t1_tiagodelararodrigues.CONTROLLER.BandasController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BandasController.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BandasControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveRetornarTodasAsBandasQuandoGetBandasEndpoint() throws Exception {
        mockMvc.perform(get("/atv/bandas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].nome").value("The Beatles"))
                .andExpect(jsonPath("$[1].nome").value("Pink Floyd"))
                .andExpect(jsonPath("$[2].nome").value("Queen"));
    }

    @Test
    void deveRetornarBandaQuandoGetBandaPorIdEndpointComIdExistente() throws Exception {
        mockMvc.perform(get("/atv/bandas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.nome").value("The Beatles"))
                .andExpect(jsonPath("$.genero").value("Rock"))
                .andExpect(jsonPath("$.anoFormacao").value("1960"));
    }

    @Test
    void deveRetornarVazioQuandoGetBandaPorIdEndpointComIdInexistente() throws Exception {
        mockMvc.perform(get("/atv/bandas/999"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void deveCriarNovaBandaQuandoPostBandasEndpoint() throws Exception {
        String novaBanda = """
                {
                    "nome": "Led Zeppelin",
                    "genero": "Rock",
                    "anoFormacao": "1968"
                }
                """;

        mockMvc.perform(post("/atv/bandas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(novaBanda))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("4"))
                .andExpect(jsonPath("$.nome").value("Led Zeppelin"))
                .andExpect(jsonPath("$.genero").value("Rock"))
                .andExpect(jsonPath("$.anoFormacao").value("1968"));
    }

    @Test
    void deveAtualizarBandaCompletamenteQuandoPutBandasEndpoint() throws Exception {
        String bandaAtualizada = """
                {
                    "nome": "The Beatles Atualizado",
                    "genero": "Pop Rock",
                    "anoFormacao": "1960"
                }
                """;

        mockMvc.perform(put("/atv/bandas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bandaAtualizada))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.nome").value("The Beatles Atualizado"))
                .andExpect(jsonPath("$.genero").value("Pop Rock"));
    }

    @Test
    void deveDeletarBandaQuandoDeleteBandasEndpoint() throws Exception {
        mockMvc.perform(delete("/atv/bandas/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Banda 1 removida"));
    }

}


