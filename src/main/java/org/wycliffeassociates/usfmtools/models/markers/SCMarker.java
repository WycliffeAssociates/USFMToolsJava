package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Small-Cap Letter
 */
public class SCMarker extends Marker {
    @Override
    public String getIdentifier() {
        return "sc";
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
