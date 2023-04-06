package fr.akirabane.AkirabaneManagementWebBack.compute.service;

import fr.akirabane.AkirabaneManagementWebBack.compute.dao.IPlayerDao;
import fr.akirabane.AkirabaneManagementWebBack.compute.dto.in.PlayersDtoIn;
import fr.akirabane.AkirabaneManagementWebBack.compute.dto.mapper.PlayerDtoMapper;
import fr.akirabane.AkirabaneManagementWebBack.compute.dto.out.PlayersDtoOut;
import fr.akirabane.AkirabaneManagementWebBack.compute.entity.PlayerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayersService {

    private final PlayerDtoMapper playerDtoMapper;

    @Autowired
    IPlayerDao iplayerDao;

    public PlayersService(PlayerDtoMapper playerDtoMapper) {
        this.playerDtoMapper = playerDtoMapper;
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

    public List<PlayersDtoOut> getAllPlayers() {
        return iplayerDao.findAll()
                .stream()
                .map(playerDtoMapper)
                .collect(Collectors.toList());

    }

    //get player by uuid (type string)
    public PlayersDtoOut getPlayerByUuid(String uuid) {
        var playerEntity = iplayerDao.findByUuid_player(uuid);
        if (!playerEntity.isPresent()) {
            throw new RuntimeException("Player not found");
        }
        return playerDtoMapper.apply(playerEntity.get());
    }
}
