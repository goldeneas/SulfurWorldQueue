package io.github.golden.queue;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.block.implementation.Section;
import io.github.golden.Sulfur;

public class QueueConfig {

    private static QueueConfig queueConfig;
    private static final String QUEUES_PATH = "queues";

    private static Sulfur sulfur = Sulfur.getSulfur();
    private static YamlDocument queueFile;

    private QueueConfig() {}

    public static QueueConfig getConfig() {
        if(queueConfig == null) {
            queueConfig = new QueueConfig();
        }

        if(queueFile == null) {
            // load the custom config if it exists, or create it
            try {
                queueFile = YamlDocument.create(new File(sulfur.getDataFolder(), "queues.yml"), sulfur.getResource("queues.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return queueConfig;
    }

    private void saveFile() {
        try {
            queueFile.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(BaseQueue baseQueue) {
        // write the queue to config
        String queueName = baseQueue.getQueueName();

        addQueueName(queueName);
        addQueueProperty(queueName, "lobby", baseQueue.getLobbyName());
        addQueueProperty(queueName, "destination", baseQueue.getDestinationName());
        addQueueProperty(queueName, "max_players", baseQueue.getMaxPlayers());
    }

    public void addQueueName(String queueName) {
        queueFile.set(QUEUES_PATH + "." + queueName, "");
        saveFile();
    }

    public void addQueueProperty(String queueName, String propertyName, Object value) {
        queueFile.set(QUEUES_PATH + "." + queueName + "." + propertyName, value);
        saveFile();
    }

    public boolean removeQueue(String queueName) {
        boolean result = queueFile.remove(QUEUES_PATH + "." + queueName);
        saveFile();

        return result;
    }

    public Set<BaseQueue> getQueues() {
        Set<BaseQueue> resultQueues = new HashSet<>();
        // the section is a subsection of a part of the config (not a list of objects)
        Section queueSection = queueFile.getSection(QUEUES_PATH);
        
        // if the config is empty (because it has just been created/no queues have been created yet)
        // if that's the case, then we don't want to load anything
        if(queueSection == null) { return new HashSet<>();}

        // otherwise, get the queues' names
        Set<String> tmpQueues = queueSection.getRoutesAsStrings(false);

        for(String queueName : tmpQueues) {
            int tmpMaxPlayers       = getMaxPlayers(queueName);
            String tmpLobby         = getLobbyName(queueName);
            String tmpDestination   = getDestinationName(queueName);

            resultQueues.add(new NormalQueue(queueName, tmpLobby, tmpDestination, tmpMaxPlayers));
        }

        return resultQueues;
    }

    public String getLobbyName(String queueName) {
        return queueFile.getString(QUEUES_PATH + "." + queueName + "." + "lobby");
    }

    public String getDestinationName(String queueName) {
        return queueFile.getString(QUEUES_PATH + "." + queueName + "." + "destination");
    }

    public int getMaxPlayers(String queueName) {
        return queueFile.getInt(QUEUES_PATH + "." + queueName + "." + "max_players");
    }
    
}
