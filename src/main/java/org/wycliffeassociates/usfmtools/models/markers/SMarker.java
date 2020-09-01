package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Section marker
 */
public class SMarker extends Marker {
    public int weight = 1;
    public String text;

    @Override
    public String getIdentifier() {
        return "s";
    }

    @Override
    public String preProcess(String input) {
        text = tangible.StringHelper.trimStart(input);
        return "";
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(Arrays.asList(RMarker.class, FMarker.class, FEndMarker.class));
    }
}
