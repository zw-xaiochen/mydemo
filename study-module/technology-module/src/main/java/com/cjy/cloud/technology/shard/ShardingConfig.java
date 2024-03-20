package com.cjy.cloud.technology.shard;

import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * 分片配置 = 数据源 + 分片规则（都可以是多个）
 * <p>分片规则 = 逻辑表映射 + 分片算法
 * <p>逻辑表映射 = 库映射 + 表映射（数据源.[真实表名] -> 逻辑表名） + 分表键
 * <p>分片算法（库映射和表映射用到） = 算法类型 + 算法参数（或自定义算法）
 */

@Configuration
public class ShardingConfig {

    /**
     * @see <a href="https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/configuration/built-in-algorithm/sharding/#%E6%97%B6%E9%97%B4%E8%8C%83%E5%9B%B4%E5%88%86%E7%89%87%E7%AE%97%E6%B3%95">时间范围分片算法</a>
     */
    @Bean(name = "shardingDataSource")
    public DataSource shardingDataSource(@Qualifier("druidDataSource") DataSource dataSource) throws SQLException {
        // 配置逻辑表映射
        Map<String, DataSource> dataSourceMap = Collections.singletonMap("ds0", dataSource);

        // 配置分片规则
        ShardingRuleConfiguration ruleConfig = new ShardingRuleConfiguration();

        ruleConfig.getTableRuleConfigs().add(getEnergyConsumptionTableRuleConfiguration());

        // 其他配置
        Properties properties = new Properties();
        properties.setProperty("datetime-pattern", "yyyy-MM-dd HH:mm:ss");
        properties.setProperty("datetime-lower", "2017-06-01 00:00:00");
        properties.setProperty("datetime-upper", "2027-05-31 23:59:59");
        properties.setProperty("sharding-suffix-pattern", "_yyyyMM");
        properties.setProperty("datetime-interval-amount", "1");
        properties.setProperty("datetime-interval-unit", "MONTHS");
        // 打开日志
        // properties.setProperty("sql.show", "true");
        return ShardingDataSourceFactory.createDataSource(dataSourceMap, ruleConfig, properties);
    }

    /**
     * 能源能耗
     */
    private TableRuleConfiguration getEnergyConsumptionTableRuleConfiguration() {
        TableRuleConfiguration alarmTableRuleConfiguration = new TableRuleConfiguration("ENERGY_CONSUMPTION_STATS_DAY",
            "ds0.ENERGY_CONSUMPTION_STATS_DAY_20230$->{6..9},"
                + "ds0.ENERGY_CONSUMPTION_STATS_DAY_20$->{23..31}0$->{1..9},"
                + "ds0.ENERGY_CONSUMPTION_STATS_DAY_20$->{23..31}1$->{0..2},"
                + "ds0.ENERGY_CONSUMPTION_STATS_DAY_20320$->{1..5}");
        alarmTableRuleConfiguration.setTableShardingStrategyConfig(
            new StandardShardingStrategyConfiguration("day", new DatePreciseShardingAlgorithm(),
                new DateRangePreciseShardingAlgorithm()));
        return alarmTableRuleConfiguration;
    }


    /**
     * 事务配置
     */
    @Bean
    public DataSourceTransactionManager shardingSphereTransactionManager(
        @Qualifier("shardingDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }





}
