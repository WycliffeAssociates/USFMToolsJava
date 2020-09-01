package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

public class FTMarker extends Marker {
    @Override
    public String getIdentifier() {
        return "ft";
    }

    @Override
    public String preProcess(String input) {
        return tangible.StringHelper.trimStart(input);
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(Arrays.asList(FQAMarker.class, FQAEndMarker.class, FQMarker.class, TLMarker.class, TLEndMarker.class, WMarker.class, WEndMarker.class, TextBlock.class));
    }
}
