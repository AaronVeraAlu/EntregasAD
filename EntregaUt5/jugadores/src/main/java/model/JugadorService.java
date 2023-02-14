package model;

import java.util.List;
import java.util.Optional;

public interface JugadorService {
    List<Jugador> findAll();
    Optional<Jugador> findById(Long id);

    void saveJugador(Jugador jugador);
    Jugador updateJugador(Long id, Jugador jugador);

    void deleteJugador(Long id);

}
