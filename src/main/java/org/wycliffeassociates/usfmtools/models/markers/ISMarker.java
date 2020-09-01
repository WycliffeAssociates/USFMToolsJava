package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Introduction section heading
 */
public class ISMarker extends Marker {
    public int weight = 1;
    public String heading;

    @Override
    public String getIdentifier() {
        return "is";
    }

    @Override
    public String preProcess(String input) {
        heading = input.trim();
        return "";
    }
}
