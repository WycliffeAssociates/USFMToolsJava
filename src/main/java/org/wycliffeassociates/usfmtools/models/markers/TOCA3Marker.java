package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Tag for alternative language book abbreviation
 */
public class TOCA3Marker extends Marker {
    public String altBookAbbreviation;

    @Override
    public String getIdentifier() {
        return "toca3";
    }

    @Override
    public String preProcess(String input) {
        altBookAbbreviation = input.trim();
        return "";
    }
}
