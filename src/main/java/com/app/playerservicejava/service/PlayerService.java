package com.app.playerservicejava.service;

import com.app.playerservicejava.Exceptions.DAOException;
import com.app.playerservicejava.model.Player;
import com.app.playerservicejava.model.Players;
import com.app.playerservicejava.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PlayerService {
    private Logger logger=LoggerFactory.getLogger(PlayerService.class);
    @Autowired
    private PlayerRepository playerRepository;

    public Players getPlayers() {
        Players players = new Players();
        playerRepository.findAll()
                .forEach(players.getPlayers()::add);
        return players;
    }

    public Optional<Player> getPlayerById(String playerId) throws DAOException {
        Optional<Player> player = null;

        /* simulated network delay */
        try {
            player = playerRepository.findById(playerId);
            timeDelay();
        } catch (Exception e) {
            logger.error("message=Exception in getPlayerById; exception={}", e.toString());
            return Optional.empty();
        }
        return player;
    }
    @Async
    public void timeDelay(){
        try {
            Thread.sleep((long) (Math.random() * 2000));
        }
        catch (Exception e){
            throw new RuntimeException("exception this");
        }
    }

}
