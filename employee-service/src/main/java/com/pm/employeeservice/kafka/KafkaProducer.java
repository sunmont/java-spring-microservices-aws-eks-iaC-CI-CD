package com.pm.employeeservice.kafka;
import com.pm.employeeservice.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import employee.events.EmployeeEvent;

@Service
public class KafkaProducer {

  private static final Logger log = LoggerFactory.getLogger(
      KafkaProducer.class);
  private final KafkaTemplate<String, byte[]> kafkaTemplate;

  public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendEvent(Employee patient) {
    EmployeeEvent event = EmployeeEvent.newBuilder()
        .setPatientId(patient.getId().toString())
        .setName(patient.getName())
        .setEmail(patient.getEmail())
        .setEventType("PATIENT_CREATED")
        .build();

    try {
      kafkaTemplate.send("patient", event.toByteArray());
    } catch (Exception e) {
      log.error("Error sending PatientCreated event: {}", event);
    }
  }
}
