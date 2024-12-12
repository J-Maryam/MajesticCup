package org.youcode.majesticcup.mapper;

import org.mapstruct.Mapper;
import org.youcode.majesticcup.dto.player.PlayerRequestDTO;
import org.youcode.majesticcup.dto.player.PlayerResponseDTO;
import org.youcode.majesticcup.model.sub_document.Player;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    Player toEntity(PlayerRequestDTO dto);
    PlayerResponseDTO toDto(Player player);

    List<Player> toPlayerEntities(List<PlayerRequestDTO> dtos);
    List<PlayerResponseDTO> toPlayerResponseDTOs(List<Player> players);
}
