package com.cjy.cloud.technology.shard;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;

public class DatePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Date> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> preciseShardingValue) {
        String targetTable = preciseShardingValue.getLogicTableName().toUpperCase() + "_"
            + preciseShardingValue.getValue().toInstant().atZone(ZoneId.systemDefault()).format(FORMATTER);
        if (collection.contains(targetTable)) {
            return targetTable;
        }
        return collection.toArray(new String[] {})[0];
    }
}
