package com.fast.mybatisplus.architechturespringmybatisplus.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * 订单表分片的精确匹配算法： 区别于inline中的expression表达式，此方法更加灵活
 *
 * @author huangchangjin
 */
@Slf4j
public class OrderPreciseShardingTableAlgorithm implements PreciseShardingAlgorithm<Date> {

    @Override
    public String doSharding(final Collection<String> tableNames, final PreciseShardingValue<Date> shardingValue) {
        Date date = shardingValue.getValue();
        String suffix = ShardingUtils.getSuffixByYearMonth(date);
        for (String tableName : tableNames) {
            if (tableName.endsWith(suffix)) {
                return tableName;
            }
        }
        throw new IllegalArgumentException("未找到匹配的数据表");
    }
}
