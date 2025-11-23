package com.br.fatec.at1_t1_tiagodelararodrigues.CONTROLLER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atv/bandas")
public class BandasController {

    private final Map<Integer, Map<String, String>> bandasDB = new HashMap<>();
    private int nextId = 1;

    public BandasController() {
        bandasDB.put(1, Map.of(
            "id", "1",
            "nome", "The Beatles",
            "genero", "Rock",
            "anoFormacao", "1960"
        ));
        bandasDB.put(2, Map.of(
            "id", "2",
            "nome", "Pink Floyd",
            "genero", "Progressive Rock",
            "anoFormacao", "1965"
        ));
        bandasDB.put(3, Map.of(
            "id", "3",
            "nome", "Queen",
            "genero", "Rock",
            "anoFormacao", "1970"
        ));
        nextId = 4;
    }

    @GetMapping
    public List<Map<String, String>> getBandas() {
        return new ArrayList<>(bandasDB.values());
    }


    @GetMapping("/{id}")
    public Map<String, String> getBandaById(@PathVariable int id) {
        return bandasDB.get(id);
    }

    @PostMapping
    public Map<String, String> createBanda(@RequestBody Map<String, String> data) {
        Map<String, String> novaBanda = new HashMap<>();
        novaBanda.put("id", String.valueOf(nextId));
        novaBanda.put("nome", data.get("nome"));
        novaBanda.put("genero", data.get("genero"));
        novaBanda.put("anoFormacao", data.get("anoFormacao"));

        bandasDB.put(nextId, novaBanda);
        nextId++;

        return novaBanda;
    }


    @PutMapping("/{id}")
    public Map<String, String> updateBanda(@PathVariable int id, @RequestBody Map<String, String> data) {
        Map<String, String> bandaAtualizada = new HashMap<>();
        bandaAtualizada.put("id", String.valueOf(id));
        bandaAtualizada.put("nome", data.get("nome"));
        bandaAtualizada.put("genero", data.get("genero"));
        bandaAtualizada.put("anoFormacao", data.get("anoFormacao"));

        bandasDB.put(id, bandaAtualizada);
        return bandaAtualizada;
    }


    @DeleteMapping("/{id}")
    public String deleteBanda(@PathVariable int id) {
        bandasDB.remove(id);
        return "Banda " + id + " removida";
    }

}

