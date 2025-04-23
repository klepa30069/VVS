package ru.hpclab.hl.module1.metrics;

import java.util.LongSummaryStatistics;


public class TimingStats {
    private final LongSummaryStatistics stats = new LongSummaryStatistics();

    public synchronized void addRecord(long duration) {
        stats.accept(duration);
    }

    public String getSummary() {
        return String.format(
            "count=%d, avg=%.2fms, min=%dms, max=%dms",
            stats.getCount(),
            stats.getAverage(),
            stats.getMin(),
            stats.getMax()
        );
    }

    public synchronized long getCount() {
        return stats.getCount();
    }
}
