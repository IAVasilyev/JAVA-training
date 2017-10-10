package numberQuestions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class AboutHotel {
    /*
        Given a hotel and guests. Each guest have a check in and check out time (timestamp).
        Find a maximum number of guests which were in hotel at the same time.
     */

    class Guest {
        long checkIn, checkOut;

        Guest(long checkIn, long checkOut) {
            this.checkIn = checkIn;
            this.checkOut = checkOut;
        }
    }

    @Test
    void maxNumberOfGuestsTest() {
        Collection<Guest> guests = new LinkedList<>();
        Assertions.assertEquals(0L, maxNumberOfGuests(guests));
        guests.add(new Guest(0, 1));
        Assertions.assertEquals(1L, maxNumberOfGuests(guests));
        guests.add(new Guest(0, 2));
        Assertions.assertEquals(2L, maxNumberOfGuests(guests));
        guests.add(new Guest(1, 3));
        Assertions.assertEquals(3L, maxNumberOfGuests(guests));
        guests.add(new Guest(2, 3));
        guests.add(new Guest(2, 3));
        Assertions.assertEquals(4L, maxNumberOfGuests(guests));
        for (int i = 0; i < 10; i++) {
            guests.add(new Guest(5, 5));
        }
        Assertions.assertEquals(10L, maxNumberOfGuests(guests));
    }

    private long maxNumberOfGuests(Collection<Guest> guests) {
        class Move implements Comparable<Move> {
            private final boolean checkIn;
            private final long time;

            private Move(boolean checkIn, long time) {
                this.checkIn = checkIn;
                this.time = time;
            }

            @Override
            public int compareTo(Move o) {
                int res = Long.compare(time, o.time);
                if (res == 0) res = Boolean.compare(o.checkIn, checkIn);
                return res;
            }
        }
        Queue<Move> inOutQueue = new PriorityQueue<>();
        guests.stream().flatMap(guest -> Stream.of(new Move(true, guest.checkIn), new Move(false, guest.checkOut)))
                .forEach(inOutQueue::offer);
        return LongStream.iterate(0, numberOfGuests -> numberOfGuests + (inOutQueue.poll().checkIn ? 1 : -1))
                .skip(1)
                .limit(guests.size() * 2)
                .max().orElse(0L);
    }
}
