package com.pm.notificationservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import employee.events.EmployeeEvent;

@Service
public class KafkaConsumer {

  private static final Logger log = LoggerFactory.getLogger(
      KafkaConsumer.class);

  @KafkaListener(topics="employee", groupId = "employee-service")
  public void consumeEvent(byte[] event) {
    try {
      EmployeeEvent employeeEvent = EmployeeEvent.parseFrom(event);
      // ... perform any business related to do notification

      log.info("Received Employee Event: [employeeId={},name={},email={}]",
              employeeEvent.getEmployeeId(),
              employeeEvent.getName(),
              employeeEvent.getEmail());
    } catch (InvalidProtocolBufferException e) {
      log.error("Error deserializing event {}", e.getMessage());
    }
  }
}
