package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Intented Paragraph marker
 */
public class PIMarker extends Marker {
    public int depth = 1;

    @Override
    public String getIdentifier() {
        return "pi";
    }

    @Override
    public String preProcess(String input) {
        return tangible.StringHelper.trimStart(input);
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(Arrays.asList(VMarker.class, BMarker.class, SPMarker.class, TextBlock.class, FMarker.class, FEndMarker.class, LIMarker.class, QMarker.class));
    }
}
