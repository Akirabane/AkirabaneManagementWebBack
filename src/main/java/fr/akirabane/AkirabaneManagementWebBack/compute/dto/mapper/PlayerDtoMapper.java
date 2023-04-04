package fr.akirabane.AkirabaneManagementWebBack.compute.dto.mapper;

import fr.akirabane.AkirabaneManagementWebBack.compute.dto.out.PlayersDtoOut;
import fr.akirabane.AkirabaneManagementWebBack.compute.entity.PlayerEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PlayerDtoMapper implements Function<PlayerEntity, PlayersDtoOut> {
    @Override
    public PlayersDtoOut apply(PlayerEntity playerEntity) {
        return new PlayersDtoOut(
                playerEntity.getId(),
                playerEntity.getPseudo_player(),
                playerEntity.getUuid_player(),
                playerEntity.getPassword_player(),
                booleantoInt(playerEntity.getStaff()));
    }

    private int booleantoInt(boolean bool) {
        if (bool == true) {
            return 1;
        } else {
            return 0;
        }
    }
}
