package org.wycliffeassociates.usfmtools.models.markers;

public class IDMarker extends Marker {
    public String textIdentifier;

    @Override
    public String getIdentifier() {
        return "id";
    }

    @Override
    public String preProcess(String input) {
        textIdentifier = input.trim();
        return "";
    }
}
