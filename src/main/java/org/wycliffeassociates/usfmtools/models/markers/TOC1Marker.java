package org.wycliffeassociates.usfmtools.models.markers;

/**
 * A tag for the long table of contents
 */
public class TOC1Marker extends Marker {
    public String longTableOfContentsText;

    @Override
    public String getIdentifier() {
        return "toc1";
    }

    @Override
    public String preProcess(String input) {
        longTableOfContentsText = input.trim();
        return "";
    }
}
