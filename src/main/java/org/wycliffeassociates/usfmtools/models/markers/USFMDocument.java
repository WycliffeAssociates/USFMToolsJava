package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

public class USFMDocument extends Marker {
    public USFMDocument() {
        contents = new ArrayList<Marker>();
    }

    @Override
    public String getIdentifier() {
        return "";
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(Arrays.asList(HMarker.class, IDEMarker.class, IDMarker.class, IBMarker.class, IQMarker.class, ILIMarker.class, IOTMarker.class, IOMarker.class, STSMarker.class, USFMMarker.class, TOC1Marker.class, TOC2Marker.class, TOC3Marker.class, TOCA1Marker.class, TOCA2Marker.class, TOCA3Marker.class, ISMarker.class, MTMarker.class, IMTMarker.class, IPMarker.class, IPIMarker.class, IMMarker.class, IMIMarker.class, IPQMarker.class, IMQMarker.class, IPRMarker.class, CLMarker.class, CMarker.class));
    }

    public final void insert(Marker input) {
        if (!tryInsert(input)) {
            // Since this is the root then add them anyway
            contents.add(input);
        }
    }

    public final void insert(USFMDocument document) {
        insertMultiple(document.contents);
    }

    public final void insertMultiple(java.lang.Iterable<Marker> input) {
        for (Marker i : input) {
            insert(i);
        }
    }
}
