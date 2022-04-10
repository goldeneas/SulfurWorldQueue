package io.github.golden.command.completion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CompletionComponent {
    
    private String commandName;
    private HashMap<Integer, List<String>> optionsMap = new HashMap<>();

    public CompletionComponent(String commandName, String... options) {
        this.commandName = commandName;

        this.addOptions(options);
    }

    private void addOptions(String... options) {
        for(int i = 0; i < options.length; i++) {
            // currentOptions can be
            // - a single string, if we are passing one option for this specific index
            // - an array of strings, if the options at this index are multiple
            String[] currentOptions = options[i].split("/");

            // get the current options for that value
            List<String> list = new ArrayList<>();

            // get the old values
            List<String> old = optionsMap.get(i);
            if(old != null) { Collections.copy(list, old); }

            // add the new values
            Collections.addAll(list, currentOptions);
        
            // add the new collection to the map
            optionsMap.put(i, list);
        }
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public List<String> getOptions(int index) {
        return optionsMap.get(index);
    }

    public String getCommandName() {
        return this.commandName;
    }

}
