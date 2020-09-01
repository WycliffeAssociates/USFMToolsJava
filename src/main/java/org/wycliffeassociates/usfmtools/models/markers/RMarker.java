package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Parallel passage reference(s)
 */
public class RMarker extends Marker {
    @Override
    public String getIdentifier() {
        return "r";
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
