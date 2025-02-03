package dik.difficult_range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double point) {
        return point >= from && point <= to;
    }

    public Range getIntersection(Range range) {
        if (to <= range.getFrom() || range.getTo() <= from) {
            return null;
        } else {
            return new Range(Math.max(from, range.getFrom()), Math.min(to, range.getTo()));
        }
    }

    public Range[] getUnion(Range range) {
        if (to < range.getFrom() || range.getTo() < from) {
            return new Range[]{this, range};
        } else {
            return new Range[]{new Range(Math.min(from, range.getFrom()), Math.max(to, range.getTo()))};
        }
    }

    public Range[] getDifference(Range range) {
        if (to < range.getFrom() || range.getTo() < from) {
            return new Range[]{this};
        } else if (from > range.getFrom() && to < range.getTo()) {
            return new Range[]{null};
        } else if (from < range.getFrom() && to > range.getTo()) {
            return new Range[]{new Range(from, range.getFrom()), new Range(range.getTo(), to)};
        } else if (from < range.getFrom()) {
            return new Range[]{new Range(from, range.getFrom())};
        } else {
            return new Range[]{new Range(range.getTo(), to)};
        }
    }

    @Override
    public String toString() {
        return String.format("[%f, %f]", from, to);
    }
}