package com.mealmap.productservice.util;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.json.JsonData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ElasticsearchQueryBuilder {
    public static void addMultiMatchQuery(BoolQuery.Builder query, String search, String field, String... fields) {
        if (search != null && !search.isBlank()) {
            query.must(Query.of(q -> q
                    .multiMatch(mm -> mm
                            .fields(field, fields)
                            .query(search)
                    )));
        }
    }

    public static void addRangeFilter(BoolQuery.Builder query, String field, Number min, Number max) {
        if (min != null || max != null) {
            query.filter(Query.of(q -> q
                    .range(r -> r
                            .field(field)
                            .gte(min != null ? JsonData.of(min) : null)
                            .lte(max != null ? JsonData.of(max) : null)
                    )));
        }
    }

    public static void addTermsFilter(BoolQuery.Builder query, String field, Collection<?> values) {
        if (values != null && !values.isEmpty()) {
            List<JsonData> termsAsList = values.stream()
                    .map(JsonData::of)
                    .toList();

            query.filter(Query.of(q -> q
                    .terms(t -> t
                            .field(field)
                            .terms(tv -> tv
                                    .value(termsAsList.stream()
                                            .map(FieldValue::of)
                                            .toList()))
                    )));
        }
    }
}
