package comm;

import logic.Game;
import logic.GameField;
import logic.Player;

import javax.swing.*;
import java.io.IOException;

/**
 * Service that constructs and sends server response to players
 *
 * @author Tomas.Chour - Aspectworks
 * @date 2019-12-02
 */
public class CommunicationDtosService {

    public static void informPlayers(Game game) {
        if (game.getPlayerA() != null) {
            try {
                Player pA = game.getPlayerA();
                if (pA.getId() != null || pA.error != null) {
                    ServerDto dto = fillServerResponseForPlayer(game, pA);
                    if (dto.playerFields != null && dto.playerFields.get(0) != null) {
                        System.out.println("SEND TO " + pA.getId() + dto.playerFields.get(0).getFieldState());
                        //dto.playerFields.get(0).setFieldState(GameField.FieldState.ship);
                    }
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
                    if (dto.playerFields != null && dto.playerFields.get(0) != null) {
                        System.out.println("SEND TO " + pB.getId() + dto.playerFields.get(0).getFieldState());
                        //dto.playerFields.get(0).setFieldState(GameField.FieldState.ship);
                    }
                    pB.out.reset();
                    pB.out.writeObject(dto);
                    pB.error = null;
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
        dto.gameState = determineGameStateForPlayer(game,player);
        dto.playerPoints = player.getPoints();
        dto.playerFields = player.getFields();
        dto.ships = player.getShips();
        System.out.println("PLAYER ERROR " + player.error);
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

    private static Game.GameState determineGameStateForPlayer(Game game, Player player) {
        if (game.getOpponentForPlayer(player) != null) {
            System.out.println("TTTTTTTTTTT " + game.getCurrentGameState() + " " + player.readyToPlay() + " " + !game.getOpponentForPlayer(player).readyToPlay()
                    + " " + player.getId() + " " + game.getOpponentForPlayer(player).getId());
        }
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
