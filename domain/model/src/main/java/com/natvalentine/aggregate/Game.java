package com.natvalentine.aggregate;

import com.natvalentine.aggregate.events.*;
import com.natvalentine.aggregate.values.GameId;
import com.natvalentine.aggregate.values.objects.Outcome;
import com.natvalentine.aggregate.values.objects.Status;
import com.natvalentine.aggregate.values.objects.Turn;
import com.natvalentine.board.Board;
import com.natvalentine.board.Tile;
import com.natvalentine.board.values.BoardId;
import com.natvalentine.generics.domain.DomainEvent;
import com.natvalentine.generics.utils.AggregateRoot;
import com.natvalentine.move.values.MoveId;
import com.natvalentine.player.Player;
import com.natvalentine.player.values.PlayerId;
import com.natvalentine.player.values.objects.IsCurrent;
import com.natvalentine.user.User;
import com.natvalentine.user.values.UserId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Game extends AggregateRoot<GameId> {
    private User user;
    private ArrayList<Player> players = new ArrayList<>();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addPlayer(Player player) {
        if(this.players.toArray().length == 2)
            throw new RuntimeException("The game is full. Player could not join.");

        this.players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public Player getCurrentPlayer() {
        return this.players.stream()
                .filter(p -> p.getIsCurrent().getValue() == true).findFirst().get();
    }

    public Player getIdlePlayer() {
        return this.players.stream()
                .filter(p -> p.getIsCurrent().getValue() == false).findFirst().get();
    }

    public void toggleCurrentPlayer() { // this should be an event
        var playerEndingTurn = this.getCurrentPlayer();
        var playerStartingTurn = this.getIdlePlayer();

        playerEndingTurn.setIsCurrent(IsCurrent.of(false));
        playerStartingTurn.setIsCurrent(IsCurrent.of(true));
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

    public void createUser(String username) {
        addEvent(new UserCreated(new UserId().getValue(), username)).apply();
    }

    public void createPlayer(String userId) {
        addEvent(new PlayerCreated(userId, new PlayerId().getValue()));
    }

    public void secondPlayerJoined() {
        // can start game
        addEvent(new SecondPlayerJoined(this.players));
    }

    public void boardCreated() {
        addEvent(new BoardCreated(new BoardId().getValue()));
        addEvent(new PiecesSet(this.board, this.players));
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
