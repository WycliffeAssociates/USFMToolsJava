package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Introduction flush left (margin) quote from text paragraph
 */
public class IMQMarker extends Marker {
    @Override
    public String getIdentifier() {
        return "imq";
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
