package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Embedded text poetic line
 */
public class QMMarker extends Marker {
    public int depth = 1;

    @Override
    public String getIdentifier() {
        return "qm";
    }

    @Override
    public String preProcess(String input) {
        return tangible.StringHelper.trimStart(input);
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(Arrays.asList(TextBlock.class, FMarker.class, FEndMarker.class, TLMarker.class, TLEndMarker.class, WMarker.class, WEndMarker.class));
    }
}
