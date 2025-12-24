import java.util.*;
/**
# BattleShip Game

Design and implement a battleship game to be played between two players until one comes out as the winner.

---

## Requirements

- The game will be played in a square area of the sea with **NxN grids**, which will be called a **battlefield**.  
  ➤ `N` should be taken as input in your code.

- The battlefield will be **divided in half** between both players.  
  ➤ So in a NxN battlefield, **Nx(N/2)** grids will belong to `PlayerA`, and the other **Nx(N/2)** grids will belong to `PlayerB`.

- The **size and location of each ship** will be taken as input.  
  ➤ Each ship is assumed to be **square-shaped**.  
  ➤ Both players will have **equal fleet** (equal number and size of ships).

- The **location of each ship** in the NxN grid has to be input as `(X, Y)` coordinates:  
  ➤ Example: if a ship `SH1` is at (0, 0) with a size of 4, its corners would span:
  ```
  Top-left:     (0, 0)
  Bottom-left:  (4, 0)
  Top-right:    (0, 4)
  Bottom-right: (4, 4)
  ```

- Ships are **stationary**.  
  ➤ No overlapping is allowed, but ships can **touch edges**.

- Each player **fires one missile per turn** using a **random coordinate fire strategy**.  
  ➤ A missile is fired at a **random coordinate** of the opponent’s field.  
  ➤ Outcome:
    - `Hit`: Opponent’s ship is destroyed.
    - `Miss`: Nothing happens.
  ➤ The turn is then passed to the other player.

- **No missile should be fired at the same coordinate twice**.

- A player **loses** when **all their ships are destroyed**.

---

## Mandatory APIs

### `initGame(N)`
- Initializes the game with a **NxN battlefield**.
- The **left half (N/2 x N)** is assigned to `PlayerA`.
- The **right half (N/2 x N)** is assigned to `PlayerB`.

---

### `addShip(id, size, xA, yA, xB, yB)`
- Adds a ship of given `size` and `id` at the given coordinates:
  - `(xA, yA)` → Position for `PlayerA`
  - `(xB, yB)` → Position for `PlayerB`

---

### `startGame()`
- Begins the game.
- `PlayerA` always starts first.
- Output should be printed to the console.

Example output:
```
PlayerA’s turn: Missile fired at (2, 4). “Hit”. PlayerB’s ship with id “SH1” destroyed.
PlayerB’s turn: Missile fired at (6, 1). “Miss”
```

---

## Optional API

### `viewBattleField()`
- Displays the battlefield as a **NxN grid**.
- Shows all ships and their occupied grids.
- Ship ID markings:
  - `PlayerA` → A-SH1, A-SH2, etc.
  - `PlayerB` → B-SH1, B-SH2, etc.
- All occupied grid cells of each ship should be marked (not just the center).

---

## Guidelines

- Use **in-memory data structures**.
- **Console-based output** is fine.
- Design your app so new **fire strategies** (e.g. manual input, AI, pattern-based) can be plugged in.
- Maintain **separation of data layer and service layer**.

---

## Expectations

- Implement all mandatory functionalities.
- Code should be:
  - Executable
  - Clean & refactored
  - With **graceful exception handling**
- Display appropriate error messages when invalid input is given.

---

## Evaluation Criteria

- ✅ Working Code  
- ✅ Code Readability & Testability  
- ✅ Separation of Concerns  
- ✅ Proper Abstraction  
- ✅ OOP Concepts  
- ✅ Language Proficiency  
- ✅ Scalability  
- ✅ Bonus: **Test Coverage**

---

## Sample Run

```plaintext
>> initGame(6)
>> addShip("SH1", size = 2, 1, 5, 4, 4)
>> viewBattleField()
>> startGame()

PlayerA’s turn: Missile fired at (3, 0) : “Miss” : Ships Remaining - PlayerA:1, PlayerB:1  
PlayerB’s turn: Missile fired at (1, 1) : “Miss” : Ships Remaining - PlayerA:1, PlayerB:1  
PlayerA’s turn: Missile fired at (5, 3) : “Hit” B-SH2 destroyed : Ships Remaining - PlayerA:1, PlayerB:0  
GameOver. PlayerA wins.
```

 */

class Main {

    public static void main(String[] args) {
        // Scanner myObj = new Scanner(System.in);
        // String line = myObj.nextLine();

        // int ans = new Solution().solution(line);
        Game game = new Game();
        game.initGame(6);
        // game.printBoard();
        game.addShip("SH1", 2, 0, 0, 3, 3);
        game.addShip("SH2", 1, 2, 0, 4, 5);
        game.printBoard();
        game.startGame();
        // System.out.print(ans);
        
    }
}
/init - 
/createnewGame
/addships
/load lastgame
/analytics 


api gateway 
multiple instance of game serice -> LB .
//log table 

UI -> gameservice. - > db
    -> lastsaved checkpoint  -> cache
    -> state pattern 
-> notification service - > played for long time /

random coord
// starategy design 
choose coordinate , randomly get player qith more ships,

//postgres



db - game 
game id 
game- users
game id - id, number 
userdb -
gameplay table 
game state



class Game {
    String[][]board;
    int N=0;
    Queue<String> players;
    Map<String,Set<Ship>> ships;
    Set<String> coordinates;
    int totalShips;
    public Game(){
        this.players = new LinkedList();
        this.ships = new HashMap();
        this.coordinatesSet = new HashSet();//format will be X+Y
        this.totalShips = 0;
    }
    public void initGame(int N){
        this.N = N;
        this.board = new String [N][N];
        this.players.add("A");
        this.players.add("B");
        //add players 
        this.ships.put("A",new HashSet<>());
        this.ships.put("B",new HashSet<>());
    }
    public void addShip(String id , int size, int xA, int yA, int xB, int yB){
        //move condition to here
        if(xA+size<0 || xA+size>N/2 ||
            xB+size<=N/2||xB+size>=N  ||
            yA+size<0||yA+size>=N || 
            yB+size<0||yB+size>=N){
                System.out.println("condition failed for A or B");
                return;
            }
        dfs(xA,yA,size,id);
        dfs(xB,yB,size,id);
        this.totalShips+=2;//adding for both A and B

    }
    //add coordinates with id.
    public void dfs(int x, int y, int size, String id){
        for(int i=x;i<x+size;i++){
            for(int j=y;j<y+size;j++){
                if(board[i][j]==null){
                    board[i][j]= id;
                }
            }
        }
    }

    public void printBoard(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    thread safe public String startGame(){
        String player = "";
        while(true){
            //get player from queue
            player = this.players.poll();
            //generate random coordinate
            int[]coord = this.getRandomCoordinates(player);
            if(coordinatesSet.contains(coord[0]+"+"+coord[1])){
                System.out.println(Arrays.toString(coord));
                continue;
            }
            //fire missile
            Set<Ships> opponentShip = this.ships.get(this.players.peek());
            Ship ship = opponentShip.get(coord);
            //get ship coordinate
            
            this.fireDfs(ship.id);
            //destroy ship if at place.
            this.totalShips--;
            //remove from set 
            opponentShip.remove(ship);
            //get count
            if(this.totalShips==1){
                return player;
            }
            //add player back to queue
            this.players.add(player);
        }
    }
    public int[] getRandomCoordinates(String player){
        int coord[] = new int[2];
        if(player.equals("A")){
        coord[0] = Integer.valueOf(Math.random(this.N/2,this.N));
        }else {
        coord[0] = Integer.valueOf(Math.random(0,this.N/2));
        }
        coord[1] = Integer.valueOf(Math.random(0,this.N));
        return coord;
    }
    public void fireDfs(int x, int y,String id){
        int[][]dir = new int[][]{
            {-1,0},
            {-1,-1},
            {0,1},
            {0,-1},
            {1,0},
            {1,1},
            {1,-1},
            {-1,1}
        }
        for(int[]d:dir){
            int newX = x+d[0],newY=y+d[1];
            if(newX>0 && newX<N && newY>0&& new Y<N && board[newX][newY]==id){
                board[newX][newY] = null;
                fireDfs(x,y,id);
            }
        }
    }
}
class Ship{
    String id;
    int x;
    int y;
    int size;
    public Ship(String id, int x, int y, int size){
        this.id = id;
        this.x = x;
        this.y = y;
        this.size = size;
    }
}

//classes 
// player
//game 
//board
//cell
//ship
//main
