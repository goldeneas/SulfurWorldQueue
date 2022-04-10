package io.github.golden.queue;

public class QueueFactory {

    private QueueDeposit queueDeposit;
    private QueueConfig queueConfig = QueueConfig.getConfig();

    public QueueFactory(QueueDeposit queueDeposit) {
        this.queueDeposit = queueDeposit;
    }
    
    // crate a new queue; returning it doesn't have a real function right now
    public QueueResult createQueue(QueueType type, String queueName, String lobbyName, String destinationName, int maxPlayers) {
        if(type == QueueType.NORMAL) {
            // create, store, save to config and return the new normal queue
            NormalQueue tmpQueue = new NormalQueue(queueName, lobbyName, destinationName, maxPlayers);

            // if the queue is ok, then save it and load it in the deposit
            QueueResult status = tmpQueue.getStatus();
            if(status == QueueResult.OK) {
                queueDeposit.store(tmpQueue);
                queueConfig.save(tmpQueue);
            }

            return status;
        } else {
            return QueueResult.NOT_IMPLEMENTED;
        }
    }
    
}
