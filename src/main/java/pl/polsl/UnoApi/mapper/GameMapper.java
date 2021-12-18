package pl.polsl.UnoApi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.UnoApi.model.GameDto;
import pl.polsl.UnoApi.repository.dao.Game;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GameMapper {

    @Mapping(target = "mainPlayer", source = "mainPlayer.id")
    GameDto gameToGameDto(Game game);

    List<GameDto> gameListToGameDtoList(List<Game> games);

}
