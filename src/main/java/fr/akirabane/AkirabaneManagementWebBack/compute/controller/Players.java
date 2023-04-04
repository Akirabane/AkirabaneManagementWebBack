package fr.akirabane.AkirabaneManagementWebBack.compute.controller;

import fr.akirabane.AkirabaneManagementWebBack.compute.dto.in.PlayersDtoIn;
import fr.akirabane.AkirabaneManagementWebBack.compute.service.PlayersService;
import fr.akirabane.AkirabaneManagementWebBack.compute.entity.PlayerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Players {

    @Autowired
    PlayersService playersService;

    @GetMapping("/apiv1/player/{pseudo}")
    public PlayerEntity getPlayer(@PathVariable String pseudo) {
        try {
            return playersService.getPlayerName(pseudo);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/apiv1/player/add")
    public String addPlayer(@RequestBody PlayersDtoIn player) {
     playersService.addPlayer(player);
        //return data added
        return "Joueur ajouté : " + player.getPseudo_player() + " " + player.getUuid_player() + " " + player.getPassword_player() + " " + player.getStaff();
    }

    @DeleteMapping("/apiv1/player/delete/{id}")
    public String deletePlayer(@PathVariable Integer id) {
        playersService.deletePlayer(id);
        return "Joueur supprimé: " + id;
    }

    @PutMapping("/apiv1/player/update/{id}")
    public String updatePlayer(@PathVariable Integer id, @RequestBody PlayersDtoIn player) {
        playersService.updatePlayer(player, id);
        return "Joueur modifié: " + id + " " + player.getPseudo_player() + " " + player.getUuid_player() +  " " + player.getPassword_player() + " " + player.getStaff();
    }

    //get all players from repository
    @GetMapping("/apiv1/players")
    public Iterable<PlayerEntity> getAllPlayers() {
        return playersService.getAllPlayers();
    }


    @GetMapping("/privatePage")
    public String privatePage(){
        return "welcom to  private page Mr Akirabane ";
    }


    @GetMapping("/public")
    public String publicpage(){
        return "welcom to  public  page Mr Akirabane ";
    }

}