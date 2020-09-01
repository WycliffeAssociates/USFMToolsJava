package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * A Poetry Marker within Introduction
 */
public class IQMarker extends Marker {
    public int depth = 1;
    public String text;

    @Override
    public String getIdentifier() {
        return "iq";
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
