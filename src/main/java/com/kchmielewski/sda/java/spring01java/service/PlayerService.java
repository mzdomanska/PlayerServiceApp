package com.kchmielewski.sda.java.spring01java.service;

import com.kchmielewski.sda.java.spring01java.entity.PlayerEntity;
import com.kchmielewski.sda.java.spring01java.model.Player;
import com.kchmielewski.sda.java.spring01java.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class PlayerService {

    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public List<Player> getAllPlayers() {
        return toPlayerDto(repository.findAll());
    }

    public List<PlayerEntity> getAllPlayerEntity() {
        return repository.findAll();
    }

    public List<Player> addNewPlayer(Player newPlayer) {
        checkNotNull(newPlayer, "PlayerEntity cannot be null");
        checkArgument(newPlayer.getId() == null,"New player can't have an id");
        repository.save(toEntity(newPlayer));

        return toPlayerDto(repository.findAll());
    }

    public Player getPlayerByID(String id) {
        checkNotNull(id,"Id cannot be null");
        return toPlayerDto(repository.findAll().stream().filter(p->p.getId().equals(Integer.parseInt(id))).findFirst().get());
    }

    public List<Player> deletePlayerById(Integer id) {
        checkNotNull(id,"Player's id cannot be null");
        checkArgument(!repository.findAll().contains(repository.findById(id)),"There is no such player with given id in DB");
        repository.deleteById(id);

        return getAllPlayers();
    }

    public void editPlayer(Player player) {
        checkNotNull(player,"Player cannot be null");
        checkArgument(!repository.findAll().contains(player),"There is no such player in DB");
        Player editedPlayer = new Player(player.getId(),player.getFirstName(),player.getLastName());
        repository.save(toEntity(editedPlayer));
    }

    private PlayerEntity toEntity(Player player) {
        return new PlayerEntity(player.getId(),player.getFirstName(), player.getLastName());
    }

    private Player toPlayerDto(PlayerEntity entity) {
        return new Player(entity.getId(),entity.getFirstName(), entity.getLastName());
    }

    private List<Player> toPlayerDto(List<PlayerEntity> entities) {
        return entities.stream().map(e->toPlayerDto(e)).collect(Collectors.toList());
    }

}
