# design a  loggign library such as log4j

## Functional Requirements

1. logging Levels

    Should support logging levels like INFO, WARN, DEBUG, TRACE, ERROR
    There Should be flexibility

2. High Performance
    Must have 1 million concurrent logs per second, making async logging and buffering mandatory

3. Appenders
    FileAppenders, ConsoleAppenders, RollingFileAppenders

4. Thread Safety
    Multiple concurrent thread will be able to generate the log

5. Async Logging
    logging Process should not block main execution thread and shall be handled from behind.

6. Formatter/Layout of the logs:
    [TimeStamp] [LogLevel] [Thread] - Message would be the log format either in file or on appender.

7. Configuration:
    can be configured in json file
    Can be dynamically updated logging behaviour at the run time.

8. Filters
    Apply Conditional Logging

## Design Patterns Used

- **Singleton** this is used to create single instance of main class,=.
- **Factory** get diffrent factory Objects from factory dynamically
- **Observer** Allows event to observe events from log.
- **Builder** get the object dynamically.

-----------

Cliff Jumping at Tetebatu Waterfall
Gili Islands Snorkeling from Lombok
Surfing at Kuta Lombok
Kuta Lombok Bat Cave (Bangkang Cave)
Go White Water Rafting at Jangkok River
