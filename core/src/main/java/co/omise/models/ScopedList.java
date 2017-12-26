package co.omise.models;

import java.time.ZonedDateTime;

public class ScopedList<T extends OmiseObject> extends OmiseList<T> {
    private ZonedDateTime from;
    private ZonedDateTime to;
    private int offset;
    private int limit;

    public ZonedDateTime getFrom() {
        return from;
    }

    public void setFrom(ZonedDateTime from) {
        this.from = from;
    }

    public ZonedDateTime getTo() {
        return to;
    }

    public void setTo(ZonedDateTime to) {
        this.to = to;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
