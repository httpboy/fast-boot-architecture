package com.fast.mybatisplus.architechturespringmybatisplus.config;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Range;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 订单表分片的范围匹配算法： 区别于inline中的expression表达式，此方法更加灵活
 *
 * @author huangchangjin
 */
@Slf4j
public class OrderRangeShardingTableAlgorithm implements RangeShardingAlgorithm<Integer> {

    /**
     * 实现between and查询
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Integer> rangeShardingValue) {
        log.info("availableTargetNames:{}\t shardingValue:{}", availableTargetNames, rangeShardingValue);

        Collection<String> collect = new ArrayList<>();
        Range<Integer> valueRange = rangeShardingValue.getValueRange();

        log.info("valueRange:{}", valueRange);
        Integer lowerEndpoint = null;
        try {
            lowerEndpoint = valueRange.lowerEndpoint();
        } catch (Exception e) {
            log.error("lowerEndpoint:{}", "最小边界值错误");
        }
        Integer upperEndpoint = null;
        try {
            upperEndpoint = valueRange.upperEndpoint();
        } catch (Exception e) {
            log.error("upperEndpoint:{}", "最大边界值错误");
        }
        log.info("lowerEndpoint:{} \t upperEndpoint:{}", lowerEndpoint, upperEndpoint);
        if (lowerEndpoint == null && upperEndpoint != null) {
            lowerEndpoint = upperEndpoint - 3;
        }
        if (upperEndpoint == null && lowerEndpoint != null) {
            upperEndpoint = lowerEndpoint + 3;
        }

        for (Integer i = lowerEndpoint; i <= upperEndpoint; i++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(String.valueOf(i))) {
                    collect.add(each);
                }
            }
        }

        log.info("collect:{}", JSON.toJSONString(collect));

        return collect;
    }
}
