package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Cross reference origin reference
 */
public class XOMarker extends Marker {
    public String originRef;

    @Override
    public String getIdentifier() {
        return "xo";
    }

    @Override
    public String preProcess(String input) {
        originRef = input.trim();
        return "";
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(Arrays.asList(TextBlock.class));
    }
}
