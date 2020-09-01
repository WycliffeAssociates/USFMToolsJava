package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Introduction major title
 */
public class IMTMarker extends Marker {
    public int weight = 1;
    public String introTitle;

    @Override
    public String getIdentifier() {
        return "imt";
    }

    @Override
    public String preProcess(String input) {
        introTitle = input.trim();
        return "";
    }
}
