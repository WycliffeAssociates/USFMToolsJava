package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * A Poetry Marker
 */
public class QMarker extends Marker {
    public int depth = 1;
    public String Text;

    @Override
    public String getIdentifier() {
        return "q";
    }

    @Override
    public String preProcess(String input) {
        return tangible.StringHelper.trimStart(input);
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(Arrays.asList(BMarker.class, QSMarker.class, QSEndMarker.class, TextBlock.class, FMarker.class, FEndMarker.class, TLMarker.class, TLEndMarker.class, WMarker.class, WEndMarker.class, VMarker.class));
    }

    @Override
    public boolean tryInsert(Marker input) {
        if (input instanceof VMarker && contents.stream().anyMatch(m -> m instanceof VMarker)) {
            return false;
        }

        return super.tryInsert(input);
    }
}
