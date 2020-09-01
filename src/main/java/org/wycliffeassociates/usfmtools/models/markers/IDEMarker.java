package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Encoding marker
 */
public class IDEMarker extends Marker {
    public String encoding;

    @Override
    public String getIdentifier() {
        return "ide";
    }

    @Override
    public String preProcess(String input) {
        encoding = input.trim();
        return "";
    }
}
