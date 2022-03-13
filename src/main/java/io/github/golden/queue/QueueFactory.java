package io.github.golden.queue;

public class QueueFactory {

    private QueueDeposit queueDeposit;

    public QueueFactory() {
        queueDeposit = new QueueDeposit();
    }
    
    // crate a new queue; returning it doesn't have a real function right now
    public BaseQueue createQueue(QueueType type, String queueName, String lobbyName, String destinationName, int maxPlayers) {
        if(type == QueueType.NORMAL) {
            // create, store and return the new normal queue
            NormalQueue tmpQueue = new NormalQueue(queueName, lobbyName, destinationName, maxPlayers);
            return queueDeposit.store(tmpQueue);
        } else {
            throw new UnsupportedOperationException("Not supported yet");
        }
    }

    // overload: lets us use strings insted of ints as argument for max players
    public BaseQueue createQueue(QueueType type, String queueName, String lobbyName, String destinationName, String maxPlayers) {
        int parsedMaxPlayers = Integer.parseInt(maxPlayers);
        return createQueue(type, queueName, lobbyName, destinationName, parsedMaxPlayers);
    }

    public QueueDeposit getDeposit() {
        return queueDeposit;
    }
    
}
