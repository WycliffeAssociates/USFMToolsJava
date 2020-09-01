package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Bold Marker
 */
public class BDMarker extends Marker {
    /**
     * Text that is bolded
     */
    public String text;

    @Override
    public String getIdentifier() {
        return "bd";
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
