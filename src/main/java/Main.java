import clock.Clock;
import clock.SetableClock;
import statistic.EventsStatistic;
import statistic.EventsStatisticImpl;

import java.time.Instant;

public class Main {

    public static void main(String[] args) {
        Clock clock = new SetableClock(Instant.now());
        EventsStatistic eventsStatistic = new EventsStatisticImpl(clock);

        eventsStatistic.incEvent("First");
        eventsStatistic.incEvent("First");
        eventsStatistic.incEvent("First");
        eventsStatistic.incEvent("First");
        eventsStatistic.incEvent("First");
        eventsStatistic.incEvent("Second");
        eventsStatistic.incEvent("Second");
        eventsStatistic.incEvent("Second");
//        ((clock.SetableClock) clock).setNow(Instant.now().plus(Duration.ofHours(1)).plus(Duration.ofMinutes(1)));
        eventsStatistic.printStatistic();
    }
}
