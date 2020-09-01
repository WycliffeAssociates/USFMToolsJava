package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Chapter description marker
 */
public class CDMarker extends Marker {
    public String description;

    @Override
    public String getIdentifier() {
        return "cd";
    }

    @Override
    public String preProcess(String input) {
        description = input;
        return "";
    }
}
