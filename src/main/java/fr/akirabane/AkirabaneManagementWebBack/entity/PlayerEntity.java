package fr.akirabane.AkirabaneManagementWebBack.entity;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String pseudo_player;

    @Column(nullable = false)
    private String uuid_player;

    @Column(nullable = false)
    private Boolean isStaff;

    public PlayerEntity() {
    }

    public PlayerEntity(Integer id, String pseudo_player, String uuid_player, Boolean isStaff) {
        this.id = id;
        this.pseudo_player = pseudo_player;
        this.uuid_player = uuid_player;
        this.isStaff = isStaff;
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

    public Boolean getStaff() {
        return isStaff;
    }

    public void setStaff(Boolean staff) {
        isStaff = staff;
    }
}
