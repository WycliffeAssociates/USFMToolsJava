package org.wycliffeassociates.usfmtools.models.markers;

import java.util.ArrayList;
import java.util.Arrays;

public class PRMarker extends Marker {
    @Override
    public String getIdentifier() {
        return "pr";
    }

    @Override
    public String preProcess(String input)
    {
        return input.trim();
    }

    @Override
    public ArrayList<Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(
                Arrays.asList(
                        TextBlock.class
                )
        );
    }
}
