package com.natvalentine.aggregateGame;

import com.natvalentine.aggregateGame.events.*;
import com.natvalentine.aggregateGame.values.GameId;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

public class Game extends AggregateRoot<GameId> {
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;
    private Player idlePlayer;
    private Board board;
    private Status status;
    private Outcome result;
    private Turn currentTurn;

    public Game() {
        super(new GameId());
        setSubscription(new GameHandler(this));
    }

    protected Game(final String id) {
        super(GameId.of(id));
        setSubscription(new GameHandler(this));
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
            player.setColor(Color.of(ColorsEnum.WHITE.name()));
        }
        else if (this.blackPlayer == null) {
            this.blackPlayer = player;
            player.setColor(Color.of(ColorsEnum.BLACK.name()));
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

    public void createPlayer(String userId) {
        addEvent(new PlayerCreated(userId, new PlayerId().getValue())).apply();
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
