package io.github.golden.completition;

import java.util.ArrayList;
import java.util.List;

public class CompletionFactory {
    
    private ArrayList<CompletionComponent> components = new ArrayList<>();

    public CompletionComponent createComponent(String commandName, String... args) {
        // create the component
        CompletionComponent c = new CompletionComponent();
        c.setCommandName(commandName);

        // add the strings based on the loop index
        for(int i = 0; i < args.length; i++) {
            String s = args[i];

            if(!s.contains("/")) { c.addOption(s); }

            // the string contains a /, which means there are multiple options
            // for the same index
            // we use i + 1 becose the 0th arg is the subcommand
            /*
                        0           1      2    3     4        5
                /sulfur createqueue normal test lobby factions 32
            */
            c.addMultipleOptions(i + 1, s.split("/"));
        }

        components.add(c);
        return c;
    }

    public List<CompletionComponent> getComponents() {
        return components;
    }

}
