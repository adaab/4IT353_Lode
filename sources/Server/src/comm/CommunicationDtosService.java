package comm;

import logic.Game;
import logic.GameField;
import logic.Player;

import java.io.IOException;

/**
 * @author Tomas.Chour - Aspectworks
 * @date 2019-12-02
 */
public class CommunicationDtosService {

    public static void informPlayers(Game game) {
        if (game.getPlayerA() != null) {
            try {
                Player pA = game.getPlayerA();
                if (pA.getId() != null) {
                    ServerDto dto = fillServerResponseForPlayer(game, pA);
                    pA.out.writeObject(dto);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (game.getPlayerB() != null) {
            try {
                Player pB = game.getPlayerB();
                if (pB.getId() != null) {
                    ServerDto dto = fillServerResponseForPlayer(game, pB);
                    for (GameField field : dto.playerFields) {
                        System.out.println("PLAYER FIELDS: " + field.getX() + " " + field.getY() + " " + field.getFieldState());
                    }
                    pB.out.writeObject(dto);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ServerDto fillServerResponseForPlayer(Game game, Player player) {
        ServerDto dto = new ServerDto();
        dto.id = player.getId();
        dto.playerPoints = player.getPoints();
        if (game.getCurrentGameState() == Game.GameState.NEW && player.readyToPlay() && !game.getOpponentForPlayer(player).readyToPlay()) {
            dto.gameState = Game.GameState.WAITING_FOR_OTHER_PLAYER;
        } else {
            dto.gameState = game.getCurrentGameState();
        }
        dto.playerPoints = player.getPoints();
        System.out.println("A0 STATE DTO: " + player.getFields().get(0).getFieldState());
        dto.playerFields = player.getFields();
        System.out.println("A0 STATE DTO: " + dto.playerFields.get(0).getFieldState());
        dto.ships = player.getShips();
        Player opponent = game.getOpponentForPlayer(player);
        if (opponent != null) {
            dto.opponentId = opponent.getId();
            dto.opponentPoints = opponent.getPoints();
            dto.opponentField = opponent.getFields();
            dto.destroyedOpponentsShips = opponent.destroyedShips();
        }
        dto.shotResult = game.getLastShotResult();
        dto.gameId = game.getGameId();
        if (game.getCurrentGameState() == Game.GameState.PLAYING) {
            dto.isMyTurn = game.getCurrentlyPlaying().getId().equals(player.getId());
        }
        return dto;
    }

}
