package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VMarker extends Marker {
    // This is a string because of verse bridges. In the future this should have starting and ending verse
    public String verseNumber;
    private static Pattern verseRegex = Pattern.compile("^ *([0-9]*-?[0-9]*) ?(.*)", Pattern.DOTALL);
    public int startingVerse;
    public int endingVerse;

    public final String getVerseCharacter() {
        ArrayList<VPMarker> firstCharacterMarker = this.<VPMarker>getChildMarkers(VPMarker.class);
        if (!firstCharacterMarker.isEmpty()) {
            return firstCharacterMarker.get(0).verseCharacter;
        } else {
            return verseNumber;
        }
    }

    @Override
    public boolean tryInsert(Marker input) {
        if (input instanceof VMarker) {
            return false;
        }
        if (input instanceof QMarker && ((QMarker) input).isPoetryBlock) {
            return false;
        }
        return super.tryInsert(input);
    }

    @Override
    public String getIdentifier() {
        return "v";
    }

    @Override
    public String preProcess(String input) {
        Matcher match = verseRegex.matcher(input);
        match.find();
        verseNumber = match.group(1);
        if (!tangible.StringHelper.isNullOrWhiteSpace(verseNumber)) {
            var verseBridgeChars = verseNumber.split("[-]", -1);
            startingVerse = Integer.parseInt(verseBridgeChars[0]);
            endingVerse = verseBridgeChars.length > 1 && !tangible.StringHelper.isNullOrWhiteSpace(verseBridgeChars[1]) ? Integer.parseInt(verseBridgeChars[1]) : startingVerse;
        }
        return match.group(2);
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(
                Arrays.asList(
                        VPMarker.class,
                        VPEndMarker.class,
                        TLMarker.class,
                        TLEndMarker.class,
                        ADDMarker.class,
                        ADDEndMarker.class,
                        BMarker.class,
                        BKMarker.class,
                        BKEndMarker.class,
                        BDMarker.class,
                        BDEndMarker.class,
                        ITMarker.class,
                        ITEndMarker.class,
                        EMMarker.class,
                        EMEndMarker.class,
                        BDITMarker.class,
                        BDITEndMarker.class,
                        SUPMarker.class,
                        SUPEndMarker.class,
                        NOMarker.class,
                        NOEndMarker.class,
                        SCMarker.class,
                        SCEndMarker.class,
                        NDMarker.class,
                        NDEndMarker.class,
                        QMarker.class,
                        MMarker.class,
                        FMarker.class,
                        FEndMarker.class,
                        FRMarker.class,
                        FREndMarker.class,
                        SPMarker.class,
                        TextBlock.class,
                        WMarker.class,
                        WEndMarker.class,
                        XMarker.class,
                        XEndMarker.class,
                        CLSMarker.class,
                        RQMarker.class,
                        RQEndMarker.class,
                        PIMarker.class,
                        MIMarker.class,
                        QSMarker.class,
                        QSEndMarker.class,
                        QRMarker.class,
                        QCMarker.class,
                        QDMarker.class,
                        QACMarker.class,
                        QACEndMarker.class,
                        SMarker.class,
                        VAMarker.class,
                        VAEndMarker.class,
                        KMarker.class,
                        KEndMarker.class,
                        LFMarker.class,
                        LIKMarker.class,
                        LIKEndMarker.class,
                        LITLMarker.class,
                        LITLEndMarker.class,
                        LIVMarker.class,
                        LIMarker.class,
                        LIVEndMarker.class,
                        ORDMarker.class,
                        ORDEndMarker.class,
                        PMCMarker.class,
                        PMOMarker.class,
                        PMRMarker.class,
                        PNMarker.class,
                        PNEndMarker.class,
                        PNGMarker.class,
                        PNGEndMarker.class,
                        PRMarker.class,
                        QTMarker.class,
                        QTEndMarker.class,
                        RBMarker.class,
                        RBEndMarker.class,
                        SIGMarker.class,
                        SIGEndMarker.class,
                        SLSMarker.class,
                        SLSEndMarker.class,
                        WAMarker.class,
                        WAEndMarker.class,
                        WGMarker.class,
                        WGEndMarker.class,
                        WHMarker.class,
                        WHEndMarker.class,
                        WJMarker.class,
                        WJEndMarker.class,
                        FIGMarker.class,
                        FIGEndMarker.class,
                        PNMarker.class,
                        PNEndMarker.class,
                        PROMarker.class,
                        PROEndMarker.class,
                        REMMarker.class,
                        PMarker.class,
                        LIMarker.class
                )
        );
    }
}
