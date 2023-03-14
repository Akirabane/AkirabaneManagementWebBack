package fr.akirabane.AkirabaneManagementWebBack.dto.out;

public class PlayersDtoOut {
    private Integer id;
    private String pseudo_player;
    private String uuid_player;
    private Integer staff;

    public PlayersDtoOut() {
    }

    public PlayersDtoOut(Integer id, String pseudo_player, String uuid_player, Integer staff) {
        this.id = id;
        this.pseudo_player = pseudo_player;
        this.uuid_player = uuid_player;
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

    public Integer getStaff() {
        return staff;
    }

    public void setStaff(Integer staff) {
        this.staff = staff;
    }
}
