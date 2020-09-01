package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * A "marker" for a table block. This exists so that we can handle table data
 */
public class TableBlock extends Marker {
    @Override
    public String getIdentifier() {
        return "";
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(Arrays.asList(TRMarker.class));
    }
}
