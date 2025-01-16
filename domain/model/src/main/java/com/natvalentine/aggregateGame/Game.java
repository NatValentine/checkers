package com.natvalentine.aggregateGame;

import com.natvalentine.aggregateGame.events.*;
import com.natvalentine.aggregateGame.values.GameId;
import com.natvalentine.aggregateGame.values.GameStatus;
import com.natvalentine.aggregateGame.values.objects.Outcome;
import com.natvalentine.aggregateGame.values.objects.Status;
import com.natvalentine.aggregateGame.values.objects.Turn;
import com.natvalentine.board.Board;
import com.natvalentine.board.Tile;
import com.natvalentine.board.values.BoardId;
import com.natvalentine.generics.domain.DomainEvent;
import com.natvalentine.generics.utils.AggregateRoot;
import com.natvalentine.generics.utils.ColorsEnum;
import com.natvalentine.move.values.MoveId;
import com.natvalentine.player.Player;
import com.natvalentine.player.values.PlayerId;
import com.natvalentine.player.values.objects.Color;
import com.natvalentine.player.values.objects.IsCurrent;
import com.natvalentine.user.values.UserId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

public class Game extends AggregateRoot<GameId> {
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;
    private Player idlePlayer;
    private Board board;
    private Status status = Status.of(GameStatus.WAITING.name());
    private Outcome result;
    private Turn currentTurn;

    public Game() {
        super(new GameId());
    }

    protected Game(final String id) {
        super(GameId.of(id));
    }

    public static Mono<Game> from(final String id, Flux<DomainEvent> events) {
        Game game = new Game(id);
        return events
                .filter(eventsFilter -> id.equals(eventsFilter.getAggregateRootId()))
                .concatMap(event -> Mono.just(event)
                        .doOnNext(e -> game.addEvent(e).apply())
                )
                .doOnTerminate(game::markEventsAsCommitted)
                .then(Mono.just(game));
    }

    public Player addPlayer(Player player) {
        if (this.whitePlayer == null){
            this.whitePlayer = player;
            player.setIsCurrent(IsCurrent.of(false));
            player.setColor(Color.of(ColorsEnum.WHITE.name()));
        }
        else if (this.blackPlayer == null) {
            this.blackPlayer = player;
            player.setIsCurrent(IsCurrent.of(false));
            player.setColor(Color.of(ColorsEnum.BLACK.name()));
            startGame();
        } else {
            throw new RuntimeException("The game is full. Cannot join.");
        }

        return player;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getIdlePlayer() {
        return this.idlePlayer;
    }

    public void setIdlePlayer(Player idlePlayer) {
        this.idlePlayer = idlePlayer;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(Player whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(Player blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public void toggleCurrentPlayer() {
        var aux = this.currentPlayer;
        this.currentPlayer = this.idlePlayer;
        this.idlePlayer = aux;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Outcome getResult() {
        return result;
    }

    public void setResult(Outcome result) {
        this.result = result;
    }

    public Turn getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(Turn currentTurn) {
        this.currentTurn = currentTurn;
    }

    public void createBoard(ArrayList<Tile> tiles) {
        addEvent(new BoardCreated(new BoardId().getValue(), tiles)).apply();
    }

    public void createPlayer(String playerId, String userId) {
        Player player = addPlayer(new Player(PlayerId.of(playerId), UserId.of(userId)));
        addEvent(new PlayerCreated(userId, playerId, player.getColor().getValue(), player.getIsCurrent().getValue())).apply();
    }

    public void startGame() {
        setStartingPlayer();
        this.currentTurn = Turn.of(1);
        this.status = Status.of(GameStatus.PLAYING.name());
    }

    private void setStartingPlayer() {
        int index = (int)(Math.random() * 2);
        if (index == 0) {
            this.currentPlayer = whitePlayer;
            this.idlePlayer = blackPlayer;
            this.whitePlayer.setIsCurrent(IsCurrent.of(true));
            this.blackPlayer.setIsCurrent(IsCurrent.of(false));
        } else {
            this.currentPlayer = blackPlayer;
            this.idlePlayer = whitePlayer;
            this.blackPlayer.setIsCurrent(IsCurrent.of(true));
            this.whitePlayer.setIsCurrent(IsCurrent.of(false));
        }
    }

    public void pieceMoved(Tile from, Tile to) { // MoveCreated
        addEvent(new PieceMoved(new MoveId().getValue(), from, to));
    }

    public void pieceTaken(String pieceId) {
        addEvent(new PieceTaken(pieceId));
    }

    public void gameOver() {
        addEvent(new GameOver());
    }
}
