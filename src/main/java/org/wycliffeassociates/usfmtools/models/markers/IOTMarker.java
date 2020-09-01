package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Introduction outline title
 */
public class IOTMarker extends Marker {
    public String title;

    @Override
    public String getIdentifier() {
        return "iot";
    }

    @Override
    public String preProcess(String input) {
        title = input.trim();
        return "";
    }
}
