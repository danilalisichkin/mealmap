package com.mealmap.userservice.core.mapper;

import com.mealmap.userservice.core.dto.history.StatusHistoryCreatingDto;
import com.mealmap.userservice.core.dto.history.StatusHistoryDto;
import com.mealmap.userservice.entity.UserStatusHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserMapper.class})
public interface UserStatusHistoryMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "changedBy", source = "changedBy.id")
    StatusHistoryDto entityToDto(UserStatusHistory entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "changedBy", ignore = true)
    @Mapping(target = "eventAt", ignore = true)
    UserStatusHistory dtoToEntity(StatusHistoryCreatingDto dto);

    List<StatusHistoryDto> entityListToDtoList(List<UserStatusHistory> entityList);

    default Page<StatusHistoryDto> entityPageToDtoPage(Page<UserStatusHistory> entityPage) {
        return new PageImpl<>(
                entityListToDtoList(entityPage.getContent()),
                entityPage.getPageable(),
                entityPage.getTotalElements());
    }
}
