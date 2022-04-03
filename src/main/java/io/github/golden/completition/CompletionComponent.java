package io.github.golden.completition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CompletionComponent {
    
    private String commandName;
    private ArrayList<String> singleOptionList = new ArrayList<>();
    private HashMap<Integer, String[]> multipleOptionsMap = new HashMap<>();

    public CompletionComponent setCommandName(String commandName) {
        this.commandName = commandName;
        return this;
    }

    public CompletionComponent build(Object... args) {
        for(int i = 0; i < args.length; i++) {
            Object o = args[i];

            if(o instanceof String[] l) {
                addOptions(l); 
            }

            if(o instanceof String s) {
                if(!s.contains("/")) { addOption(s); }

                // the string contains a /, which means there are multiple options
                // for the same index
                addMultipleOptions(i, s.split("/"));
            }
        }

        return this;
    }

    public void addOption(String option) {
        singleOptionList.add(option);
    }

    public void addOptions(String... options) {
        Collections.addAll(singleOptionList, options);
    }

    public void addMultipleOptions(int index, String... options) {
        multipleOptionsMap.put(index, options);
    }

    public List<String> getOptions(int index) {
        ArrayList<String> finalList = new ArrayList<>();

        // get all the strings from the multiple options map
        // and put them in the final list
        Collections.addAll(finalList, multipleOptionsMap.get(index));

        // get the string from the single option list
        // and put them in the final list
        finalList.add(singleOptionList.get(index));

        // return the options
        return finalList;
    }

    public String getCommandName() {
        return this.commandName;
    }

}
