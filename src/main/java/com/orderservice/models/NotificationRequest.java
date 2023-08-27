package com.orderservice.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationRequest {
  private Long customer_id;
  private String message;
}
