package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Superscript text. Typically for use in critical edition footnotes
 */
public class SUPMarker extends Marker {
    @Override
    public String getIdentifier() {
        return "sup";
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
