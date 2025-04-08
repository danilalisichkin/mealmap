package com.mealmap.starters.paginationstarter.autoconfigure;

import com.mealmap.starters.paginationstarter.mapper.PageMapper;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class PaginationAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public PageMapper pageMapper() {
        return new PageMapper();
    }
}
