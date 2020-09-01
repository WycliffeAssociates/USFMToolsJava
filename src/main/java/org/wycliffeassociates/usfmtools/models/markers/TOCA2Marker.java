package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Tag for the Alternative language short table of contents
 */
public class TOCA2Marker extends Marker {
    public String altShortTableOfContentsText;

    @Override
    public String getIdentifier() {
        return "toca2";
    }

    @Override
    public String preProcess(String input) {
        altShortTableOfContentsText = input.trim();
        return "";
    }
}
