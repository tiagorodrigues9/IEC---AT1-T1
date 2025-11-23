package com.br.fatec.at1_t1_tiagodelararodrigues.controller;

import com.br.fatec.at1_t1_tiagodelararodrigues.CONTROLLER.HelloController;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class HelloControllerTest {

    private final HelloController controller = new HelloController();

    @Test
    void deveRetornarMensagemHello() {
        // Testamos diretamente o retorno do método `hello()`
        String resultado = controller.hello();
        assertEquals("Tiago de Lara Rodrigues", resultado);
    }

}