package com.distributed.customer;

import com.distributed.amqp.RabbitMQMessageProducer;
import com.distributed.clients.fraud.FraudCheckResponse;
import com.distributed.clients.fraud.FraudClient;
import com.distributed.clients.notification.NotificationRegisterRequest;
import com.distributed.clients.notification.NotificationType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        } else {
            NotificationRegisterRequest notificationRegisterRequest = new NotificationRegisterRequest(
                    customer.getId(),
                    customer.getEmail(),
                    "Distributed App",
                    NotificationType.USER_CREATED,
                    String.format("User with email: %s was successfully created.", customer.getEmail())
            );
            rabbitMQMessageProducer.publish(
                    notificationRegisterRequest,
                    "internal.exchange",
                    "internal.notification.routing-key"
                    );
        }

    }
}
