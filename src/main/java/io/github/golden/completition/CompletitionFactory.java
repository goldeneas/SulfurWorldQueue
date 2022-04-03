package io.github.golden.completition;

import java.util.List;

public class CompletitionFactory {
    
    private List<CompletionComponent> components;

    public CompletionComponent createComponent(String) {
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
    }

}
