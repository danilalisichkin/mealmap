package com.mealmap.productservice.service.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.mealmap.productservice.core.dto.filter.ProductFilter;
import com.mealmap.productservice.service.ElasticsearchQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.stereotype.Service;

import static com.mealmap.productservice.util.ElasticsearchQueryBuilder.addWildcardQuery;
import static com.mealmap.productservice.util.ElasticsearchQueryBuilder.addRangeFilter;
import static com.mealmap.productservice.util.ElasticsearchQueryBuilder.addTermsFilter;

@Service
@RequiredArgsConstructor
public class ElasticsearchQueryServiceImpl implements ElasticsearchQueryService {
    @Override
    public Query buildQueryForProducts(Pageable pageable, ProductFilter filter, String search) {
        BoolQuery.Builder boolQuery = QueryBuilders.bool();

        applyQueryByProductSearch(boolQuery, search);
        applyQueryByPrice(boolQuery, filter);
        applyQueryByWeight(boolQuery, filter);
        applyQueryByNutrients(boolQuery, filter);
        applyQueryByCategories(boolQuery, filter);

        return NativeQuery.builder()
                .withQuery(Query.of(q -> q
                        .bool(boolQuery.build())))
                .withPageable(pageable)
                .build()
                .getQuery();
    }

    @Override
    public Query buildQueryForCategories(Pageable pageable, String search) {
        BoolQuery.Builder boolQuery = QueryBuilders.bool();

        applyQueryByCategorySearch(boolQuery, search);

        return NativeQuery.builder()
                .withQuery(Query.of(q -> q
                        .bool(boolQuery.build())))
                .withPageable(pageable)
                .build()
                .getQuery();
    }

    private void applyQueryByProductSearch(BoolQuery.Builder query, String search) {
        addWildcardQuery(query, search, "name", "description", "categories.name", "categories.parent.name");
    }

    private void applyQueryByCategorySearch(BoolQuery.Builder query, String search) {
        addWildcardQuery(query, search, "name", "children.name", "parent.name");
    }

    private void applyQueryByPrice(BoolQuery.Builder query, ProductFilter filter) {
        addRangeFilter(query, "price", filter.getMinPrice(), filter.getMaxPrice());
    }

    private void applyQueryByWeight(BoolQuery.Builder query, ProductFilter filter) {
        addRangeFilter(query, "weight", filter.getMinWeight(), filter.getMaxWeight());
    }

    private void applyQueryByNutrients(BoolQuery.Builder query, ProductFilter filter) {
        addRangeFilter(query, "nutrients.calories", filter.getMinCalories(), filter.getMaxCalories());
        addRangeFilter(query, "nutrients.proteins", filter.getMinProteins(), filter.getMaxProteins());
        addRangeFilter(query, "nutrients.fats", filter.getMinFats(), filter.getMaxFats());
        addRangeFilter(query, "nutrients.carbs", filter.getMinCarbs(), filter.getMaxCarbs());
        addRangeFilter(query, "nutrients.fibers", filter.getMinFibers(), filter.getMaxFibers());
        addRangeFilter(query, "nutrients.sugars", filter.getMinSugars(), filter.getMaxSugars());
    }

    private void applyQueryByCategories(BoolQuery.Builder query, ProductFilter filter) {
        addTermsFilter(query, "categories.id", filter.getCategories());
    }
}
