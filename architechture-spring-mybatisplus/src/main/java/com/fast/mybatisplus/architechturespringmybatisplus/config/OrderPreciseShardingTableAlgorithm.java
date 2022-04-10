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
        log.info("tableNames:{}\t shardingValue:{}", tableNames, shardingValue);
        StringBuilder sb = new StringBuilder();
        //获取设置的虚拟表名称，这里获取到的logicTableName=t_order
        String logicTableName = shardingValue.getLogicTableName();
        Date value = shardingValue.getValue();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(value);                    //放入Date类型数据

        //获取年份
        String year = calendar.get(Calendar.YEAR) + "";
        String monthValue = calendar.get(Calendar.MONTH)+1 < 10 ? "0" +  (calendar.get(Calendar.MONTH)+1) :  calendar.get(Calendar.MONTH)+1 + "";

        String yearMonth = year + monthValue;
        //拼接实际的表名称: t_order_202007
        sb.append(logicTableName)
                .append("_")
                .append(yearMonth);
        if (tableNames.contains(sb.toString())) {
            return sb.toString();
        } else {
            log.info("精确分片策略：没找到与分片键匹配的表名! {} : {} = {}", shardingValue.getLogicTableName(), shardingValue.getColumnName(), shardingValue.getValue());
            throw new UnsupportedOperationException();
        }
    }
}
