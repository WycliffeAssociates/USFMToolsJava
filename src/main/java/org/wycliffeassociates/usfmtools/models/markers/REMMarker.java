package org.wycliffeassociates.usfmtools.models.markers;

public class REMMarker extends Marker {
    public String comment;

    @Override
    public String getIdentifier() {
        return "rem";
    }

    @Override
    public String preProcess(String input) {
        comment = input.trim();
        return "";
    }
}
