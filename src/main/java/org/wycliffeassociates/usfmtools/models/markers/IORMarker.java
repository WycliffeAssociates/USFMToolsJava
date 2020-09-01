package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Introduction outline reference range
 */
public class IORMarker extends Marker {
    @Override
    public String getIdentifier() {
        return "ior";
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
