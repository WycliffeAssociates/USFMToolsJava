package org.wycliffeassociates.usfmtools;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wycliffeassociates.usfmtools.models.markers.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;

public class USFMParserTest {
    private USFMParser parser;

    @Before
    public void setupTest() throws Exception {
        parser = new USFMParser();
    }

    @Test
    public void testIgnoredTags() throws Exception {
        parser = new USFMParser(new ArrayList<String>(List.of("bd", "bd*")));
        USFMDocument doc = parser.parseFromString("\\v 1 In the beginning \\bd God \\bd*");
        Assert.assertEquals(1, doc.contents.size());
        VMarker vm = (VMarker) doc.contents.get(0);
        Assert.assertEquals(1, vm.contents.size());
        TextBlock tb = (TextBlock) vm.contents.get(0);
        Assert.assertEquals(0, tb.contents.size());
        Assert.assertEquals("In the beginning ", tb.text);
    }

    @Test
    public void testIdentificationMarkers() throws Exception {
        Assert.assertEquals("Genesis", ((IDMarker) parser.parseFromString("\\id Genesis").contents.get(0)).textIdentifier);
        Assert.assertEquals("UTF-8", ((IDEMarker) parser.parseFromString("\\ide UTF-8").contents.get(0)).encoding);
        Assert.assertEquals("2", ((STSMarker) parser.parseFromString("\\sts 2").contents.get(0)).statusText);
        Assert.assertEquals("3.0", ((USFMMarker) parser.parseFromString("\\usfm 3.0").contents.get(0)).getVersion());
        USFMDocument doc = parser.parseFromString("\\rem Remark");
        Assert.assertThat(doc.contents.get(0), instanceOf(REMMarker.class));
        REMMarker rem = (REMMarker) doc.contents.get(0);
        Assert.assertEquals("Remark", rem.comment);
    }

    @Test
    public void testIntroductionMarkers() throws Exception {
        Assert.assertEquals("Title", ((IMTMarker) parser.parseFromString("\\imt Title").contents.get(0)).introTitle);
        Assert.assertEquals(1, ((IMTMarker) parser.parseFromString("\\imt").contents.get(0)).weight);
        Assert.assertEquals(1, ((IMTMarker) parser.parseFromString("\\imt1").contents.get(0)).weight);
        Assert.assertEquals(2, ((IMTMarker) parser.parseFromString("\\imt2").contents.get(0)).weight);
        Assert.assertEquals(3, ((IMTMarker) parser.parseFromString("\\imt3").contents.get(0)).weight);
        Assert.assertEquals("Heading", ((ISMarker) parser.parseFromString("\\is Heading").contents.get(0)).heading);
        Assert.assertEquals(1, ((ISMarker) parser.parseFromString("\\is").contents.get(0)).weight);
        Assert.assertEquals(1, ((ISMarker) parser.parseFromString("\\is1").contents.get(0)).weight);
        Assert.assertEquals(2, ((ISMarker) parser.parseFromString("\\is2").contents.get(0)).weight);
        Assert.assertEquals(3, ((ISMarker) parser.parseFromString("\\is3").contents.get(0)).weight);
        Assert.assertEquals(1, ((IQMarker) parser.parseFromString("\\iq").contents.get(0)).depth);
        Assert.assertEquals(1, ((IQMarker) parser.parseFromString("\\iq1").contents.get(0)).depth);
        Assert.assertEquals(2, ((IQMarker) parser.parseFromString("\\iq2").contents.get(0)).depth);
        Assert.assertEquals(3, ((IQMarker) parser.parseFromString("\\iq3").contents.get(0)).depth);
        Assert.assertNotNull(((IBMarker) parser.parseFromString("\\ib").contents.get(0)));
        Assert.assertEquals("Title", ((IOTMarker) parser.parseFromString("\\iot Title").contents.get(0)).title);
        Assert.assertEquals(1, ((IOMarker) parser.parseFromString("\\io").contents.get(0)).depth);
        Assert.assertEquals(1, ((IOMarker) parser.parseFromString("\\io1").contents.get(0)).depth);
        Assert.assertEquals(2, ((IOMarker) parser.parseFromString("\\io2").contents.get(0)).depth);
        Assert.assertEquals(3, ((IOMarker) parser.parseFromString("\\io3").contents.get(0)).depth);
        USFMDocument doc = parser.parseFromString("\\ior (1.1-3)\\ior*");
        Assert.assertEquals(2, doc.contents.size());
        Assert.assertEquals("(1.1-3)", ((TextBlock) doc.contents.get(0).contents.get(0)).text);
        Assert.assertEquals("Text", ((TextBlock) parser.parseFromString("\\ili Text").contents.get(0).contents.get(0)).text);
        Assert.assertEquals(1, ((ILIMarker) parser.parseFromString("\\ili").contents.get(0)).depth);
        Assert.assertEquals(1, ((ILIMarker) parser.parseFromString("\\ili1").contents.get(0)).depth);
        Assert.assertEquals(2, ((ILIMarker) parser.parseFromString("\\ili2").contents.get(0)).depth);
        Assert.assertEquals(3, ((ILIMarker) parser.parseFromString("\\ili3").contents.get(0)).depth);
        doc = parser.parseFromString("\\ip Text");
        isInstanceOfType(doc.contents.get(0), IPMarker.class);
        Assert.assertEquals("Text", ((TextBlock) doc.contents.get(0).contents.get(0)).text);
        doc = parser.parseFromString("\\ipi Text");
        isInstanceOfType(doc.contents.get(0), IPIMarker.class);
        Assert.assertEquals("Text", ((TextBlock) doc.contents.get(0).contents.get(0)).text);
        doc = parser.parseFromString("\\im Text");
        isInstanceOfType(doc.contents.get(0), IMMarker.class);
        Assert.assertEquals("Text", ((TextBlock) doc.contents.get(0).contents.get(0)).text);
        doc = parser.parseFromString("\\is Heading");
        isInstanceOfType(doc.contents.get(0), ISMarker.class);
        Assert.assertEquals("Heading", ((ISMarker) doc.contents.get(0)).heading);
        doc = parser.parseFromString("\\iq Quote");
        isInstanceOfType(doc.contents.get(0), IQMarker.class);
        Assert.assertEquals("Quote", ((TextBlock) doc.contents.get(0).contents.get(0)).text);
        Assert.assertEquals(1, ((IQMarker) parser.parseFromString("\\iq Quote").contents.get(0)).depth);
        Assert.assertEquals(1, ((IQMarker) parser.parseFromString("\\iq1 Quote").contents.get(0)).depth);
        Assert.assertEquals(2, ((IQMarker) parser.parseFromString("\\iq2 Quote").contents.get(0)).depth);
        Assert.assertEquals(3, ((IQMarker) parser.parseFromString("\\iq3 Quote").contents.get(0)).depth);
        doc = parser.parseFromString("\\imi Text");
        isInstanceOfType(doc.contents.get(0), IMIMarker.class);
        Assert.assertEquals("Text", ((TextBlock) doc.contents.get(0).contents.get(0)).text);
        doc = parser.parseFromString("\\ipq Text");
        isInstanceOfType(doc.contents.get(0), IPQMarker.class);
        Assert.assertEquals("Text", ((TextBlock) doc.contents.get(0).contents.get(0)).text);
        doc = parser.parseFromString("\\imq Text");
        isInstanceOfType(doc.contents.get(0), IMQMarker.class);
        Assert.assertEquals("Text", ((TextBlock) doc.contents.get(0).contents.get(0)).text);
        doc = parser.parseFromString("\\ipr Text");
        isInstanceOfType(doc.contents.get(0), IPRMarker.class);
        Assert.assertEquals("Text", ((TextBlock) doc.contents.get(0).contents.get(0)).text);
    }

    @Test
    public void testSectionParse() throws Exception {
        // Section Headings
        Assert.assertEquals("Silsilah Yesus Kristus ", ((SMarker) parser.parseFromString("\\s Silsilah Yesus Kristus \\r (Luk. 3:23 - 38)").contents.get(0)).text);
        Assert.assertEquals("Kumpulkanlah Harta di Surga ", ((SMarker) parser.parseFromString("\\s3 Kumpulkanlah Harta di Surga \\r (Luk. 12:33 - 34; 11:34 - 36; 16:13)").contents.get(0)).text);
        Assert.assertEquals(1, ((SMarker) parser.parseFromString("\\s Silsilah Yesus Kristus \\r (Luk. 3:23 - 38)").contents.get(0)).weight);
        Assert.assertEquals(2, ((SMarker) parser.parseFromString("\\s2 Silsilah Yesus Kristus \\r (Luk. 3:23 - 38)").contents.get(0)).weight);
        Assert.assertEquals(3, ((SMarker) parser.parseFromString("\\s3 Silsilah Yesus Kristus \\r (Luk. 3:23 - 38)").contents.get(0)).weight);
        // Major Section
        Assert.assertEquals("jilid 1 ", ((MSMarker) parser.parseFromString("\\ms1 jilid 1 \\mr (Mazmur 1 - 41)").contents.get(0)).heading);
        Assert.assertEquals("jilid 1 ", ((MSMarker) parser.parseFromString("\\ms2 jilid 1 \\mr (Mazmur 1 - 41)").contents.get(0)).heading);
        Assert.assertEquals(3, ((MSMarker) parser.parseFromString("\\ms3 jilid 1 \\mr (Mazmur 1 - 41)").contents.get(0)).weight);
        Assert.assertEquals(1, ((MSMarker) parser.parseFromString("\\ms jilid 1 \\mr (Mazmur 1 - 41)").contents.get(0)).weight);
        // References
        Assert.assertEquals("(Mazmur 1 - 41)", ((MRMarker) parser.parseFromString("\\ms2 jilid 1 \\mr (Mazmur 1 - 41)").contents.get(0).contents.get(0)).sectionReference);
        Assert.assertEquals("(Mazmur 41)", ((MRMarker) parser.parseFromString("\\ms2 jilid 1 \\mr (Mazmur 41)").contents.get(0).contents.get(0)).sectionReference);
        Assert.assertEquals("(Mazmur)", ((MRMarker) parser.parseFromString("\\ms2 jilid 1 \\mr (Mazmur)").contents.get(0).contents.get(0)).sectionReference);
    }

    @Test
    public void testTableOfContentsParse() throws Exception {
        // Table of Contents
        Assert.assertEquals("Keluaran", ((TOC1Marker) parser.parseFromString("\\toc1 Keluaran").contents.get(0)).longTableOfContentsText);
        Assert.assertEquals("Keluaran", ((TOC2Marker) parser.parseFromString("\\toc2 Keluaran").contents.get(0)).shortTableOfContentsText);
        Assert.assertEquals("Kel", ((TOC3Marker) parser.parseFromString("\\toc3 Kel").contents.get(0)).bookAbbreviation);
        // Alternate Table of Contents
        Assert.assertEquals("Keluaran", ((TOCA1Marker) parser.parseFromString("\\toca1 Keluaran").contents.get(0)).altLongTableOfContentsText);
        Assert.assertEquals("Keluaran", ((TOCA2Marker) parser.parseFromString("\\toca2 Keluaran").contents.get(0)).altShortTableOfContentsText);
        Assert.assertEquals("Kel", ((TOCA3Marker) parser.parseFromString("\\toca3 Kel").contents.get(0)).altBookAbbreviation);
    }

    @Test
    public void testMajorTitleParse() throws Exception {
        Assert.assertEquals("Keluaran", ((MTMarker) parser.parseFromString("\\mt1 Keluaran").contents.get(0)).title);
        Assert.assertEquals("Keluaran", ((MTMarker) parser.parseFromString("\\mt3 Keluaran").contents.get(0)).title);
        Assert.assertEquals(1, ((MTMarker) parser.parseFromString("\\mt Keluaran").contents.get(0)).weight);
        Assert.assertEquals(2, ((MTMarker) parser.parseFromString("\\mt2 Keluaran").contents.get(0)).weight);
    }

    @Test
    public void testHeaderParse() throws Exception {
        Assert.assertEquals("Genesis", ((HMarker) parser.parseFromString("\\h Genesis").contents.get(0)).headerText);
        Assert.assertEquals("", ((HMarker) parser.parseFromString("\\h").contents.get(0)).headerText);
        Assert.assertEquals("1 John", ((HMarker) parser.parseFromString("\\h 1 John").contents.get(0)).headerText);
        Assert.assertEquals("", ((HMarker) parser.parseFromString("\\h   ").contents.get(0)).headerText);
        USFMDocument doc = parser.parseFromString("\\sp Job");
        SPMarker sp = (SPMarker) doc.contents.get(0);
        Assert.assertEquals("Job", sp.speaker);
    }

    @Test
    public void testChapterParse() throws Exception {
        Assert.assertEquals(1, ((CMarker) parser.parseFromString("\\c 1").contents.get(0)).number);
        Assert.assertEquals(1000, ((CMarker) parser.parseFromString("\\c 1000").contents.get(0)).number);
        Assert.assertEquals(0, ((CMarker) parser.parseFromString("\\c 0").contents.get(0)).number);
        // Chapter Labels
        Assert.assertEquals("Chapter One", ((CLMarker) parser.parseFromString("\\c 1 \\cl Chapter One").contents.get(0).contents.get(0)).label);
        Assert.assertEquals("Chapter One", ((CLMarker) parser.parseFromString("\\cl Chapter One \\c 1").contents.get(0)).label);
        Assert.assertEquals("Chapter Two", ((CLMarker) parser.parseFromString("\\c 1 \\cl Chapter One \\c 2 \\cl Chapter Two").contents.get(1).contents.get(0)).label);
        USFMDocument doc = parser.parseFromString("\\cp Q");
        isInstanceOfType(doc.contents.get(0), CPMarker.class);
        Assert.assertEquals("Q", ((CPMarker) doc.contents.get(0)).publishedChapterMarker);
        doc = parser.parseFromString("\\ca 53 \\ca*");
        Assert.assertEquals(2, doc.contents.size());
        CAMarker caBegin = (CAMarker) doc.contents.get(0);
        CAEndMarker caEnd = (CAEndMarker) doc.contents.get(1);
        Assert.assertEquals("53", caBegin.altChapterNumber);
        doc = parser.parseFromString("\\va 22 \\va*");
        Assert.assertEquals(2, doc.contents.size());
        VAMarker vaBegin = (VAMarker) doc.contents.get(0);
        VAEndMarker vaEnd = (VAEndMarker) doc.contents.get(1);
        Assert.assertEquals("22", vaBegin.altVerseNumber);
        doc = parser.parseFromString("\\p In the beginning God created the heavens and the earth.");
        isInstanceOfType(doc.contents.get(0), PMarker.class);
        Assert.assertEquals("In the beginning God created the heavens and the earth.", ((TextBlock) doc.contents.get(0).contents.get(0)).text);
        doc = parser.parseFromString("\\pc In the beginning God created the heavens and the earth.");
        isInstanceOfType(doc.contents.get(0), PCMarker.class);
        Assert.assertEquals("In the beginning God created the heavens and the earth.", ((TextBlock) doc.contents.get(0).contents.get(0)).text);
        doc = parser.parseFromString("\\p \\v 1 In the beginning God created the heavens and the earth.");
        isInstanceOfType(doc.contents.get(0), PMarker.class);
        PMarker pm = (PMarker) doc.contents.get(0);
        VMarker vm = (VMarker) pm.contents.get(0);
        Assert.assertEquals("In the beginning God created the heavens and the earth.", ((TextBlock) vm.contents.get(0)).text);
        doc = parser.parseFromString("\\mi");
        Assert.assertEquals(1, doc.contents.size());
        isInstanceOfType(doc.contents.get(0), MIMarker.class);
        doc = parser.parseFromString("\\d A Psalm of David");
        Assert.assertEquals("A Psalm of David", ((DMarker) doc.contents.get(0)).description);
        doc = parser.parseFromString("\\nb");
        isInstanceOfType(doc.contents.get(0), NBMarker.class);
        doc = parser.parseFromString("\\fq the Son of God");
        isInstanceOfType(doc.contents.get(0), FQMarker.class);
        Assert.assertEquals("the Son of God", ((TextBlock) doc.contents.get(0).contents.get(0)).text);
        doc = parser.parseFromString("\\pi The one who scattered");
        isInstanceOfType(doc.contents.get(0), PIMarker.class);
        Assert.assertEquals(1, doc.contents.size());
        Assert.assertEquals("The one who scattered", ((TextBlock) doc.contents.get(0).contents.get(0)).text);
        Assert.assertEquals(1, ((PIMarker) parser.parseFromString("\\pi").contents.get(0)).depth);
        Assert.assertEquals(1, ((PIMarker) parser.parseFromString("\\pi1").contents.get(0)).depth);
        Assert.assertEquals(2, ((PIMarker) parser.parseFromString("\\pi2").contents.get(0)).depth);
        Assert.assertEquals(3, ((PIMarker) parser.parseFromString("\\pi3").contents.get(0)).depth);
        doc = parser.parseFromString("\\m \\v 37 David himself called him 'Lord';");
        Assert.assertEquals(1, doc.contents.size());
        MMarker mm = (MMarker) doc.contents.get(0);
        Assert.assertEquals(1, mm.contents.size());
        vm = (VMarker) mm.contents.get(0);
        Assert.assertEquals("David himself called him 'Lord';", ((TextBlock) vm.contents.get(0)).text);
        doc = parser.parseFromString("\\b");
        Assert.assertEquals(1, doc.contents.size());
        isInstanceOfType(doc.contents.get(0), BMarker.class);
    }

    @Test
    public void testVerseParse() throws Exception {
        Assert.assertEquals("9", ((VMarker) parser.parseFromString("\\v 9 Yahweh God called to the man and said to him, \"Where are you?\"").contents.get(0)).verseNumber);
        Assert.assertEquals("26", ((VMarker) (parser.parseFromString("\\v 26 God said, \"Let us make man in our image, after our likeness. Let them have dominion over the fish of the sea, over the birds of the sky, over the livestock, over all the earth, and over every creeping thing that creeps on the earth.\" \\f + \\ft Some ancient copies have: \\fqa ... Over the livestock, over all the animals of the earth, and over every creeping thing that creeps on the earth \\fqa*  . \\f*").contents.get(0))).verseNumber);
        Assert.assertEquals("0", ((VMarker) parser.parseFromString("\\v 0 Not in the Bible").contents.get(0)).verseNumber);
        Assert.assertEquals("1-2", ((VMarker) parser.parseFromString("\\v 1-2 Not in the Bible").contents.get(0)).verseNumber);
        // References - Quoted book title - Parallel passage reference
        Assert.assertEquals("(Luk. 3:23 - 38)", ((TextBlock) parser.parseFromString("\\s Silsilah Yesus Kristus \\r (Luk. 3:23 - 38)").contents.get(0).contents.get(0).contents.get(0)).text);
        Assert.assertEquals("(Luk. 12:33 - 34; 11:34 - 36; 16:13)", ((TextBlock) parser.parseFromString("\\s Kumpulkanlah Harta di Surga \\r (Luk. 12:33 - 34; 11:34 - 36; 16:13)").contents.get(0).contents.get(0).contents.get(0)).text);
        Assert.assertEquals("Kitab Peperangan TUHAN,", ((BKMarker) parser.parseFromString("\\v 14 Itulah sebabnya kata-kata ini ditulis dalam \\bk Kitab Peperangan TUHAN,\\bk*").contents.get(0).contents.get(1)).bookTitle);
        Assert.assertEquals("Psa 2.7", ((TextBlock) parser.parseFromString("\\v 5 For God never said to any of his angels,\\q1 \"You are my Son;\\q2 today I have become your Father.\"\\rq Psa 2.7\\rq* ").contents.get(0).contents.get(3).contents.get(0)).text);
        // Closing - Selah
        Assert.assertEquals("[[ayt.co/Mat]]", ((TextBlock) parser.parseFromString("\\cls [[ayt.co/Mat]]").contents.get(0).contents.get(0)).text);
        Assert.assertEquals("Sela", ((TextBlock) parser.parseFromString("\\v 3 Allah datang dari negeri Teman \\q2 dan Yang Mahakudus datang dari Gunung Paran. \\qs Sela \\qs* ").contents.get(0).contents.get(1).contents.get(1).contents.get(0)).text);
        Assert.assertEquals("Sela", ((TextBlock) parser.parseFromString("\\q2 dan sampai batu yang penghabisan. \\qs Sela \\qs*").contents.get(0).contents.get(1).contents.get(0)).text);
        // Transliterated
        Assert.assertEquals("Hades", ((TextBlock) parser.parseFromString("\\f + \\fr 10:15 \\fk dunia orang mati \\ft Dalam bahasa Yunani adalah \\tl Hades\\tl* \\ft , tempat orang setelah meninggal.\\f*").contents.get(0).contents.get(2).contents.get(1).contents.get(0)).text);
        Assert.assertEquals("TEKEL", ((TextBlock) parser.parseFromString("\\v 27 \\tl TEKEL\\tl* :").contents.get(0).contents.get(0).contents.get(0)).text);
    }

    @Test
    public void testTableParse() throws Exception {
        isInstanceOfType(parser.parseFromString("\\tr \\tc1 dari suku Ruben \\tcr2 12.000").contents.get(0), TableBlock.class);
        // Table Rows - Cells
        isInstanceOfType(parser.parseFromString("\\tr \\tc1 dari suku Ruben \\tcr2 12.000").contents.get(0).contents.get(0), TRMarker.class);
        Assert.assertEquals("dari suku Ruben", ((TextBlock) parser.parseFromString("\\tr \\tc1 dari suku Ruben \\tcr2 12.000").contents.get(0).contents.get(0).contents.get(0).contents.get(0)).text);
        Assert.assertEquals("12.000", ((TextBlock) parser.parseFromString("\\tr \\tc1 dari suku Ruben \\tcr2 12.000").contents.get(0).contents.get(0).contents.get(1).contents.get(0)).text);
        Assert.assertEquals(1, ((TCMarker) parser.parseFromString("\\tr \\tc1 dari suku Ruben \\tcr2 12.000").contents.get(0).contents.get(0).contents.get(0)).columnPosition);
        Assert.assertEquals(2, ((TCMarker) parser.parseFromString("\\tr \\tc2 dari suku Ruben \\tcr2 12.000").contents.get(0).contents.get(0).contents.get(0)).columnPosition);
        Assert.assertEquals(3, ((TCMarker) parser.parseFromString("\\tr \\tc3 dari suku Ruben \\tcr2 12.000").contents.get(0).contents.get(0).contents.get(0)).columnPosition);
        Assert.assertEquals(1, ((TCRMarker) parser.parseFromString("\\tr \\tc1 dari suku Ruben \\tcr1 12.000").contents.get(0).contents.get(0).contents.get(1)).columnPosition);
        Assert.assertEquals(2, ((TCRMarker) parser.parseFromString("\\tr \\tc1 dari suku Ruben \\tcr2 12.000").contents.get(0).contents.get(0).contents.get(1)).columnPosition);
        Assert.assertEquals(3, ((TCRMarker) parser.parseFromString("\\tr \\tc1 dari suku Ruben \\tcr3 12.000").contents.get(0).contents.get(0).contents.get(1)).columnPosition);
        // Embedded Verse
        Assert.assertEquals("6", ((VMarker) parser.parseFromString("\\tc1 \\v 6 dari suku Asyer").contents.get(0).contents.get(0)).verseNumber);
        // Table Headers
        Assert.assertEquals("dari suku Ruben", ((TextBlock) parser.parseFromString("\\tr \\th1 dari suku Ruben \\thr2 12.000").contents.get(0).contents.get(0).contents.get(0).contents.get(0)).text);
        Assert.assertEquals("12.000", ((TextBlock) parser.parseFromString("\\tr \\th1 dari suku Ruben \\thr2 12.000").contents.get(0).contents.get(0).contents.get(1).contents.get(0)).text);
        Assert.assertEquals(1, ((THMarker) parser.parseFromString("\\tr \\th1 dari suku Ruben \\thr2 12.000").contents.get(0).contents.get(0).contents.get(0)).columnPosition);
        Assert.assertEquals(2, ((THMarker) parser.parseFromString("\\tr \\th2 dari suku Ruben \\thr 12.000").contents.get(0).contents.get(0).contents.get(0)).columnPosition);
        Assert.assertEquals(3, ((THMarker) parser.parseFromString("\\tr \\th3 dari suku Ruben \\thr 12.000").contents.get(0).contents.get(0).contents.get(0)).columnPosition);
        Assert.assertEquals(1, ((THRMarker) parser.parseFromString("\\tr \\th1 dari suku Ruben \\thr1 12.000").contents.get(0).contents.get(0).contents.get(1)).columnPosition);
        Assert.assertEquals(2, ((THRMarker) parser.parseFromString("\\tr \\th1 dari suku Ruben \\thr2 12.000").contents.get(0).contents.get(0).contents.get(1)).columnPosition);
        Assert.assertEquals(3, ((THRMarker) parser.parseFromString("\\tr \\th1 dari suku Ruben \\thr3 12.000").contents.get(0).contents.get(0).contents.get(1)).columnPosition);
    }

    @Test
    public void testListParse() throws Exception {
        // List Items
        Assert.assertEquals("Peres ayah Hezron.", ((TextBlock) parser.parseFromString("\\li Peres ayah Hezron.").contents.get(0).contents.get(0)).text);
        Assert.assertEquals(1, ((LIMarker) parser.parseFromString("\\li Peres ayah Hezron.").contents.get(0)).depth);
        Assert.assertEquals(1, ((LIMarker) parser.parseFromString("\\li1 Peres ayah Hezron.").contents.get(0)).depth);
        Assert.assertEquals(2, ((LIMarker) parser.parseFromString("\\li2 Peres ayah Hezron.").contents.get(0)).depth);
        Assert.assertEquals(3, ((LIMarker) parser.parseFromString("\\li3 Peres ayah Hezron.").contents.get(0)).depth);
        // Verse within List
        Assert.assertEquals("19", ((VMarker) parser.parseFromString("\\li Peres ayah Hezron. \\li \\v 19 Hezron ayah Ram.").contents.get(1).contents.get(0)).verseNumber);
    }

    @Test
    public void testFootnoteParse() throws Exception {
        // Footnote Text Marker
        Assert.assertEquals("Sample Simple Footnote. ", ((TextBlock) parser.parseFromString("\\f + \\ft Sample Simple Footnote. \\f*").contents.get(0).contents.get(0).contents.get(0)).text);
        // Footnote Caller
        Assert.assertEquals("+", ((FMarker) parser.parseFromString("\\f + \\ft Sample Simple Footnote. \\f*").contents.get(0)).footNoteCaller);
        Assert.assertEquals("-", ((FMarker) parser.parseFromString("\\f - \\ft Sample Simple Footnote. \\f*").contents.get(0)).footNoteCaller);
        Assert.assertEquals("abc", ((FMarker) parser.parseFromString("\\f abc \\ft Sample Simple Footnote. \\f*").contents.get(0)).footNoteCaller);
        // Footnote Alternate Translation Marker
        Assert.assertEquals("... Over the livestock, over all the animals of the earth, and over every creeping thing that creeps on the earth ", ((TextBlock) parser.parseFromString("\\v 26 God said, \"Let us make man in our image, after our likeness. Let them have dominion over the fish of the sea, over the birds of the sky, over the livestock, over all the earth, and over every creeping thing that creeps on the earth.\" \\f + \\ft Some ancient copies have: \\fqa ... Over the livestock, over all the animals of the earth, and over every creeping thing that creeps on the earth \\fqa*  . \\f*").contents.get(0).contents.get(1).contents.get(0).contents.get(1).contents.get(0)).text);
        // Footnote Keyword
        Assert.assertEquals("Tamar", ((FKMarker) parser.parseFromString("\\f + \\fr 1.3 \\fk Tamar \\ft Menantu Yehuda yang akhirnya menjadi istrinya (bc. Kej. 38:1-30).\\f*").contents.get(0).contents.get(1)).footNoteKeyword);
        //Footnote Reference
        Assert.assertEquals("1.3", ((FRMarker) parser.parseFromString("\\f + \\fr 1.3 \\fk Tamar \\ft Menantu Yehuda yang akhirnya menjadi istrinya (bc. Kej. 38:1-30).\\f*").contents.get(0).contents.get(0)).verseReference);
        // Footnote Verse Marker - Paragraph
        Assert.assertEquals("56", ((FVMarker) parser.parseFromString("\\f + \\fr 9:55 \\ft Beberapa salinan Bahasa Yunani menambahkan: Dan ia berkata, Kamu tidak tahu roh apa yang memilikimu. \\fv 56 \\fv* \\ft Anak Manusia tidak datang untuk menghancurkan hidup manusia, tetapi untuk menyelamatkan mereka.\\f*").contents.get(0).contents.get(2)).verseCharacter);
        isInstanceOfType(parser.parseFromString("\\f + \\fr 17.25 \\ft Kemungkinan maksudnya adalah bebas dari kewajiban pajak seumur hidup. (bdk. NIV. NET) \\fp \\f*").contents.get(0).contents.get(2), FPMarker.class);
        // Make sure that a fqa end marker doesn't end up outside of the footnote
        Assert.assertEquals(1, parser.parseFromString("\\v 1 Words \\f + \\fqa Thing \\fqa* \\f*").contents.size());
    }

    @Test
    public void testCrossReferenceParse() throws Exception {
        // Cross Reference Caller
        Assert.assertEquals("-", ((XMarker) parser.parseFromString("\\x - \\xo 11.21 \\xq Tebes \\xt \\x*").contents.get(0)).crossRefCaller);
        // Cross Reference Origin
        Assert.assertEquals("11.21", ((XOMarker) parser.parseFromString("\\x - \\xo 11.21 \\xq Tebes \\xt \\x*").contents.get(0).contents.get(0)).originRef);
        // Cross Reference Target
        Assert.assertEquals("Mrk 1.24; Luk 2.39; Jhn 1.45.", ((TextBlock) parser.parseFromString("\\x - \\xo 11.21 \\xq Tebes \\xt Mrk 1.24; Luk 2.39; Jhn 1.45.\\x*").contents.get(0).contents.get(2).contents.get(0)).text);
        // Cross Reference Quotation
        Assert.assertEquals("Tebes", ((TextBlock) parser.parseFromString("\\x - \\xo 11.21 \\xq Tebes \\xt \\x*").contents.get(0).contents.get(1).contents.get(0)).text);
    }

    @Test
    public void testVerseCharacterParse() throws Exception {
        Assert.assertEquals("1a", ((VPMarker) parser.parseFromString("\\v 1 \\vp 1a \\vp* This is not Scripture").contents.get(0).contents.get(0)).verseCharacter);
        Assert.assertEquals("2b", ((VPMarker) parser.parseFromString("\\v 2 \\vp 2b \\vp* This is not Scripture").contents.get(0).contents.get(0)).verseCharacter);
        Assert.assertEquals("asdf", ((VPMarker) parser.parseFromString("\\v 1 \\vp asdf \\vp* This is not Scripture").contents.get(0).contents.get(0)).verseCharacter);
    }

    @Test
    public void testTranslationNotesParse() throws Exception {
        // Translator’s addition
        Assert.assertEquals("dan mencari TUHAN semesta alam!", ((TextBlock) parser.parseFromString("\\v 21 Penduduk kota yang satu akan pergi kepada penduduk kota yang lain sambil berkata,\\q2 'Mari kita pergi memohon belas kasihan TUHAN\\q1 \\add dan mencari TUHAN semesta alam!\\add * ").contents.get(0).contents.get(3).contents.get(0)).text);
        Assert.assertEquals("(malaikat)", ((TextBlock) parser.parseFromString("\\v 1 “Pada tahun pertama pemerintahan Darius, orang Media, aku bangkit untuk menguatkan dan melindungi dia.” \\add (malaikat)\\add* dari Persia.").contents.get(0).contents.get(1).contents.get(0)).text);
    }

    @Test
    public void testWordEntryParse() throws Exception {
        // Within Footnotes
        Assert.assertEquals("Berhala", ((WMarker) parser.parseFromString("\\f + \\fr 3:5 \\fk berhala \\ft Lih. \\w Berhala \\w* di Daftar Istilah.\\f*").contents.get(0).contents.get(2).contents.get(1)).term);
        // Word Entry Attributes
        Assert.assertEquals("Berhala", ((WMarker) parser.parseFromString("\\f + \\fr 3:5 \\fk berhala \\ft Lih. \\w Berhala|Berhala \\w* di Daftar Istilah.\\f*").contents.get(0).contents.get(2).contents.get(1)).attributes.get("lemma"));
        var stuff = ((WMarker) parser.parseFromString("\\f + \\fr 3:5 \\fk berhala \\ft Lih. \\w gracious|lemma=\"grace\" \\w* di Daftar Istilah.\\f*").contents.get(0).contents.get(2).contents.get(1));
        Assert.assertEquals("grace", ((WMarker) parser.parseFromString("\\f + \\fr 3:5 \\fk berhala \\ft Lih. \\w gracious|lemma=\"grace\" \\w* di Daftar Istilah.\\f*").contents.get(0).contents.get(2).contents.get(1)).attributes.get("lemma"));
        Assert.assertEquals("G5485", ((WMarker) parser.parseFromString("\\f + \\fr 3:5 \\fk berhala \\ft Lih. \\w gracious|lemma=\"grace\" strong=\"G5485\" \\w* di Daftar Istilah.\\f*").contents.get(0).contents.get(2).contents.get(1)).attributes.get("strong"));
        Assert.assertEquals("H1234,G5485", ((WMarker) parser.parseFromString("\\f + \\fr 3:5 \\fk berhala \\ft Lih. \\w gracious|strong=\"H1234,G5485\" \\w* di Daftar Istilah.\\f*").contents.get(0).contents.get(2).contents.get(1)).attributes.get("strong"));
        Assert.assertEquals("gnt5:51.1.2.1", ((WMarker) parser.parseFromString("\\f + \\fr 3:5 \\fk berhala \\ft Lih. \\w gracious|lemma=\"grace\" srcloc=\"gnt5:51.1.2.1\" \\w* di Daftar Istilah.\\f*").contents.get(0).contents.get(2).contents.get(1)).attributes.get("srcloc"));
    }

    @Test
    public void testPoetryParse() throws Exception {
        USFMDocument doc = parser.parseFromString("\\q Quote");
        isInstanceOfType(doc.contents.get(0), QMarker.class);
        Assert.assertEquals("Quote", ((TextBlock) doc.contents.get(0).contents.get(0)).text);
        Assert.assertEquals(1, ((QMarker) parser.parseFromString("\\q Quote").contents.get(0)).depth);
        Assert.assertEquals(1, ((QMarker) parser.parseFromString("\\q1 Quote").contents.get(0)).depth);
        Assert.assertEquals(2, ((QMarker) parser.parseFromString("\\q2 Quote").contents.get(0)).depth);
        Assert.assertEquals(3, ((QMarker) parser.parseFromString("\\q3 Quote").contents.get(0)).depth);
        doc = parser.parseFromString("\\qr God's love never fails.");
        isInstanceOfType(doc.contents.get(0), QRMarker.class);
        Assert.assertEquals("God's love never fails.", ((TextBlock) doc.contents.get(0).contents.get(0)).text);
        doc = parser.parseFromString("\\qc Amen! Amen!");
        isInstanceOfType(doc.contents.get(0), QCMarker.class);
        Assert.assertEquals("Amen! Amen!", ((TextBlock) doc.contents.get(0).contents.get(0)).text);
        doc = parser.parseFromString("\\qd For the director of music.");
        isInstanceOfType(doc.contents.get(0), QDMarker.class);
        Assert.assertEquals("For the director of music.", ((TextBlock) doc.contents.get(0).contents.get(0)).text);
        doc = parser.parseFromString("\\qac P\\qac*");
        Assert.assertEquals(2, doc.contents.size());
        QACMarker qac = (QACMarker) doc.contents.get(0);
        QACEndMarker qacEnd = (QACEndMarker) doc.contents.get(1);
        Assert.assertEquals("P", qac.acrosticLetter);
        doc = parser.parseFromString("\\qm God is on your side.");
        Assert.assertEquals(1, doc.contents.size());
        isInstanceOfType(doc.contents.get(0), QMMarker.class);
        Assert.assertEquals("God is on your side.", ((TextBlock) doc.contents.get(0).contents.get(0)).text);
        Assert.assertEquals(1, ((QMMarker) parser.parseFromString("\\qm God is on your side.").contents.get(0)).depth);
        Assert.assertEquals(1, ((QMMarker) parser.parseFromString("\\qm1 God is on your side.").contents.get(0)).depth);
        Assert.assertEquals(2, ((QMMarker) parser.parseFromString("\\qm2 God is on your side.").contents.get(0)).depth);
        Assert.assertEquals(3, ((QMMarker) parser.parseFromString("\\qm3 God is on your side.").contents.get(0)).depth);
        doc = parser.parseFromString("\\qa Aleph");
        isInstanceOfType(doc.contents.get(0), QAMarker.class);
        QAMarker qa = (QAMarker) doc.contents.get(0);
        Assert.assertEquals("Aleph", qa.heading);
    }

    @Test
    public void testCharacterStylingParse() throws Exception {
        isInstanceOfType(parser.parseFromString("\\v 21 Penduduk kota yang satu akan pergi \\em Emphasis \\em* ").contents.get(0).contents.get(1), EMMarker.class);
        isInstanceOfType(parser.parseFromString("\\v 21 Penduduk kota yang satu akan pergi \\bd Boldness \\bd* ").contents.get(0).contents.get(1), BDMarker.class);
        isInstanceOfType(parser.parseFromString("\\v 21 Penduduk kota yang satu akan pergi \\bdit Boldness and Italics \\bdit* ").contents.get(0).contents.get(1), BDITMarker.class);
        isInstanceOfType(parser.parseFromString("\\v 21 Penduduk kota yang satu akan pergi \\it Italics \\it* ").contents.get(0).contents.get(1), ITMarker.class);
        isInstanceOfType(parser.parseFromString("\\v 21 Penduduk kota yang satu akan pergi \\sup Superscript \\sup* ").contents.get(0).contents.get(1), SUPMarker.class);
        isInstanceOfType(parser.parseFromString("\\v 21 Penduduk kota yang satu akan pergi \\nd Name of Diety \\nd* ").contents.get(0).contents.get(1), NDMarker.class);
        isInstanceOfType(parser.parseFromString("\\v 21 Penduduk kota yang satu akan pergi \\sc Small Caps \\sc* ").contents.get(0).contents.get(1), SCMarker.class);
        isInstanceOfType(parser.parseFromString("\\v 21 Penduduk kota yang satu akan pergi \\no Normal \\no* ").contents.get(0).contents.get(1), NOMarker.class);
        // Text Content
        Assert.assertEquals("Emphasis", ((TextBlock) parser.parseFromString("\\v 21 Penduduk kota yang satu akan pergi \\em Emphasis \\em* ").contents.get(0).contents.get(1).contents.get(0)).text);
        Assert.assertEquals("Boldness", ((TextBlock) parser.parseFromString("\\v 21 Penduduk kota yang satu akan pergi \\bd Boldness \\bd* ").contents.get(0).contents.get(1).contents.get(0)).text);
        Assert.assertEquals("Boldness and Italics", ((TextBlock) parser.parseFromString("\\v 21 Penduduk kota yang satu akan pergi \\bdit Boldness and Italics \\bdit* ").contents.get(0).contents.get(1).contents.get(0)).text);
        Assert.assertEquals("Italics", ((TextBlock) parser.parseFromString("\\v 21 Penduduk kota yang satu akan pergi \\it Italics \\it* ").contents.get(0).contents.get(1).contents.get(0)).text);
        Assert.assertEquals("Superscript", ((TextBlock) parser.parseFromString("\\v 21 Penduduk kota yang satu akan pergi \\sup Superscript \\sup* ").contents.get(0).contents.get(1).contents.get(0)).text);
        Assert.assertEquals("Name of Diety", ((TextBlock) parser.parseFromString("\\v 21 Penduduk kota yang satu akan pergi \\nd Name of Diety \\nd* ").contents.get(0).contents.get(1).contents.get(0)).text);
        Assert.assertEquals("Small Caps", ((TextBlock) parser.parseFromString("\\v 21 Penduduk kota yang satu akan pergi \\sc Small Caps \\sc* ").contents.get(0).contents.get(1).contents.get(0)).text);
        Assert.assertEquals("Normal", ((TextBlock) parser.parseFromString("\\v 21 Penduduk kota yang satu akan pergi \\no Normal \\no* ").contents.get(0).contents.get(1).contents.get(0)).text);
    }

    @Test
    public void testUnknownMarkerParse() throws Exception {
        Assert.assertEquals(" what is yy?", ((UnknownMarker) parser.parseFromString("\\yy what is yy?").contents.get(0)).parsedValue);
        Assert.assertEquals("yy", ((UnknownMarker) parser.parseFromString("\\yy what is yy?").contents.get(0)).parsedIdentifier);
        Assert.assertEquals(" what is z?", ((UnknownMarker) parser.parseFromString("\\z what is z?").contents.get(0)).parsedValue);
        Assert.assertEquals("z", ((UnknownMarker) parser.parseFromString("\\z what is z?").contents.get(0)).parsedIdentifier);
        Assert.assertEquals(" what is 1?", ((UnknownMarker) parser.parseFromString("\\1 what is 1?").contents.get(0)).parsedValue);
        Assert.assertEquals("1", ((UnknownMarker) parser.parseFromString("\\1  what is 1?").contents.get(0)).parsedIdentifier);
    }

    @Test
    public void testWhitespacePreserve() throws Exception {
        String verseText = "This is verse text ";
        String otherVerseText = " after the word";
        USFMDocument output = parser.parseFromString("\\v 1 " + verseText + "\\bd Bold \\bd*" + otherVerseText);
        Assert.assertEquals(verseText, ((TextBlock) output.contents.get(0).contents.get(0)).text);
        Assert.assertEquals(otherVerseText, ((TextBlock) output.contents.get(0).contents.get(3)).text);
    }

    @Test
    public void testVersePoetryNesting() throws Exception {
        String verseText = "\\q \\v 1 This is verse one \\q another poetry \\v 2 second verse";
        USFMDocument output = parser.parseFromString(verseText);
        Assert.assertEquals(2, output.contents.size());
        Assert.assertTrue(output.contents.get(0) instanceof QMarker);
        Assert.assertTrue(output.contents.get(0).contents.get(0) instanceof VMarker);
        Assert.assertTrue(output.contents.get(0).contents.get(0).contents.get(1) instanceof QMarker);
        Assert.assertTrue(output.contents.get(1) instanceof VMarker);

        String secondVerseText = "\\v 1 This is verse one \\q another poetry \\v 2 second verse";

        output = parser.parseFromString(secondVerseText);
        Assert.assertEquals(2, output.contents.size());
        Assert.assertTrue(output.contents.get(0) instanceof VMarker);
        Assert.assertTrue(output.contents.get(0).contents.get(1) instanceof QMarker);
        Assert.assertTrue(output.contents.get(1) instanceof VMarker);
    }

    @Test
    public void TestEmptyQMarkerInVerse() {
        String verseText = "\\v 1 This is verse one \\q \\v 2 second verse";
        var output = parser.parseFromString(verseText);
        Assert.assertEquals(2, output.contents.size());
        Assert.assertTrue(output.contents.get(0) instanceof VMarker);
        Assert.assertTrue(output.contents.get(1) instanceof QMarker && ((QMarker)output.contents.get(1)).isPoetryBlock);
        Assert.assertTrue(output.contents.get(1).contents.get(0) instanceof VMarker);
    }

    @Test
    public void testBadChapterHandling() throws Exception {
        String verseText = "\\c 1 Bad text here";
        USFMDocument output = parser.parseFromString(verseText);
        Assert.assertEquals(1, output.contents.size());
        Assert.assertTrue(output.contents.get(0).contents.get(0) instanceof TextBlock);
        Assert.assertEquals(1, ((CMarker) output.contents.get(0)).number);
        Assert.assertEquals("Bad text here", ((TextBlock) output.contents.get(0).contents.get(0)).text);
    }

    @Test
    public void testNoChapterNumberingHandling() throws Exception {
        String verseText = "\\c \\v 1 Bad text here";
        USFMDocument output = parser.parseFromString(verseText);
        Assert.assertEquals(1, output.contents.size());
        Assert.assertTrue(output.contents.get(0) instanceof CMarker);
        Assert.assertEquals(0, ((CMarker) output.contents.get(0)).number);
    }

    @Test
    public void testNoChapterNumberingAndTextHandling() throws Exception {
        String verseText = "\\c Text Block \\v 1 Bad text here";
        USFMDocument output = parser.parseFromString(verseText);
        Assert.assertEquals(1, output.contents.size());
        Assert.assertTrue(output.contents.get(0) instanceof CMarker);
        Assert.assertEquals(0, ((CMarker) output.contents.get(0)).number);
        Assert.assertEquals(2, output.contents.get(0).contents.size());
        Assert.assertTrue(output.contents.get(0).contents.get(0) instanceof TextBlock);
        Assert.assertEquals("Text Block", ((TextBlock) output.contents.get(0).contents.get(0)).text);
    }

    @Test
    public void testCorrectFQAEndMarkerNesting() throws Exception {
        String verseText = "\\f + \\ft Text \\fqa Other \\fqa* More";
        USFMDocument output = parser.parseFromString(verseText);
        // Make sure the FMarker has only one child
        Assert.assertEquals(1, output.contents.get(0).contents.size());
    }

    private void isInstanceOfType(Object obj, Class clazz) {
        Assert.assertThat(obj, instanceOf(clazz));
    }

    /**
     * Verify that if a \q marker is at the end of a string it doesn't throw an exception
     */
    @Test
    public void TestTrailingEmptyQMarker()
    {
        String verseText = "\\q";
        var output = parser.parseFromString(verseText);
        isInstanceOfType(output.contents.get(0), QMarker.class);
    }
}
