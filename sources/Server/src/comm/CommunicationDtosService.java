package comm;

import logic.Game;
import logic.Player;

/**
 * @author Tomas.Chour - Aspectworks
 * @date 2019-12-02
 */
public class CommunicationDtosService {

    public static void fillServerResponseForPlayer(Game game, ServerDto dto, Player player) {
        dto.playerPoints = player.getPoints();
        dto.gameState = game.getCurrentGameState();
        dto.playerPoints = player.getPoints();
        dto.playerFields = player.getPlayerFields();
        dto.ships = player.getShips();
        dto.opponentId = game.getOpponentForPlayer(player).getId();
        dto.opponentPoints = game.getOpponentForPlayer(player).getPoints();
        dto.opponentField = game.getOpponentForPlayer(player).getPlayerFields();
        dto.destroyedOpponentsShips = game.getOpponentForPlayer(player).destroyedShips();
    }
}
