package com.mealmap.productservice.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "products")
public class ProductDocument {
    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Integer)
    private Integer price;

    @Field(type = FieldType.Integer)
    private Integer weight;

    @Field(type = FieldType.Nested, includeInParent = true)
    private NutrientDocument nutrients;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Boolean)
    private boolean isNew;

    @Field(type = FieldType.Keyword)
    private Integer supplierId;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<CategoryDocument> categories;
}
