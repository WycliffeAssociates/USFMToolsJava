package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Inline quotation reference(s)
 */
public class RQMarker extends Marker {
    @Override
    public String getIdentifier() {
        return "rq";
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
