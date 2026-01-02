package com.example.notification_service.utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TemplateRender {
  private final ObjectMapper objectMapper;

  public TemplateRender(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public String render(String templateContent, String payload) {
    try {
      Map<String, Object> data = objectMapper.readValue(payload, new TypeReference<>() {
      });

      Iterator<Map.Entry<String, Object>> iterator = data.entrySet().iterator();

      while (iterator.hasNext()) {
        Map.Entry<String, Object> entry = iterator.next();
        String placeholder = "{{" + entry.getKey() + "}}";
        templateContent = templateContent.replace(placeholder, entry.getValue().toString());
      }

      return templateContent;

    } catch (IOException e) {
      throw new RuntimeException("Error renderizando la plantilla de notificación", e);
    }
  }
}
