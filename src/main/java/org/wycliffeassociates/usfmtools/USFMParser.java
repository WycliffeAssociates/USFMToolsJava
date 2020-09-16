package org.wycliffeassociates.usfmtools;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.wycliffeassociates.usfmtools.models.ConvertToMarkerResult;
import org.wycliffeassociates.usfmtools.models.markers.*;
import tangible.StringHelper;

/**
 * Parses a USFM file into a Abstract Syntax Tree
 */
public class USFMParser {
    private ArrayList<String> ignoredTags;
    private static Pattern splitRegex = Pattern.compile("\\\\([a-z0-9\\-]*\\**)([^\\\\]*)");

    public USFMParser() {
        ignoredTags = new ArrayList<String>();
    }

    public USFMParser(ArrayList<String> tagsToIgnore) {
        ignoredTags = tagsToIgnore;
    }


    /**
     * Parses a string into a USFMDocument
     *
     * @param input A USFM string
     * @return A USFMDocument representing the input
     */
    public final USFMDocument parseFromString(String input) {
        USFMDocument output = new USFMDocument();
        ArrayList<Marker> markers = tokenizeFromString(input);

        for (Marker marker : markers) {
            if (marker instanceof TRMarker && !output.getTypesPathToLastMarker().contains(TableBlock.class)) {
                output.insert(new TableBlock());
            }

            int nextMarkerIndex = markers.indexOf(marker) + 1;
            if (marker instanceof QMarker
                    && markers.size() > nextMarkerIndex
                    && markers.get(nextMarkerIndex) instanceof VMarker
            ) {
                output.insert(new TableBlock());
            }

            output.insert(marker);
        }

        return output;
    }

    /**
     * Generate a list of Markers from a string
     *
     * @param input USFM String to tokenize
     * @return A List of Markers based upon the string
     */
    private ArrayList<Marker> tokenizeFromString(String input) {
        ArrayList<Marker> output = new ArrayList<>();

        Matcher match = splitRegex.matcher(input);
        while (match.find()) {
            if (ignoredTags.contains(match.group(1))) {
                continue;
            }

            ConvertToMarkerResult result = convertToMarker(match.group(1), match.group(2));
            result.marker.setPosition(match.start());
            output.add(result.marker);

            if (!StringHelper.isNullOrWhiteSpace(result.remainingText)) {
                output.add(new TextBlock(result.remainingText));
            }
        }

        return output;
    }

    private ConvertToMarkerResult convertToMarker(String identifier, String value) {
        Marker output = selectMarker(identifier);
        String tmp = output.preProcess(value);
        return new ConvertToMarkerResult(output, tmp);
    }

    private Marker selectMarker(String identifier) {
        switch (identifier) {
            case "id":
                return new IDMarker();
            case "ide":
                return new IDEMarker();
            case "sts":
                return new STSMarker();
            case "h":
                return new HMarker();
            case "toc1":
                return new TOC1Marker();
            case "toc2":
                return new TOC2Marker();
            case "toc3":
                return new TOC3Marker();
            case "toca1":
                return new TOCA1Marker();
            case "toca2":
                return new TOCA2Marker();
            case "toca3":
                return new TOCA3Marker();

            // Introduction Markers
            case "imt":
            case "imt1":
                return new IMTMarker();
            case "imt2":
                IMTMarker tempVar = new IMTMarker();
                tempVar.weight = 2;
                return tempVar;
            case "imt3":
                IMTMarker tempVar2 = new IMTMarker();
                tempVar2.weight = 3;
                return tempVar2;
            case "is":
            case "is1":
                return new ISMarker();
            case "is2":
                ISMarker tempVar3 = new ISMarker();
                tempVar3.weight = 2;
                return tempVar3;
            case "is3":
                ISMarker tempVar4 = new ISMarker();
                tempVar4.weight = 3;
                return tempVar4;
            case "ib":
                return new IBMarker();
            case "iq":
            case "iq1":
                return new IQMarker();
            case "iq2":
                IQMarker tempVar5 = new IQMarker();
                tempVar5.depth = 2;
                return tempVar5;
            case "iq3":
                IQMarker tempVar6 = new IQMarker();
                tempVar6.depth = 3;
                return tempVar6;
            case "iot":
                return new IOTMarker();
            case "io":
            case "io1":
                return new IOMarker();
            case "io2":
                IOMarker tempVar7 = new IOMarker();
                tempVar7.depth = 2;
                return tempVar7;
            case "io3":
                IOMarker tempVar8 = new IOMarker();
                tempVar8.depth = 3;
                return tempVar8;
            case "ior":
                return new IORMarker();
            case "ior*":
                return new IOREndMarker();
            case "ili":
            case "ili1":
                return new ILIMarker();
            case "ili2":
                ILIMarker tempVar9 = new ILIMarker();
                tempVar9.depth = 2;
                return tempVar9;
            case "ili3":
                ILIMarker tempVar10 = new ILIMarker();
                tempVar10.depth = 3;
                return tempVar10;
            case "ip":
                return new IPMarker();
            case "ipi":
                return new IPIMarker();
            case "im":
                return new IMMarker();
            case "imi":
                return new IMIMarker();
            case "ipq":
                return new IPQMarker();
            case "imq":
                return new IMQMarker();
            case "ipr":
                return new IPRMarker();
            case "mt":
            case "mt1":
                return new MTMarker();
            case "mt2":
                MTMarker tempVar11 = new MTMarker();
                tempVar11.weight = 2;
                return tempVar11;
            case "mt3":
                MTMarker tempVar12 = new MTMarker();
                tempVar12.weight = 3;
                return tempVar12;
            case "c":
                return new CMarker();
            case "cp":
                return new CPMarker();
            case "ca":
                return new CAMarker();
            case "ca*":
                return new CAEndMarker();
            case "p":
                return new PMarker();
            case "v":
                return new VMarker();
            case "va":
                return new VAMarker();
            case "va*":
                return new VAEndMarker();
            case "vp":
                return new VPMarker();
            case "vp*":
                return new VPEndMarker();
            case "q":
            case "q1":
                return new QMarker();
            case "q2":
                QMarker tempVar13 = new QMarker();
                tempVar13.depth = 2;
                return tempVar13;
            case "q3":
                QMarker tempVar14 = new QMarker();
                tempVar14.depth = 3;
                return tempVar14;
            case "qr":
                return new QRMarker();
            case "qc":
                return new QCMarker();
            case "qd":
                return new QDMarker();
            case "qac":
                return new QACMarker();
            case "qac*":
                return new QACEndMarker();
            case "qm":
            case "qm1":
                QMMarker tempVar15 = new QMMarker();
                tempVar15.depth = 1;
                return tempVar15;
            case "qm2":
                QMMarker tempVar16 = new QMMarker();
                tempVar16.depth = 2;
                return tempVar16;
            case "qm3":
                QMMarker tempVar17 = new QMMarker();
                tempVar17.depth = 3;
                return tempVar17;
            case "m":
                return new MMarker();
            case "d":
                return new DMarker();
            case "ms":
            case "ms1":
                return new MSMarker();
            case "ms2":
                MSMarker tempVar18 = new MSMarker();
                tempVar18.weight = 2;
                return tempVar18;
            case "ms3":
                MSMarker tempVar19 = new MSMarker();
                tempVar19.weight = 3;
                return tempVar19;
            case "mr":
                return new MRMarker();
            case "cl":
                return new CLMarker();
            case "qs":
                return new QSMarker();
            case "qs*":
                return new QSEndMarker();
            case "f":
                return new FMarker();
            case "fp":
                return new FPMarker();
            case "qa":
                return new QAMarker();
            case "nb":
                return new NBMarker();
            case "fqa":
                return new FQAMarker();
            case "fqa*":
                return new FQAEndMarker();
            case "fq":
                return new FQMarker();
            case "pi":
            case "pi1":
                return new PIMarker();
            case "pi2":
                PIMarker tempVar20 = new PIMarker();
                tempVar20.depth = 2;
                return tempVar20;
            case "pi3":
                PIMarker tempVar21 = new PIMarker();
                tempVar21.depth = 3;
                return tempVar21;
            case "sp":
                return new SPMarker();
            case "ft":
                return new FTMarker();
            case "fr":
                return new FRMarker();
            case "fk":
                return new FKMarker();
            case "fv":
                return new FVMarker();
            case "fv*":
                return new FVEndMarker();
            case "f*":
                return new FEndMarker();
            case "bd":
                return new BDMarker();
            case "bd*":
                return new BDEndMarker();
            case "it":
                return new ITMarker();
            case "it*":
                return new ITEndMarker();
            case "rem":
                return new REMMarker();
            case "b":
                return new BMarker();
            case "s":
            case "s1":
                return new SMarker();
            case "s2":
                SMarker tempVar22 = new SMarker();
                tempVar22.weight = 2;
                return tempVar22;
            case "s3":
                SMarker tempVar23 = new SMarker();
                tempVar23.weight = 3;
                return tempVar23;
            case "bk":
                return new BKMarker();
            case "bk*":
                return new BKEndMarker();
            case "li":
            case "li1":
                return new LIMarker();
            case "li2":
                LIMarker tempVar24 = new LIMarker();
                tempVar24.depth = 2;
                return tempVar24;
            case "li3":
                LIMarker tempVar25 = new LIMarker();
                tempVar25.depth = 3;
                return tempVar25;
            case "add":
                return new ADDMarker();
            case "add*":
                return new ADDEndMarker();
            case "tl":
                return new TLMarker();
            case "tl*":
                return new TLEndMarker();
            case "mi":
                return new MIMarker();
            case "sc":
                return new SCMarker();
            case "sc*":
                return new SCEndMarker();
            case "r":
                return new RMarker();
            case "rq":
                return new RQMarker();
            case "rq*":
                return new RQEndMarker();
            case "w":
                return new WMarker();
            case "w*":
                return new WEndMarker();
            case "x":
                return new XMarker();
            case "x*":
                return new XEndMarker();
            case "xo":
                return new XOMarker();
            case "xt":
                return new XTMarker();
            case "xq":
                return new XQMarker();
            case "pc":
                return new PCMarker();
            case "cls":
                return new CLSMarker();
            case "tr":
                return new TRMarker();
            case "th1":
                return new THMarker();
            case "thr1":
                return new THRMarker();
            case "th2":
                THMarker tempVar26 = new THMarker();
                tempVar26.columnPosition = 2;
                return tempVar26;
            case "thr2":
                THRMarker tempVar27 = new THRMarker();
                tempVar27.columnPosition = 2;
                return tempVar27;
            case "th3":
                THMarker tempVar28 = new THMarker();
                tempVar28.columnPosition = 3;
                return tempVar28;
            case "thr3":
                THRMarker tempVar29 = new THRMarker();
                tempVar29.columnPosition = 3;
                return tempVar29;
            case "tc1":
                return new TCMarker();
            case "tcr1":
                return new TCRMarker();
            case "tc2":
                TCMarker tempVar30 = new TCMarker();
                tempVar30.columnPosition = 2;
                return tempVar30;
            case "tcr2":
                TCRMarker tempVar31 = new TCRMarker();
                tempVar31.columnPosition = 2;
                return tempVar31;
            case "tc3":
                TCMarker tempVar32 = new TCMarker();
                tempVar32.columnPosition = 3;
                return tempVar32;
            case "tcr3":
                TCRMarker tempVar33 = new TCRMarker();
                tempVar33.columnPosition = 3;
                return tempVar33;
            case "usfm":
                return new USFMMarker();
            /* Character Styles */
            case "em":
                return new EMMarker();
            case "em*":
                return new EMEndMarker();
            case "bdit":
                return new BDITMarker();
            case "bdit*":
                return new BDITEndMarker();
            case "no":
                return new NOMarker();
            case "no*":
                return new NOEndMarker();
            case "nd":
                return new NDMarker();
            case "nd*":
                return new NDEndMarker();
            case "sup":
                return new SUPMarker();
            case "sup*":
                return new SUPEndMarker();
            default:
                UnknownMarker tempVar34 = new UnknownMarker();
                tempVar34.parsedIdentifier = identifier;
                return tempVar34;
        }
    }
}

