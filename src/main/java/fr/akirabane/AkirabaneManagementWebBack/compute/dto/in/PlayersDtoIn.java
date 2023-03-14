package fr.akirabane.AkirabaneManagementWebBack.compute.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.akirabane.AkirabaneManagementWebBack.compute.entity.PlayerEntity;

public class PlayersDtoIn {
    private Integer id;
    private String pseudo_player;
    private String uuid_player;

    private String password_player;
    private Integer staff;

    public PlayersDtoIn() {
    }

    public PlayersDtoIn(Integer id, String pseudo_player, String uuid_player, String password_player, Integer staff) {
        this.id = id;
        this.pseudo_player = pseudo_player;
        this.uuid_player = uuid_player;
        this.password_player = password_player;
        this.staff = staff;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPseudo_player() {
        return pseudo_player;
    }

    public void setPseudo_player(String pseudo_player) {
        this.pseudo_player = pseudo_player;
    }

    public String getUuid_player() {
        return uuid_player;
    }

    public void setUuid_player(String uuid_player) {
        this.uuid_player = uuid_player;
    }

    public String getPassword_player() {
        return password_player;
    }

    public void setPassword_player(String password_player) {
        this.password_player = password_player;
    }

    public Integer getStaff() {
        return staff;
    }

    public void setStaff(Integer staff) {
        this.staff = staff;
    }

    /**
     * @doc Convert to entity
     * @return
     */
    @JsonIgnore
    public PlayerEntity toEntity() {
        if(pseudo_player == null || uuid_player == null || staff == null)
           throw new IllegalArgumentException("Cannot convert to entity, missing data");
        else if (this.staff < 0 || this.staff > 2) {
            throw new IllegalArgumentException("Cannot convert to entity, staff value is not valid");
        } else if(this.staff == 0) {
            return new PlayerEntity(this.id, this.pseudo_player, this.uuid_player, this.password_player, false);
        } else {
            return new PlayerEntity(this.id, this.pseudo_player, this.uuid_player, this.password_player, true);
        }
    }
}