package com.br.fatec.at1_t1_tiagodelararodrigues.controller;

import com.br.fatec.at1_t1_tiagodelararodrigues.CONTROLLER.BandasController;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class BandasControllerTest {

    private final BandasController controller = new BandasController();

    // 1. Teste para listar todas as bandas
    @Test
    void deveRetornarTodasAsBandas() {
        List<Map<String, String>> bandas = controller.getBandas();
        assertEquals(3, bandas.size());
        assertEquals("The Beatles", bandas.get(0).get("nome"));
    }

    // 2. Teste para buscar banda por ID existente
    @Test
    void deveRetornarBandaQuandoIdExiste() {
        Map<String, String> banda = controller.getBandaById(1);
        assertNotNull(banda);
        assertEquals("1", banda.get("id"));
        assertEquals("The Beatles", banda.get("nome"));
        assertEquals("Rock", banda.get("genero"));
    }

    // 3. Teste para buscar banda por ID inexistente
    @Test
    void deveRetornarNullQuandoIdNaoExiste() {
        Map<String, String> banda = controller.getBandaById(999);
        assertEquals(null, banda);
    }

    // 4. Teste para criar nova banda
    @Test
    void deveCriarNovaBandaComIdGerado() {
        Map<String, String> novaBanda = Map.of(
            "nome", "Led Zeppelin",
            "genero", "Rock",
            "anoFormacao", "1968"
        );
        Map<String, String> resultado = controller.createBanda(novaBanda);

        assertEquals("Led Zeppelin", resultado.get("nome"));
        assertEquals("Rock", resultado.get("genero"));
        assertEquals("1968", resultado.get("anoFormacao"));
        assertEquals("4", resultado.get("id")); // Próximo ID
    }

    // 5. Teste para atualizar banda existente
    @Test
    void deveAtualizarBandaExistenteCompletamente() {
        Map<String, String> dadosAtualizacao = Map.of(
            "nome", "The Beatles Atualizado",
            "genero", "Pop Rock",
            "anoFormacao", "1960"
        );
        Map<String, String> resultado = controller.updateBanda(1, dadosAtualizacao);

        assertEquals("The Beatles Atualizado", resultado.get("nome"));
        assertEquals("Pop Rock", resultado.get("genero"));
    }

    // 6. Teste para deletar banda
    @Test
    void deveDeletarBandaERetornarMensagemConfirmacao() {
        String resultado = controller.deleteBanda(1);
        assertEquals("Banda 1 removida", resultado);
    }

}

