package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * List Entry Marker within Introduction
 */
public class ILIMarker extends Marker {
    public int depth = 1;

    @Override
    public String getIdentifier() {
        return "ili";
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
