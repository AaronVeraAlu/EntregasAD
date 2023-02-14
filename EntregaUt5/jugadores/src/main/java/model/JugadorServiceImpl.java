package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class JugadorServiceImpl implements JugadorService{
    @Autowired
    private JugadorRepository jr;

    public List<Jugador> findAll() {
        return (List<Jugador>)jr.findAll() ;
    }

    public Optional<Jugador> findById(Long id) {
        return jr.findById(id);
    }

    public void saveJugador(Jugador jugador) {
        jr.save(jugador);
    }


    public Jugador updateJugador(Long id, Jugador jugador) {
        if(jr.findById(id).isPresent()){
            jr.save(jugador);
        }
        return null;
    }

    public void deleteJugador(Long id) {
        jr.deleteById(id);
    }
}
