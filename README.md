# Checkers Backend Application

## Requirements
This app's objective is emulating a game of checkers between two players. Each player must be able to create an account and authenticate to enter the current game.

Must haves:
- Reactive Springboot
- NoSQL Database
- Hexagonal architecture
- Event Driven Architecture
- AMQP
- CQRS + VM
- Spring security
- Testing

## Use cases
1. Registro: Debe existir un caso de uso que permita el registro para poder unirse a una partida.
2. Inicio de sesión: Debe existir un caso de uso que valide la información del usuario para poder entrar a la partida.
3. Generación del tablero: Debe existir un caso de uso que genere el tablero con las respectivas fichas en su lugar para iniciar el juego y se debe escoger aleatoriamente el jugador que hace el primer movimiento.
4. Movimiento de las fichas: Debe existir un caso de uso que le permita al jugador indicar la casilla de la ficha que quiere mover y la casilla a la cual desea moverla. Este caso de uso debe de tener sus respectivas validaciones para que el movimiento sea válido y también debe verificar si en el proceso se comió alguna ficha del rival.
5. Estado de la ficha: Este caso de uso debe actualizar el estado de la ficha dependiendo de si se convierte en dama o es eliminada.
6. Estado de la partida: Este caso de uso debe comprobar cuántas fichas tienen vigentes los jugadores y así determinar al ganador o si se da un empate.
7. Verificación de partida finalizada: Cada que se realice un movimiento se debe enviar el tablero a una cola de mensajería de forma que este caso de uso recibe el tablero y utiliza el caso de uso #6 para verificar si la partida continúa o ha finalizado. Si ya finalizó, debe actualizarse la vista materializada de esa partida, para marcar como finalizada e indicar el ganador o si hubo empate.
8. Consulta de partidas: Este caso de uso permite consultar las partidas que se han jugado históricamente junto al estado en el que quedó el tablero, el ganador (o si quedó en empate) junto con la cantidad de fichas que quedaron.
9. Todas las rutas deben estar protegidas con Spring Security y JWT.

