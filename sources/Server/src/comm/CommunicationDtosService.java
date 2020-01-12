package comm;

import logic.Game;
import logic.GameField;
import logic.Player;

import javax.swing.*;
import java.io.IOException;

/**
 * Service that constructs and sends server response to players
 *
 * @author chot02 - Aspectworks
 */
public class CommunicationDtosService {

    /**
     * Sends ServerDto to both players (if possible)
     *
     * @author chot02
     *
     * @param game game from which we want to fill dto
     */
    public static void informPlayers(Game game) {
        if (game.getPlayerA() != null) {
            try {
                Player pA = game.getPlayerA();
                if (pA.getId() != null || pA.error != null) {
                    ServerDto dto = fillServerResponseForPlayer(game, pA);
                    pA.out.reset();
                    pA.out.writeObject(dto);
                    pA.error = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (game.getPlayerB() != null) {
            try {
                Player pB = game.getPlayerB();
                if (pB.getId() != null || pB.error != null) {
                    ServerDto dto = fillServerResponseForPlayer(game, pB);
                    pB.out.reset();
                    pB.out.writeObject(dto);
                    pB.error = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * creates and fills Server dto with values from game and player
     *
     * @author chot02
     *
     * @param game game from which we want to fill dto
     * @param player player for which dto should be filled
     */
    public static ServerDto fillServerResponseForPlayer(Game game, Player player) {
        ServerDto dto = new ServerDto();
        dto.id = player.getId();
        dto.playerPoints = player.getPoints();
        dto.gameState = determineGameStateForPlayer(game,player);
        dto.playerPoints = player.getPoints();
        dto.playerFields = player.getFields();
        dto.ships = player.getShips();
        dto.error = player.error;
        Player opponent = game.getOpponentForPlayer(player);
        if (opponent != null) {
            dto.opponentId = opponent.getId();
            dto.opponentPoints = opponent.getPoints();
            dto.opponentField = opponent.getFields();
        }
        dto.gameId = game.getGameId();
        if (game.getCurrentGameState() == Game.GameState.PLAYING) {
            dto.isMyTurn = game.getCurrentlyPlaying().getId().equals(player.getId());
        }
        return dto;
    }

    /**
     * determines which GameState should be send to player
     *
     * @author chot02
     *
     * @param game game from which we want to fill dto
     * @param player player for which dto should be filled
     */
    private static Game.GameState determineGameStateForPlayer(Game game, Player player) {
        if (game.getCurrentGameState() == Game.GameState.NEW
                && ((player.readyToPlay() && !game.getOpponentForPlayer(player).readyToPlay())
                        || player.getId() != null &&  game.getOpponentForPlayer(player).getId() == null)) {
            return Game.GameState.WAITING_FOR_OTHER_PLAYER;
        } else {
            if (!game.getGameRunning() && game.getCurrentGameState() == Game.GameState.PLAYING) {
                if (player.isAlive()) {

                    return Game.GameState.WIN;
                } else {
                    return Game.GameState.LOSS;
                }
            }
            return game.getCurrentGameState();
        }
    }

}
