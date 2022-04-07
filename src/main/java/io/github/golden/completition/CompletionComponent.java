package io.github.golden.completition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CompletionComponent {
    
    private String commandName;
    private int currentUnpopulatedIndex;
    private HashMap<Integer, List<String>> optionsMap = new HashMap<>();

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public void addOption(String option) {
        // add the new option to an "unpopulated" position
        // and then keep track of the change by increasing the index
        optionsMap.put(currentUnpopulatedIndex, Arrays.asList(option));
        currentUnpopulatedIndex++;
    }

    public void addMultipleOptions(int index, String... options) {
        // get the current options for that value
        // if there isn't a list for that value, create a new empty list 
        // to avoid NPE
        List<String> list = optionsMap.get(index);
        if(list == null) { list = new ArrayList<>(); }
        
        // add the new values to the collection
        Collections.addAll(list, options);

        // add the new collection to the map
        optionsMap.put(index, list);
    }

    public List<String> getOptions(int index) {
        // todo: remove this debug output
        for(String s : optionsMap.get(index)) {
            System.out.println(index + " " + s);
        }
        return optionsMap.get(index);
    }

    public String getCommandName() {
        return this.commandName;
    }

}
