package fr.akirabane.AkirabaneManagementWebBack.compute.service;

import fr.akirabane.AkirabaneManagementWebBack.compute.dao.IPlayerDao;
import fr.akirabane.AkirabaneManagementWebBack.compute.dto.in.PlayersDtoIn;
import fr.akirabane.AkirabaneManagementWebBack.compute.dto.mapper.PlayerDtoMapper;
import fr.akirabane.AkirabaneManagementWebBack.compute.dto.out.PlayersDtoOut;
import fr.akirabane.AkirabaneManagementWebBack.compute.entity.PlayerEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlayersServiceTest {

    @Mock
    private IPlayerDao iplayerDao;

    @InjectMocks
    private PlayersService playersService;

    @BeforeEach
    void setUp() {
        this.playersService = new PlayersService(new PlayerDtoMapper(), iplayerDao);
    }

    @Test
    void addPlayerWithExistingOne() {
        PlayersDtoIn playersDtoIn = new PlayersDtoIn();
        playersDtoIn.setUuid_player("uuid");
        playersDtoIn.setPseudo_player("pseudo");
        playersDtoIn.setStaff(0);

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setUuid_player("uuid");

        Mockito.when(iplayerDao.findByUuid_player("uuid")).thenReturn(Optional.of(playerEntity));

        Assertions.assertThrows(RuntimeException.class, () -> {
            playersService.addPlayer(playersDtoIn);
        }, "Player already exist");


    }

    @Test
    void deletePlayerIfUuidIsNull() {
        //check if player uuid is null on deletion
        PlayersDtoIn playersDtoIn = new PlayersDtoIn();

        Assertions.assertThrows(RuntimeException.class, () -> {
            playersService.deletePlayer(playersDtoIn.getUuid_player());
        }, "Player doesn't exist");
    }

    @Test
    void deletePlayerIfUuidIsntNull() {
        //check if player uuid isn't null on deletion as an example
        PlayersDtoIn playersDtoIn = new PlayersDtoIn();
        playersDtoIn.setUuid_player("uuid".repeat(8));

        Assertions.assertDoesNotThrow(() -> {
            playersService.deletePlayer(playersDtoIn.getUuid_player());
        }, "Player doesn't exist");
    }

    @Test
    void deletePlayerIfUuidIsEmpty() {
        //check if player uuid is empty on deletion
        PlayersDtoIn playersDtoIn = new PlayersDtoIn();
        playersDtoIn.setUuid_player("");

        Assertions.assertThrows(RuntimeException.class, () -> {
            playersService.deletePlayer(playersDtoIn.getUuid_player());
        }, "Player doesn't exist");
    }

    @Test
    void deletePlayerIfUuidHasWrongLength() {
        //check if player uuid has wrong length on deletion
        PlayersDtoIn playersDtoIn = new PlayersDtoIn();
        playersDtoIn.setUuid_player("1".repeat(33));

        Assertions.assertThrows(RuntimeException.class, () -> {
            playersService.deletePlayer(playersDtoIn.getUuid_player());
        }, "Uuid is not valid");
    }

    @Test
    void deletePlayerIfUuidHasGreatLength() {
        //check if player uuid has great length on deletion
        PlayersDtoIn playersDtoIn = new PlayersDtoIn();
        playersDtoIn.setUuid_player("1".repeat(32));

        Assertions.assertDoesNotThrow(() -> {
            playersService.deletePlayer(playersDtoIn.getUuid_player());
        }, "Uuid is valid");
    }

    @Test
    void updatePlayer() {
    }

    @Test
    void getAllPlayers() {
    }
}