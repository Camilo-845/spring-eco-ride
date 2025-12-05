package com.example.notification_service.mappers;

import org.mapstruct.Mapper;

import com.example.notification_service.dtos.response.ChannelResponse;
import com.example.notification_service.models.Channel;

@Mapper(componentModel = "spring")
public interface ChannelMapper {
  ChannelResponse toDto(Channel channel);
}
