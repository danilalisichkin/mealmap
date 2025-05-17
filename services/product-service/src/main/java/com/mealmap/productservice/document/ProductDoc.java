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
import org.springframework.data.elasticsearch.annotations.Setting;
import org.springframework.data.elasticsearch.annotations.WriteTypeHint;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Setting(settingPath = "elasticsearch/settings/russian-morphology.json")
@Document(indexName = "products", writeTypeHint = WriteTypeHint.FALSE)
public class ProductDoc {
    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "russian_morphology", fielddata = true)
    private String name;

    @Field(type = FieldType.Keyword)
    private String imageUrl;

    @Field(type = FieldType.Integer)
    private Integer price;

    @Field(type = FieldType.Integer)
    private Integer weight;

    @Field(type = FieldType.Nested, includeInParent = true)
    private NutrientDoc nutrients;

    @Field(type = FieldType.Text, analyzer = "russian_morphology")
    private String description;

    @Field(type = FieldType.Date)
    private LocalDate createdAt;

    @Field(type = FieldType.Boolean)
    private Boolean isNew;

    @Field(type = FieldType.Keyword)
    private Integer supplierId;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<CategoryDoc> categories;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<AllergenDoc> allergens;
}
