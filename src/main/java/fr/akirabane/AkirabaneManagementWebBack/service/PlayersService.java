package fr.akirabane.AkirabaneManagementWebBack.service;

import fr.akirabane.AkirabaneManagementWebBack.dao.IPlayerDao;
import fr.akirabane.AkirabaneManagementWebBack.dto.in.PlayersDtoIn;
import fr.akirabane.AkirabaneManagementWebBack.entity.PlayerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayersService {

    @Autowired
    IPlayerDao iplayerDao;

    public PlayersService() {
    }

    public PlayerEntity getPlayerName(String pseudo) throws RuntimeException{
        var player = iplayerDao.findById(pseudo);
        if (player.isPresent()) {
            return player.get();
        } else {
            throw new RuntimeException("Player not found");
        }
    }

    //add player to repository
    public void addPlayer(PlayersDtoIn player) throws RuntimeException {
        var playerEntity = new PlayerEntity();
        //check if player already exist
        var player1 = iplayerDao.findByUuid_player(player.getUuid_player());
        if (player1.isPresent()) {
            throw new RuntimeException("Player already exist");
        } else {
            playerEntity.setUuid_player(player.getUuid_player());
            playerEntity.setPseudo_player(player.getPseudo_player());
            if (player.getStaff() == 0) {
                playerEntity.setStaff(false);
            } else if (player.getStaff() == 1) {
                playerEntity.setStaff(true);
            }
            iplayerDao.save(playerEntity);
        }
    }

    //delete player from repository
    public void deletePlayer(Integer id) {
        iplayerDao.deleteById(id);
    }

    //update player from repository
    public void updatePlayer(PlayersDtoIn player, Integer id) throws RuntimeException {
        var playerEntity = iplayerDao.findById(id);
        if (!playerEntity.isPresent()) {
            throw new RuntimeException("Player not found");
        }
        var player1 = playerEntity.get();
        player1.setUuid_player(player.getUuid_player());
        player1.setPseudo_player(player.getPseudo_player());
        if (player.getStaff() == 0) {
            player1.setStaff(false);
        } else if (player.getStaff() == 1) {
            player1.setStaff(true);
        }
        iplayerDao.save(player1);
        }

    public Iterable<PlayerEntity> getAllPlayers() {
        return iplayerDao.findAll();
    }
}
