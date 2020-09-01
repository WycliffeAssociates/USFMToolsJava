package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

public class PMarker extends Marker {
    @Override
    public String getIdentifier() {
        return "p";
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
