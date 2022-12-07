package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Table Cell Marker
 */
public class TCMarker extends Marker {
    public int columnPosition = 1;

    @Override
    public String getIdentifier() {
        return "tc";
    }

    @Override
    public String preProcess(String input) {
        return input.trim();
    }

    @Override
    public ArrayList<Class> getAllowedContents() {
        return new ArrayList(Arrays.asList(TextBlock.class));
    }
}
