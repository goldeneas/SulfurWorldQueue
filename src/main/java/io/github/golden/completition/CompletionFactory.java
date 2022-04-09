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

            if(!s.contains("/")) {
                // add a single option to that index
                c.addOption(i, s);
            } else {
                // the string contains a /, which means there are multiple options
                // for the same index
                c.addOptions(i, s.split("/"));
            }
        }

        components.add(c);
        return c;
    }

    public List<CompletionComponent> getComponents() {
        return components;
    }

}
