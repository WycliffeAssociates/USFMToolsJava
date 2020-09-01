package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Running header marker
 */
public class HMarker extends Marker {
    public String headerText;

    @Override
    public String getIdentifier() {
        return "h";
    }

    @Override
    public String preProcess(String input) {
        headerText = input.trim();
        return "";
    }
}
