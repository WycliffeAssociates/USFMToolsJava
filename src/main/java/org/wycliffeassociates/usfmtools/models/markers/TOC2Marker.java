package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Tag for the short table of contents
 */
public class TOC2Marker extends Marker {
    public String shortTableOfContentsText;

    @Override
    public String getIdentifier() {
        return "toc2";
    }

    @Override
    public String preProcess(String input) {
        shortTableOfContentsText = input.trim();
        return "";
    }
}
