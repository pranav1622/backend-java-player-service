package com.app.playerservicejava;

import com.app.playerservicejava.model.Player;
import com.app.playerservicejava.model.PlayerResponseDTO;
import com.app.playerservicejava.repository.PlayerRepository;
import com.app.playerservicejava.service.PlayerServiceV2_0;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class PlayerService_2_0Test {
    @InjectMocks
    private PlayerServiceV2_0 playerServiceV20;
    @Mock
    private PlayerRepository playerRepository;

    @Test
    public void testGetPlayerByIdWithCache(){
        Player mockPlayer = new Player();
        Optional<Player> opt = Optional.of(mockPlayer);
        Mockito.when(playerRepository.findById(Mockito.anyString())).thenReturn(opt);
        PlayerResponseDTO playerResponseDTO=playerServiceV20.getPlayerByIdWithCache("abc");
        System.out.println(playerResponseDTO.getFirstName());

    }
}
