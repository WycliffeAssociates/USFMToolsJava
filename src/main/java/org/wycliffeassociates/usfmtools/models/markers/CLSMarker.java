package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Closure of an epistle/letter
 */
public class CLSMarker extends Marker {
    @Override
    public String getIdentifier() {
        return "cls";
    }

    @Override
    public String preProcess(String input) {
        return input.trim();
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(Arrays.asList(TextBlock.class));
    }
}
