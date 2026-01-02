package com.example.notification_service.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TemplateRenderTest {

  private TemplateRender templateRender;

  @BeforeEach
  void setUp() {
    templateRender = new TemplateRender(new ObjectMapper());
  }

  @Test
  void testRender_withMultiplePlaceholders() {
    String template = "Hello, {{name}}! Your order number is {{orderId}}.";
    String payload = "{\"name\": \"John Doe\", \"orderId\": 12345}";
    String expected = "Hello, John Doe! Your order number is 12345.";
    String actual = templateRender.render(template, payload);
    assertEquals(expected, actual);
  }

  @Test
  void testRender_withNoPlaceholders() {
    String template = "This is a static message.";
    String payload = "{\"name\": \"John Doe\"}";
    String expected = "This is a static message.";
    String actual = templateRender.render(template, payload);
    assertEquals(expected, actual);
  }

  @Test
  void testRender_withSomePlaceholdersNotPresentInPayload() {
    String template = "Hello, {{name}}! Your order number is {{orderId}}. Status: {{status}}";
    String payload = "{\"name\": \"Jane\", \"orderId\": 67890}";
    String expected = "Hello, Jane! Your order number is 67890. Status: {{status}}";
    String actual = templateRender.render(template, payload);
    assertEquals(expected, actual);
  }

  @Test
  void testRender_withInvalidJsonPayload() {
    String template = "Some template content.";
    String payload = "{invalid json}";
    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
      templateRender.render(template, payload);
    });
    assertEquals("Error renderizando la plantilla de notificación", thrown.getMessage());
  }
}
