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
import org.springframework.data.elasticsearch.annotations.WriteTypeHint;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "products", writeTypeHint = WriteTypeHint.FALSE)
public class ProductDoc {
    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Integer)
    private Integer price;

    @Field(type = FieldType.Integer)
    private Integer weight;

    @Field(type = FieldType.Nested, includeInParent = true)
    private NutrientDoc nutrients;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Boolean)
    private Boolean isNew;

    @Field(type = FieldType.Keyword)
    private Integer supplierId;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<CategoryDoc> categories;
}
