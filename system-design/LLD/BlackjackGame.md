
# Blackjack Game - Low-Level Design

## 1. Requirements Analysis

### Functional Requirements

- Core blackjack gameplay: hit, stand, double down, split, surrender
- Card dealing and deck management (multiple decks, shuffling)
- Hand evaluation and scoring (soft/hard ace, blackjack, bust)
- Betting mechanics: virtual chips, bet placement, payout calculation, insurance, side bets
- Player authentication and profile management
- Game session management (single-player, multiplayer, tournaments)
- Multiplayer room creation, joining, and management
- Tournament creation, management, and progression (elimination, round-robin, prizes)
- Statistics tracking: wins, losses, hands played, winnings, streaks
- Leaderboards and ranking systems (global, friends, tournaments)
- Game variants and rule customization (European, Vegas Strip, Atlantic City, custom rules)
- AI dealer behavior and decision making (configurable difficulty)
- Chat functionality for multiplayer games
- Game history and replay features
- Administrative functions: user management, game monitoring, banning, logs

### Non-Functional Requirements

- Response time for game actions: < 100ms (local), < 200ms (multiplayer)
- Support for 1000+ concurrent players
- System availability: 99.5% uptime
- Data consistency for betting, statistics, and transactions
- Security for financial transactions and player data
- Cross-platform compatibility: web, mobile, desktop
- Real-time synchronization latency: < 200ms
- Database performance for player statistics and leaderboards

### Assumptions & Constraints

- Technology stack: Java (Spring Boot), WebSocket for real-time, REST for stateless ops
- Relational DB (PostgreSQL) for transactional data, Redis for caching
- Client-side rendering: React (web), native mobile apps
- Reliable network required for multiplayer
- Compliance with gambling regulations (if real money)
- Budget constraints for cloud infrastructure
- Payment integration (if real money): PCI DSS compliance
- Mobile device limitations: memory, screen size, battery

## 2. High-Level Architecture

### System Components

- **Game Engine**: Core game logic, rule enforcement, state management
- **User Management Service**: Authentication, profiles, session management
- **Betting and Wallet Service**: Chip management, transactions, payouts
- **Tournament Management Service**: Tournament creation, progression, prizes
- **Statistics and Analytics Service**: Tracking, leaderboards, achievements
- **Real-time Communication Service**: WebSocket server for multiplayer sync, chat
- **Database Layer**: Persistent storage (PostgreSQL), caching (Redis)
- **Client Applications**: Web (React), mobile (iOS/Android)
- **Administrative Dashboard**: User/game monitoring, moderation
- **External Integrations**: Payment gateways, OAuth providers

### Core Entities

- **Player/User**: Profile, wallet, statistics, achievements
- **Game/GameSession**: State, players, hands, bets, variant
- **Card/Deck**: Suit, rank, value, deck management
- **Hand**: Cards, score, actions
- **Bet/Transaction**: Amount, type, status, payout
- **Tournament**: Structure, participants, rounds, prizes
- **Room/Table**: Multiplayer grouping, state
- **GameVariant**: Rule set, configuration
- **Statistics**: Wins, losses, hands played, streaks
- **Achievement**: Milestones, badges

### Component Interactions

- **RESTful APIs**: User management, statistics, leaderboards, admin
- **WebSocket**: Real-time game state, chat, multiplayer actions
- **Database Transactions**: Consistency for bets, stats, history
- **Event-driven Architecture**: Game state changes, notifications
- **Message Queuing**: Tournament notifications, async processing

## 3. Class Design

### UML Class Diagram

```mermaid
classDiagram
    class Card {
        <<enum>> Suit
        <<enum>> Rank
        +Suit suit
        +Rank rank
        +int value
        +toString()
    }
    class Deck {
        -List<Card> cards
        +shuffle()
        +dealCard() Card
        +reset()
    }
    class Hand {
        -List<Card> cards
        +addCard(Card)
        +getScore() int
        +isBlackjack() boolean
        +isBust() boolean
        +canSplit() boolean
        +canDoubleDown() boolean
        +toString()
    }
    class Player {
        +String id
        +String name
        +Wallet wallet
        +Statistics stats
        +List<Hand> hands
        +PlayerProfile profile
        +joinGame(Game)
        +placeBet(Bet)
        +performAction(Action)
    }
    class Dealer {
        +Hand hand
        +DealerAI ai
        +dealCard(Deck)
        +playTurn(GameRules)
    }
    class Game {
        +String id
        +List<Player> players
        +Dealer dealer
        +Deck deck
        +GameState state
        +GameVariant variant
        +start()
        +end()
        +nextTurn()
        +processAction(Player, Action)
    }
    class GameEngine {
        +startGame(GameVariant, List<Player>)
        +processPlayerAction(Game, Player, Action)
        +evaluateHands(Game)
        +endGame(Game)
    }
    class Tournament {
        +String id
        +List<Player> participants
        +TournamentStructure structure
        +start()
        +advanceRound()
        +awardPrizes()
    }
    class Bet {
        +Player player
        +double amount
        +BetType type
        +BetStatus status
        +payout()
    }
    class Wallet {
        +double balance
        +deposit(double)
        +withdraw(double)
        +getBalance()
    }
    class Statistics {
        +int wins
        +int losses
        +int handsPlayed
        +double totalWinnings
        +update(Outcome)
    }
    class DealerAI {
        +DifficultyLevel level
        +decideAction(Hand, GameRules)
    }
    enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }
    enum Rank { TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE }
    enum GameState { WAITING, DEALING, PLAYING, FINISHED }
    enum Action { HIT, STAND, DOUBLE_DOWN, SPLIT, SURRENDER, INSURANCE }
    enum BetType { MAIN, INSURANCE, SIDE }
    enum BetStatus { PLACED, WON, LOST, PUSH }
    enum DifficultyLevel { EASY, MEDIUM, HARD }

    Card "*" -- "1" Deck
    Deck "1" -- "*" Game
    Player "*" -- "1" Game
    Dealer "1" -- "1" Game
    Hand "*" -- "1" Player
    Hand "*" -- "1" Dealer
    Bet "*" -- "1" Player
    Tournament "*" -- "*" Player
    GameEngine "1" -- "*" Game
    GameVariant <|-- Game
    DealerAI "1" -- "1" Dealer
    Wallet "1" -- "1" Player
    Statistics "1" -- "1" Player
```

### Interface Definitions

```java
public interface GameEngine {
    Game startGame(GameVariant variant, List<Player> players) throws GameException;
    void processPlayerAction(Game game, Player player, Action action) throws InvalidActionException;
    void evaluateHands(Game game);
    void endGame(Game game);
}

public interface PlayerService {
    Player register(String username, String password) throws UserAlreadyExistsException;
    Player login(String username, String password) throws AuthenticationException;
    void updateProfile(Player player, PlayerProfile profile);
    Player getPlayerById(String playerId);
}

public interface DealerService {
    void dealInitialCards(Game game);
    void playDealerTurn(Game game);
}

public interface BettingService {
    void placeBet(Player player, double amount, BetType type) throws InsufficientFundsException;
    void payout(Player player, double amount);
    double getBalance(Player player);
}

public interface TournamentService {
    Tournament createTournament(TournamentStructure structure, List<Player> participants);
    void advanceRound(Tournament tournament);
    void awardPrizes(Tournament tournament);
}

public interface GameRepository {
    void saveGame(Game game);
    Game findGameById(String gameId);
    List<Game> findActiveGames();
}

public interface PlayerRepository {
    void savePlayer(Player player);
    Player findPlayerById(String playerId);
    List<Player> findAll();
}
```

### Core Classes

```java
// Card.java
public class Card {
    public enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }
    public enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
        JACK(10), QUEEN(10), KING(10), ACE(11);
        private final int value;
        Rank(int value) { this.value = value; }
        public int getValue() { return value; }
    }
    private final Suit suit;
    private final Rank rank;
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    public int getValue() { return rank.getValue(); }
    public Suit getSuit() { return suit; }
    public Rank getRank() { return rank; }
    @Override
    public String toString() { return rank + " of " + suit; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return suit == card.suit && rank == card.rank;
    }
    @Override
    public int hashCode() { return suit.hashCode() * 31 + rank.hashCode(); }
}

// Deck.java
public class Deck {
    private final List<Card> cards = new ArrayList<>();
    private final int numDecks;
    public Deck(int numDecks) {
        this.numDecks = numDecks;
        reset();
    }
    public void shuffle() {
        Collections.shuffle(cards, new SecureRandom());
    }
    public Card dealCard() {
        if (cards.isEmpty()) throw new NoSuchElementException("Deck is empty");
        return cards.remove(cards.size() - 1);
    }
    public void reset() {
        cards.clear();
        for (int d = 0; d < numDecks; d++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    cards.add(new Card(suit, rank));
                }
            }
        }
        shuffle();
    }
    public int size() { return cards.size(); }
}

// Hand.java
public class Hand {
    private final List<Card> cards = new ArrayList<>();
    public void addCard(Card card) { cards.add(card); }
    public List<Card> getCards() { return Collections.unmodifiableList(cards); }
    public int getScore() {
        int total = 0, aces = 0;
        for (Card c : cards) {
            total += c.getValue();
            if (c.getRank() == Card.Rank.ACE) aces++;
        }
        while (total > 21 && aces > 0) {
            total -= 10; aces--;
        }
        return total;
    }
    public boolean isBlackjack() { return cards.size() == 2 && getScore() == 21; }
    public boolean isBust() { return getScore() > 21; }
    public boolean canSplit() {
        return cards.size() == 2 && cards.get(0).getRank() == cards.get(1).getRank();
    }
    public boolean canDoubleDown() { return cards.size() == 2; }
    @Override
    public String toString() { return cards.toString(); }
}

// Player.java
public class Player {
    private final String id;
    private final String name;
    private final Wallet wallet;
    private final Statistics stats;
    private final List<Hand> hands = new ArrayList<>();
    private final PlayerProfile profile;
    public Player(String id, String name, Wallet wallet, Statistics stats, PlayerProfile profile) {
        this.id = id;
        this.name = name;
        this.wallet = wallet;
        this.stats = stats;
        this.profile = profile;
        hands.add(new Hand());
    }
    public void joinGame(Game game) { game.addPlayer(this); }
    public void placeBet(Bet bet) { wallet.withdraw(bet.getAmount()); }
    public void performAction(Action action) { /* Implementation omitted for brevity */ }
    // Getters, setters, equals, hashCode, toString omitted for brevity
}

// Dealer.java
public class Dealer {
    private final Hand hand = new Hand();
    private final DealerAI ai;
    public Dealer(DealerAI ai) { this.ai = ai; }
    public void dealCard(Deck deck) { hand.addCard(deck.dealCard()); }
    public void playTurn(GameRules rules) { ai.decideAction(hand, rules); }
    public Hand getHand() { return hand; }
}

// Game.java
public class Game {
    private final String id;
    private final List<Player> players;
    private final Dealer dealer;
    private final Deck deck;
    private GameState state;
    private final GameVariant variant;
    public Game(String id, List<Player> players, Dealer dealer, Deck deck, GameVariant variant) {
        this.id = id;
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
        this.variant = variant;
        this.state = GameState.WAITING;
    }
    public void start() { state = GameState.DEALING; /* Deal cards, etc. */ }
    public void end() { state = GameState.FINISHED; }
    public void nextTurn() { /* Advance turn logic */ }
    public void processAction(Player player, Action action) { /* Handle player action */ }
    // Getters, setters, etc.
}

// GameEngineImpl.java
public class GameEngineImpl implements GameEngine {
    @Override
    public Game startGame(GameVariant variant, List<Player> players) throws GameException {
        // Create game, initialize deck, dealer, etc.
        return new Game(UUID.randomUUID().toString(), players, new Dealer(new DealerAI(DifficultyLevel.MEDIUM)), new Deck(variant.getNumDecks()), variant);
    }
    @Override
    public void processPlayerAction(Game game, Player player, Action action) throws InvalidActionException {
        // Validate and process action
    }
    @Override
    public void evaluateHands(Game game) {
        // Evaluate all hands, determine winners
    }
    @Override
    public void endGame(Game game) {
        game.end();
    }
}

// Tournament.java
public class Tournament {
    private final String id;
    private final List<Player> participants;
    private final TournamentStructure structure;
    public Tournament(String id, List<Player> participants, TournamentStructure structure) {
        this.id = id;
        this.participants = participants;
        this.structure = structure;
    }
    public void start() { /* Start tournament */ }
    public void advanceRound() { /* Advance round */ }
    public void awardPrizes() { /* Award prizes */ }
}

// Bet.java
public class Bet {
    private final Player player;
    private final double amount;
    private final BetType type;
    private BetStatus status;
    public Bet(Player player, double amount, BetType type) {
        this.player = player;
        this.amount = amount;
        this.type = type;
        this.status = BetStatus.PLACED;
    }
    public double getAmount() { return amount; }
    public void payout() { /* Calculate and pay winnings */ }
    // Getters, setters, etc.
}

// Wallet.java
public class Wallet {
    private double balance;
    public Wallet(double initialBalance) { this.balance = initialBalance; }
    public void deposit(double amount) { balance += amount; }
    public void withdraw(double amount) {
        if (balance < amount) throw new InsufficientFundsException();
        balance -= amount;
    }
    public double getBalance() { return balance; }
}

// Statistics.java
public class Statistics {
    private int wins, losses, handsPlayed;
    private double totalWinnings;
    public void update(Outcome outcome) { /* Update stats */ }
    // Getters, setters, etc.
}

// DealerAI.java
public class DealerAI {
    private final DifficultyLevel level;
    public DealerAI(DifficultyLevel level) { this.level = level; }
    public void decideAction(Hand hand, GameRules rules) { /* AI logic */ }
}

// Exception classes, enums, and other supporting classes omitted for brevity
```

## 4. Design Patterns Used

- **Singleton Pattern**: Ensures only one instance of GameEngine and DeckFactory exists for consistent configuration and resource management.
- **State Pattern**: Manages transitions between game states (waiting, dealing, playing, finished) for clear game flow.
- **Strategy Pattern**: Allows different game variants and AI dealer strategies to be plugged in without changing core logic.
- **Observer Pattern**: Notifies clients of real-time game state changes and chat messages via WebSocket.
- **Factory Pattern**: Creates different types of games, tournaments, and decks based on configuration.
- **Command Pattern**: Encapsulates player actions (hit, stand, split, etc.) for undo/redo and logging.
- **Template Method Pattern**: Defines the skeleton of game flow, allowing variants to override specific steps.
- **Builder Pattern**: Simplifies construction of complex objects like Game, Tournament, and PlayerProfile.

## 5. Key Algorithms

- **Card Shuffling**: Uses Fisher-Yates shuffle for unbiased, secure shuffling of decks.
- **Hand Evaluation**: Calculates hand score with proper soft/hard ace handling (aces count as 11 unless bust, then 1).
- **Basic Strategy Calculator**: Computes optimal player moves based on hand and dealer upcard (for AI and hints).
- **AI Dealer Decision-Making**: DealerAI uses rules and difficulty level to decide actions (e.g., hit on soft 17, stand otherwise).
- **Tournament Bracket Generation**: Generates single/double elimination or round-robin brackets, advances winners.
- **Player Matching**: Matches players to rooms/tables based on skill, bet size, and availability.
- **Statistics Calculation**: Aggregates player stats, calculates rankings, streaks, achievements.
- **Fraud Detection**: Monitors for unusual betting patterns, rapid chip transfers, or collusion.

## 6. Exception Handling Strategy

- **Custom Exception Hierarchy**: GameException (base), InvalidActionException, InsufficientFundsException, AuthenticationException, etc.
- **Validation Layers**: Input validation (API), business logic validation (service), data validation (DB constraints).
- **Graceful Recovery**: Rollback transactions on error, notify users of recoverable issues, auto-retry where safe.
- **Logging**: Structured logging for all errors, with correlation IDs for tracing.
- **User-Friendly Messages**: Map technical errors to clear, actionable user messages.
- **Transaction Rollback**: Use DB transactions for all betting and wallet operations to ensure atomicity.

## 7. Thread Safety Considerations

- **Synchronized Game State**: Use locks or concurrent data structures for shared game state (e.g., table, deck).
- **Thread-Safe Deck**: Ensure shuffling and dealing are atomic per game instance.
- **Concurrent Player Actions**: Queue and process player actions in order, prevent race conditions.
- **DB Isolation Levels**: Use appropriate transaction isolation for betting and stats updates.
- **WebSocket Management**: Handle concurrent connections, message ordering, and disconnects.
- **Deadlock Avoidance**: Design multiplayer and betting flows to avoid circular waits.

## 8. Testing Strategy

```java
// Unit test: Hand scoring
@Test
public void testHandScoringWithAces() {
    Hand hand = new Hand();
    hand.addCard(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
    hand.addCard(new Card(Card.Suit.SPADES, Card.Rank.SIX));
    hand.addCard(new Card(Card.Suit.CLUBS, Card.Rank.SEVEN));
    assertEquals(14, hand.getScore());
}

// Integration test: Game flow
@Test
public void testGameFlow() {
    GameEngine engine = new GameEngineImpl();
    List<Player> players = Arrays.asList(new Player(...));
    Game game = engine.startGame(GameVariant.VEGAS_STRIP, players);
    engine.processPlayerAction(game, players.get(0), Action.HIT);
    engine.evaluateHands(game);
    assertEquals(GameState.FINISHED, game.getState());
}

// Performance test: Concurrent players
@Test
public void testConcurrentPlayers() throws InterruptedException {
    ExecutorService pool = Executors.newFixedThreadPool(100);
    for (int i = 0; i < 1000; i++) {
        pool.submit(() -> {/* Simulate player actions */});
    }
    pool.shutdown();
    assertTrue(pool.awaitTermination(10, TimeUnit.SECONDS));
}

// Security test: Insufficient funds
@Test(expected = InsufficientFundsException.class)
public void testInsufficientFunds() {
    Wallet wallet = new Wallet(10);
    wallet.withdraw(100);
}

// Edge case: Split aces
@Test
public void testSplitAces() {
    Hand hand = new Hand();
    hand.addCard(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
    hand.addCard(new Card(Card.Suit.SPADES, Card.Rank.ACE));
    assertTrue(hand.canSplit());
}
```

## 9. Scalability & Extensibility

- **Horizontal Scaling**: Stateless services, load balancers, container orchestration (Kubernetes)
- **Database Sharding**: Partition player/game data for high throughput
- **Caching**: Use Redis for hot stats, leaderboards, session data
- **Microservices**: Decompose into user, game, betting, tournament, and chat services
- **Plugin Architecture**: Add new game variants, AI strategies, or tournament types via plugins
- **API Versioning**: Maintain backward compatibility for clients
- **Load Balancing**: Distribute game sessions across servers
- **CDN**: Serve static assets (images, JS, CSS) globally

## 10. Alternative Approaches

- **Event Sourcing**: Store all game actions as events for full audit/replay, but increases complexity
- **Relational vs NoSQL**: Relational DB for strong consistency, NoSQL for high scalability (trade-off: complexity vs ACID)
- **Peer-to-Peer vs Server-Based**: Server-based ensures fairness and anti-cheat, P2P reduces server load but is less secure
- **Real-Time vs Turn-Based**: Real-time (WebSocket) for fast-paced games, turn-based (REST/polling) for casual/asynchronous play
- **Monolithic vs Microservices**: Monolith is simpler for MVP, microservices scale better for large user base
- **Client-Side vs Server-Side Logic**: Server-side for fairness/security, client-side for responsiveness (hybrid possible)
- **Trade-Offs**: Each approach impacts scalability, security, complexity, and user experience; choose based on business goals
