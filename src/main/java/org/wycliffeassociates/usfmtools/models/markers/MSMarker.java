package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Major heading marker
 */
public class MSMarker extends Marker {
    public int weight = 1;
    public String heading;

    @Override
    public String getIdentifier() {
        return "ms";
    }

    @Override
    public String preProcess(String input) {
        heading = tangible.StringHelper.trimStart(input);
        return "";
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(Arrays.asList(MRMarker.class));
    }
}
