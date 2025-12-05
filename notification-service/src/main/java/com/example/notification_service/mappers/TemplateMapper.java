package com.example.notification_service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.notification_service.dtos.request.TemplateRequest;
import com.example.notification_service.dtos.response.TemplateResponse;
import com.example.notification_service.models.Template;

@Mapper(componentModel = "spring")
public interface TemplateMapper {
  @Mapping(target = "id", ignore = true)
  Template toEntity(TemplateRequest templateRequest);

  TemplateResponse toDto(Template template);
}
