package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Chapter label marker
 */
public class CLMarker extends Marker {
    public String label;

    @Override
    public String getIdentifier() {
        return "cl";
    }

    @Override
    public String preProcess(String input) {
        label = input.trim();
        return "";
    }
}
