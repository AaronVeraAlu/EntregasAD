package controller;

import lombok.Data;
import model.Jugador;
import model.JugadorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebController {
    @Autowired
    private JugadorServiceImpl jsi;

    @PostMapping("/alta")
    public void saveJugador(Jugador jugador){jsi.saveJugador(jugador);}
    @GetMapping("/list")
    public List<Jugador> findJugador(){
        return jsi.findAll();
    }
    @PutMapping("/jugadores/{id}")
    public void updateJugador(Jugador jugador,@PathVariable Long id){
        jsi.updateJugador(id, jugador);
    }
    @DeleteMapping("/deleteJ/{id}")
    public void deleteJugador(@PathVariable Long id){
        jsi.deleteJugador(id);
    }
}
