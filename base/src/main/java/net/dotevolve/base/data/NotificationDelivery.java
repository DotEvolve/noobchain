package net.dotevolve.base.data;


import lombok.Data;

@Data
public class NotificationDelivery {
    String time;
    NotificationDeliveryEnum status;
    String serviceId;
    String error;
}
