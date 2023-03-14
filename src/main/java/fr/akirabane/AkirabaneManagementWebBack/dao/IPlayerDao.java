package fr.akirabane.AkirabaneManagementWebBack.dao;

import fr.akirabane.AkirabaneManagementWebBack.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPlayerDao extends JpaRepository<PlayerEntity, Integer> {
    //find player by uuid
    @Query("SELECT p FROM PlayerEntity p WHERE p.uuid_player = ?1")
    Optional<PlayerEntity> findByUuid_player(String uuid_player);

    //find player by pseudo
    @Query("SELECT p FROM PlayerEntity p WHERE p.pseudo_player = ?1")
    Optional<PlayerEntity> findById(String pseudo_player);
}
