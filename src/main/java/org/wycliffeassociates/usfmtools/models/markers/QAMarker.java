package org.wycliffeassociates.usfmtools.models.markers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Acrostic heading for poetry
 */
public class QAMarker extends Marker {
    /**
     * Heading for the poetry
     */
    public String heading;

    @Override
    public String getIdentifier() {
        return "qa";
    }

    @Override
    public String preProcess(String input) {
        heading = input.trim();
        return "";
    }

    @Override
    public ArrayList<Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(
                Arrays.asList(
                        QACMarker.class,
                        QACEndMarker.class
                )
        );
    }
}
