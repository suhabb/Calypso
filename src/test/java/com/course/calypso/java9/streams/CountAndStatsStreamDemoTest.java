package com.course.calypso.java9.streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CountAndStatsStreamDemoTest {

    private CountAndStatsStreamDemo  demo = new CountAndStatsStreamDemo();

    @Test
    public void given_array_int_return_count()  {
        assertEquals(9, demo.countStream());
    }

    @Test
    public void given_array_int_return_count_stream()  {
        assertEquals(Long.valueOf(9), demo.countStreamCollect());
    }

    @Test
    public void given_array_int_return_count_stream_partition_by()  {
        Map theStreamIsPartitionBy = demo.countUsingPartitionBy(Arrays.asList("The","stream", "partition by","done"));
        assertEquals(Long.valueOf(3), theStreamIsPartitionBy.get(Boolean.TRUE));
        assertEquals(Long.valueOf(1), theStreamIsPartitionBy.get(Boolean.FALSE));
    }

    @Test
    public void given_int_stream_return_statistics()  {
        IntSummaryStatistics intSummaryStatistics = demo.summaryStatisticsStream();
        assertEquals( 500,intSummaryStatistics.getMax());
        assertEquals(500,intSummaryStatistics.getCount());
        assertEquals(125250,intSummaryStatistics.getSum());
        assertEquals(1,intSummaryStatistics.getMin());
        assertEquals(Double.valueOf(250.5).doubleValue(),intSummaryStatistics.getAverage(),0.01);

    }
}
