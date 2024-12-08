package com.app.playerservicejava.controller;

import com.app.playerservicejava.Exceptions.BadRequestException;
import com.app.playerservicejava.Exceptions.DAOException;
import com.app.playerservicejava.model.Player;
import com.app.playerservicejava.model.PlayerCreateDTO;
import com.app.playerservicejava.model.Players;
import com.app.playerservicejava.service.PlayerService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "v1/players", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PlayerController extends BaseResource   {
    @Autowired
    private PlayerService playerService;

    @RequestMapping(method = RequestMethod.GET)
    public Response getPlayers() {
        Players players = playerService.getPlayers();
        return ok(players);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") String id) throws DAOException {
        Optional<Player> player = playerService.getPlayerById(id);

        if (player.isPresent()) {
            return new ResponseEntity<>(player.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tester/{id}")
    public Response getPlayerTest(@QueryParam("value") String value, @NotNull @PathVariable("id") String id, @RequestBody PlayerCreateDTO playerCreateDTO){
        try {
            if(value==null){
                throw new BadRequestException("");
            }
            Optional<Player> player = playerService.getPlayerById(id);
            return ok(player);
        } catch (BadRequestException e){
            return badRequest(e);
        }catch (DAOException e){
            return serverError("Sever_ERRR",e.getMessage());
        }
        catch (Exception e){
            throw new RuntimeException("");
        }
    }
}
