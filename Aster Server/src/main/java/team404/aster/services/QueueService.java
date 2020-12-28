package team404.aster.services;

import java.util.List;

public interface QueueService {
    void sendToQueue(String queue, String message);
    void sendToQueueExchange(String exchangeName, String exchangeType, String message);
    void sendListToQueueExchange(String exchangeName, String exchangeType, List<String> messages);
}
