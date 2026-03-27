package tacos.kitchen.messaging.kafka.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import tacos.Order;
import tacos.TacoOrder;
import tacos.kitchen.KitchenUI;

public class OrderListener {

    private KitchenUI ui;

    @Autowired
    public OrderListener(KitchenUi ui) {
        this.ui = ui;
    }

    @KafkaListener(topics = "tacocloud.orders.tocpic")
    public void handle(TacoOrder order) {
        ui.displayOrder(order);
    }
}
