package com.mealmap.productservice.document;

import com.mealmap.productservice.core.dto.category.CategoryShortInfo;
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

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Setting(settingPath = "elasticsearch/settings/russian-morphology.json")
@Document(indexName = "categories", writeTypeHint = WriteTypeHint.FALSE)
public class CategoryDoc {
    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "russian_morphology")
    private String name;

    @Field(type = FieldType.Object, analyzer = "russian_morphology")
    private CategoryShortInfo parent;

    @Field(type = FieldType.Object)
    private List<CategoryShortInfo> children;
}
