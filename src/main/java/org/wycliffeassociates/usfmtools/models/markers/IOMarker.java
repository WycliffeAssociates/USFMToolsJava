package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Introduction outline entry
 */
public class IOMarker extends Marker {
    public int depth = 1;

    @Override
    public String getIdentifier() {
        return "io";
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(Arrays.asList(TextBlock.class, IORMarker.class, IOREndMarker.class));
    }
}
