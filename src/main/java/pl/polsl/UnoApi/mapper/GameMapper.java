package pl.polsl.UnoApi.mapper;

import org.mapstruct.Mapper;
import pl.polsl.UnoApi.model.GameDto;
import pl.polsl.UnoApi.repository.dao.Game;

@Mapper(componentModel = "spring")
public interface GameMapper {

    GameDto gameToGameDto(Game game);

}
