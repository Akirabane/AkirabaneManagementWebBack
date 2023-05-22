package fr.akirabane.AkirabaneManagementWebBack.compute.controller;

import fr.akirabane.AkirabaneManagementWebBack.compute.dto.in.PlayersDtoIn;
import fr.akirabane.AkirabaneManagementWebBack.compute.dto.out.PlayersDtoOut;
import fr.akirabane.AkirabaneManagementWebBack.compute.service.PlayersService;
import fr.akirabane.AkirabaneManagementWebBack.compute.entity.PlayerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Players {

    @Autowired
    PlayersService playersService;

    @PostMapping("/apiv1/player/add")
    public String addPlayer(@RequestBody PlayersDtoIn player) {
        if(player.getPassword_player() == null) {
            throw new IllegalArgumentException("password_player cannot be null");
        }
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
    public List<PlayersDtoOut> getAllPlayers() {
        return playersService.getAllPlayers();
    }

/** Test de pages pour spring security **/
    @GetMapping("/privatePage")
    public String privatePage(){
        return "welcome to private page Mr Akirabane ";
    }


    @GetMapping("/public")
    public String publicpage(){
        return "welcome to public  page Mr Akirabane ";
    }

}
