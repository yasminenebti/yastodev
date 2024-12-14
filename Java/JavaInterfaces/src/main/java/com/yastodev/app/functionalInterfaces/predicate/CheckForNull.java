package com.yastodev.app.functionalInterfaces.predicate;

import java.util.Objects;
import java.util.function.Predicate;

public class CheckForNull implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return s != null;
    }

    //PredicateExample checkForNull = (value) -> value != null;
    Predicate checkForNull = Objects::nonNull;  //method reference instead of Lambda

}
