package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Project text status tracking
 */
public class STSMarker extends Marker {
    public String statusText;

    @Override
    public String getIdentifier() {
        return "sts";
    }

    @Override
    public String preProcess(String input) {
        statusText = input.trim();
        return "";
    }
}
