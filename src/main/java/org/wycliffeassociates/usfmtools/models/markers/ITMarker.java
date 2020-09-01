package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Italic marker
 */
public class ITMarker extends Marker {
    @Override
    public String getIdentifier() {
        return "it";
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
