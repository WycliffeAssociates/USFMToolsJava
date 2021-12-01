package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Chapter marker
 */
public class CMarker extends Marker {
    private static Pattern regex = Pattern.compile(" *(\\d*) *(.*)", Pattern.DOTALL);
    public int number;

    public final String getPublishedChapterMarker() {
        ArrayList<CPMarker> childCharacterMarker = this.<CPMarker>getChildMarkers(CPMarker.class);
        if (!childCharacterMarker.isEmpty()) {
            return childCharacterMarker.get(0).publishedChapterMarker;
        } else {
            return String.valueOf(number);
        }
    }

    public final String getCustomChapterLabel() {
        ArrayList<CLMarker> childChapLabelMarker = this.<CLMarker>getChildMarkers(CLMarker.class);
        if (!childChapLabelMarker.isEmpty()) {
            return childChapLabelMarker.get(0).label;
        } else {
            return getPublishedChapterMarker();
        }

    }

    @Override
    public String getIdentifier() {
        return "c";
    }

    @Override
    public String preProcess(String input) {
        Matcher match = regex.matcher(input);
        if (match.find()) {
            if (tangible.StringHelper.isNullOrWhiteSpace(match.group(1))) {
                number = 0;
            } else {
                number = Integer.parseInt(match.group(1));
            }
            if (tangible.StringHelper.isNullOrWhiteSpace(match.group(2))) {
                return "";
            }
            return tangible.StringHelper.trimEnd(match.group(2));
        }
        return "";
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(
                Arrays.asList(
                        MMarker.class,
                        MSMarker.class,
                        SMarker.class,
                        BMarker.class,
                        DMarker.class,
                        VMarker.class,
                        PMarker.class,
                        PCMarker.class,
                        CDMarker.class,
                        CPMarker.class,
                        DMarker.class,
                        CLMarker.class,
                        QMarker.class,
                        QSMarker.class,
                        QSEndMarker.class,
                        QAMarker.class,
                        QMarker.class,
                        NBMarker.class,
                        RMarker.class,
                        LIMarker.class,
                        TableBlock.class,
                        MMarker.class,
                        MIMarker.class,
                        PIMarker.class,
                        CAMarker.class,
                        CAEndMarker.class,
                        SPMarker.class,
                        TextBlock.class,
                        REMMarker.class,
                        DMarker.class,
                        VAMarker.class,
                        VAEndMarker.class,
                        FMarker.class,
                        FEndMarker.class
                )
        );
    }
}
