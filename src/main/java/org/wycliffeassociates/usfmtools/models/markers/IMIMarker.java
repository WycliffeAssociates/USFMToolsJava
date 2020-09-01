package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Indented introduction flush left (margin) paragraph
 */
public class IMIMarker extends Marker {
    @Override
    public String getIdentifier() {
        return "imi";
    }

    @Override
    public String preProcess(String input) {
        return input.trim();
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(Arrays.asList(TextBlock.class, BKMarker.class, BKEndMarker.class, BDMarker.class, BDEndMarker.class, ITMarker.class, ITEndMarker.class));
    }
}
