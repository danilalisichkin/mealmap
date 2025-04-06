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

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "nutrients", writeTypeHint = WriteTypeHint.FALSE)
public class NutrientDoc {
    @Id
    private Long id;

    @Field(type = FieldType.Integer)
    private Integer calories;

    @Field(type = FieldType.Integer)
    private Integer proteins;

    @Field(type = FieldType.Integer)
    private Integer fats;

    @Field(type = FieldType.Integer)
    private Integer carbs;

    @Field(type = FieldType.Integer)
    private Integer fibers;

    @Field(type = FieldType.Integer)
    private Integer sugars;
}
