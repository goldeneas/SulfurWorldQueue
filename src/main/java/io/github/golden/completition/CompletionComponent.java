package io.github.golden.completition;

import java.util.ArrayList;
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
        add(index, option);
    }

    public void addOptions(int index, String... options) {
        add(index, options);
    }

    private void add(int index, String... options) {
        // get the current options for that value
        List<String> list = new ArrayList<>();

        // get the old values
        List<String> old = optionsMap.get(index);
        if(old != null) { Collections.copy(list, old); }
        
        // add the new values
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
