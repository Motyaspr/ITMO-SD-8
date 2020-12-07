package statistic;

import clock.Clock;
import clock.SetableClock;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

public class EventsStatisticTest {
    private static final double EPS = 1e-8;

    @Test
    public void statisticTest() {
        Clock clock = new SetableClock(Instant.now());
        EventsStatistic eventsStatistic = new EventsStatisticImpl(clock);

        for (int i = 0; i < 120; i++) {
            eventsStatistic.incEvent("First Event");
        }
        for (int i = 0; i < 10; i++) {
            eventsStatistic.incEvent("Second Event");
        }
        for (int i = 0; i < 5; i++) {
            eventsStatistic.incEvent("Third Event");
        }

        Assert.assertTrue(Math.abs(eventsStatistic.getEventStatisticByName("First Event") - 120 / 60.) <= EPS);
        Assert.assertTrue(Math.abs(eventsStatistic.getEventStatisticByName("Second Event") - 10 / 60.) <= EPS);
        Assert.assertTrue(Math.abs(eventsStatistic.getEventStatisticByName("Third Event") - 5 / 60.) <= EPS);

        ((clock.SetableClock) clock).setNow(Instant.now().plus(Duration.ofHours(1)).plus(Duration.ofMinutes(1)));

        for (int i = 0; i < 8; i++) {
            eventsStatistic.incEvent("Third Event");
        }

        Assert.assertEquals(0, eventsStatistic.getEventStatisticByName("First Event"), EPS);
        Assert.assertEquals(0, eventsStatistic.getEventStatisticByName("Second Event"), EPS);
        Assert.assertTrue(Math.abs(eventsStatistic.getEventStatisticByName("Third Event") - 8 / 60.) <= EPS);
    }
}