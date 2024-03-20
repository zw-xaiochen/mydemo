package com.cjy.cloud.technology.shard;

import com.google.common.collect.Range;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DateRangePreciseShardingAlgorithm implements RangeShardingAlgorithm<Date> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");

    @Override
    public Collection<String> doSharding(Collection<String> allTables, RangeShardingValue<Date> rangeShardingValue) {

        Range<Date> valueRange = rangeShardingValue.getValueRange();
        ZonedDateTime lower = valueRange.lowerEndpoint().toInstant().atZone(ZoneId.systemDefault());
        ZonedDateTime upper = valueRange.upperEndpoint().toInstant().atZone(ZoneId.systemDefault());

        Set<String> targetTable = new HashSet<>();
        targetTable.add(rangeShardingValue.getLogicTableName().toUpperCase() + "_" + lower.format(FORMATTER));
        targetTable.add(rangeShardingValue.getLogicTableName().toUpperCase() + "_" + upper.format(FORMATTER));
        // 转换为时间戳
        // 计算两个日期的差值  补数据
        LocalDate localStartTime = LocalDateTime.ofEpochSecond(lower.toEpochSecond(), 0, lower.getOffset()).toLocalDate();
        LocalDate localEndTime = LocalDateTime.ofEpochSecond(upper.toEpochSecond(), 0, upper.getOffset()).toLocalDate();
        Period period = Period.between(localStartTime, localEndTime);
        // 获取两个日期相差的月份
        int month = period.getMonths();
        int startMonth = localStartTime.getMonthValue();
        for (int index = 0; index < month; index++) {
            LocalDate localDate;
            if (startMonth + 1 > 12) {
                startMonth = 1;
                localDate =
                    localStartTime.withYear(localStartTime.getYear() + 1).withMonth(startMonth).withDayOfMonth(1);
            } else {
                localDate = localStartTime.withMonth(startMonth + 1).withDayOfMonth(1);
            }
            targetTable.add(rangeShardingValue.getLogicTableName().toUpperCase() + "_" + localDate.format(FORMATTER));
        }
        List<String> collect = new ArrayList<>();
        for (String each : allTables) {
            if (targetTable.contains(each)) {
                collect.add(each);
            }
        }
        // 如果没有找到表,则返回第一个
        if (CollectionUtils.isEmpty(collect)) {
            collect.add(allTables.toArray(new String[] {})[0]);
        }
        return collect;
    }
}
