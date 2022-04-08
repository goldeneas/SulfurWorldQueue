package io.github.golden.completition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CompletionComponent {
    
    private String commandName;
    private HashMap<Integer, List<String>> optionsMap = new HashMap<>();

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public void addOption(int index, String option) {
        optionsMap.put(index, Arrays.asList(option));
    }

    public void addMultipleOptions(int index, String... options) {
        // get the current options for that value
        // List<String> list = new ArrayList<>(optionsMap.get(index));
        List<String> list = new ArrayList<>();
        
        // add the new values to the collection
        Collections.addAll(list, options);

        // add the new collection to the map
        optionsMap.put(index, list);
    }

    public List<String> getOptions(int index) {
        return optionsMap.get(index);
    }

    public String getCommandName() {
        return this.commandName;
    }

}
