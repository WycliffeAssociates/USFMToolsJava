package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * A descriptive title marker
 */
public class DMarker extends Marker {
    public String description;

    @Override
    public String getIdentifier() {
        return "d";
    }

    @Override
    public String preProcess(String input) {
        description = input.trim();
        return "";
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(
                Arrays.asList(
                        FMarker.class,
                        FEndMarker.class,
                        FEndMarker.class,
                        ITMarker.class,
                        ITEndMarker.class,
                        TextBlock.class
                )
        );
    }
}
