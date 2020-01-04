package comm;

import logic.Game;
import logic.Player;

import java.io.IOException;

/**
 * @author Tomas.Chour - Aspectworks
 * @date 2019-12-02
 */
public class CommunicationDtosService {

    public static void informPlayers(Game game) {
        if (game.getPlayerA() != null) {
            Player pA = game.getPlayerA();
            ServerDto dto = fillServerResponseForPlayer(game, pA);
            try {
                pA.out.writeObject(dto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (game.getPlayerB() != null) {
            Player pB = game.getPlayerB();
            ServerDto dto = fillServerResponseForPlayer(game, pB);
            try {
                pB.out.writeObject(dto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ServerDto fillServerResponseForPlayer(Game game, Player player) {
        ServerDto dto = new ServerDto();
        dto.id = player.getId();
        dto.playerPoints = player.getPoints();
        dto.gameState = game.getCurrentGameState();
        dto.playerPoints = player.getPoints();
        dto.playerFields = player.getPlayerFields();
        dto.ships = player.getShips();
        Player opponent = game.getOpponentForPlayer(player);
        if (opponent != null) {
            dto.opponentId = opponent.getId();
            dto.opponentPoints = opponent.getPoints();
            dto.opponentField = opponent.getPlayerFields();
            dto.destroyedOpponentsShips = opponent.destroyedShips();
        }
        dto.shotResult = game.getLastShotResult();
        dto.gameId = game.getGameId();
        if (game.getCurrentlyPlaying() != null) {
            dto.isMyTurn = game.getCurrentlyPlaying().getId().equals(player.getId());
        }
        return dto;
    }
}
