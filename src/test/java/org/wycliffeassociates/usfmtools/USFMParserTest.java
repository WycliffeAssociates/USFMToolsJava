package org.wycliffeassociates.usfmtools;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wycliffeassociates.usfmtools.models.markers.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;

public class USFMParserTest   
{
    private USFMParser parser;

    @Before
    public void setupTest() throws Exception {
        parser = new USFMParser();
    }

    @Test
    public void testIgnoredTags() throws Exception {
        parser = new USFMParser(new ArrayList<String>(List.of("bd", "bd*")));
        USFMDocument doc = parser.ParseFromString("\\v 1 In the beginning \\bd God \\bd*");
        Assert.assertEquals(1, doc.Contents.size());
        VMarker vm = (VMarker)doc.Contents.get(0);
        Assert.assertEquals(1, vm.Contents.size());
        TextBlock tb = (TextBlock)vm.Contents.get(0);
        Assert.assertEquals(0, tb.Contents.size());
        Assert.assertEquals("In the beginning ", tb.Text);
    }

    @Test
    public void testIdentificationMarkers() throws Exception {
        Assert.assertEquals("Genesis", ((IDMarker)parser.ParseFromString("\\id Genesis").Contents.get(0)).TextIdentifier);
        Assert.assertEquals("UTF-8", ((IDEMarker)parser.ParseFromString("\\ide UTF-8").Contents.get(0)).Encoding);
        Assert.assertEquals("2", ((STSMarker)parser.ParseFromString("\\sts 2").Contents.get(0)).StatusText);
        Assert.assertEquals("3.0", ((USFMMarker)parser.ParseFromString("\\usfm 3.0").Contents.get(0)).getVersion());
        USFMDocument doc = parser.ParseFromString("\\rem Remark");
        Assert.assertThat(doc.Contents.get(0), instanceOf(REMMarker.class));
        REMMarker rem = (REMMarker)doc.Contents.get(0);
        Assert.assertEquals("Remark", rem.Comment);
    }

    @Test
    public void testIntroductionMarkers() throws Exception {
        Assert.assertEquals("Title", ((IMTMarker)parser.ParseFromString("\\imt Title").Contents.get(0)).IntroTitle);
        Assert.assertEquals(1, ((IMTMarker)parser.ParseFromString("\\imt").Contents.get(0)).Weight);
        Assert.assertEquals(1, ((IMTMarker)parser.ParseFromString("\\imt1").Contents.get(0)).Weight);
        Assert.assertEquals(2, ((IMTMarker)parser.ParseFromString("\\imt2").Contents.get(0)).Weight);
        Assert.assertEquals(3, ((IMTMarker)parser.ParseFromString("\\imt3").Contents.get(0)).Weight);
        Assert.assertEquals("Heading", ((ISMarker)parser.ParseFromString("\\is Heading").Contents.get(0)).Heading);
        Assert.assertEquals(1, ((ISMarker)parser.ParseFromString("\\is").Contents.get(0)).Weight);
        Assert.assertEquals(1, ((ISMarker)parser.ParseFromString("\\is1").Contents.get(0)).Weight);
        Assert.assertEquals(2, ((ISMarker)parser.ParseFromString("\\is2").Contents.get(0)).Weight);
        Assert.assertEquals(3, ((ISMarker)parser.ParseFromString("\\is3").Contents.get(0)).Weight);
        Assert.assertEquals(1, ((IQMarker)parser.ParseFromString("\\iq").Contents.get(0)).Depth);
        Assert.assertEquals(1, ((IQMarker)parser.ParseFromString("\\iq1").Contents.get(0)).Depth);
        Assert.assertEquals(2, ((IQMarker)parser.ParseFromString("\\iq2").Contents.get(0)).Depth);
        Assert.assertEquals(3, ((IQMarker)parser.ParseFromString("\\iq3").Contents.get(0)).Depth);
        Assert.assertNotNull(((IBMarker)parser.ParseFromString("\\ib").Contents.get(0)));
        Assert.assertEquals("Title", ((IOTMarker)parser.ParseFromString("\\iot Title").Contents.get(0)).Title);
        Assert.assertEquals(1, ((IOMarker)parser.ParseFromString("\\io").Contents.get(0)).Depth);
        Assert.assertEquals(1, ((IOMarker)parser.ParseFromString("\\io1").Contents.get(0)).Depth);
        Assert.assertEquals(2, ((IOMarker)parser.ParseFromString("\\io2").Contents.get(0)).Depth);
        Assert.assertEquals(3, ((IOMarker)parser.ParseFromString("\\io3").Contents.get(0)).Depth);
        USFMDocument doc = parser.ParseFromString("\\ior (1.1-3)\\ior*");
        Assert.assertEquals(2, doc.Contents.size());
        Assert.assertEquals("(1.1-3)", ((TextBlock)doc.Contents.get(0).Contents.get(0)).Text);
        Assert.assertEquals("Text", ((TextBlock)parser.ParseFromString("\\ili Text").Contents.get(0).Contents.get(0)).Text);
        Assert.assertEquals(1, ((ILIMarker)parser.ParseFromString("\\ili").Contents.get(0)).Depth);
        Assert.assertEquals(1, ((ILIMarker)parser.ParseFromString("\\ili1").Contents.get(0)).Depth);
        Assert.assertEquals(2, ((ILIMarker)parser.ParseFromString("\\ili2").Contents.get(0)).Depth);
        Assert.assertEquals(3, ((ILIMarker)parser.ParseFromString("\\ili3").Contents.get(0)).Depth);
        doc = parser.ParseFromString("\\ip Text");
        isInstanceOfType(doc.Contents.get(0), IPMarker.class);
        Assert.assertEquals("Text", ((TextBlock)doc.Contents.get(0).Contents.get(0)).Text);
        doc = parser.ParseFromString("\\ipi Text");
        isInstanceOfType(doc.Contents.get(0), IPIMarker.class);
        Assert.assertEquals("Text", ((TextBlock)doc.Contents.get(0).Contents.get(0)).Text);
        doc = parser.ParseFromString("\\im Text");
        isInstanceOfType(doc.Contents.get(0), IMMarker.class);
        Assert.assertEquals("Text", ((TextBlock)doc.Contents.get(0).Contents.get(0)).Text);
        doc = parser.ParseFromString("\\is Heading");
        isInstanceOfType(doc.Contents.get(0), ISMarker.class);
        Assert.assertEquals("Heading", ((ISMarker)doc.Contents.get(0)).Heading);
        doc = parser.ParseFromString("\\iq Quote");
        isInstanceOfType(doc.Contents.get(0), IQMarker.class);
        Assert.assertEquals("Quote", ((TextBlock)doc.Contents.get(0).Contents.get(0)).Text);
        Assert.assertEquals(1, ((IQMarker)parser.ParseFromString("\\iq Quote").Contents.get(0)).Depth);
        Assert.assertEquals(1, ((IQMarker)parser.ParseFromString("\\iq1 Quote").Contents.get(0)).Depth);
        Assert.assertEquals(2, ((IQMarker)parser.ParseFromString("\\iq2 Quote").Contents.get(0)).Depth);
        Assert.assertEquals(3, ((IQMarker)parser.ParseFromString("\\iq3 Quote").Contents.get(0)).Depth);
        doc = parser.ParseFromString("\\imi Text");
        isInstanceOfType(doc.Contents.get(0), IMIMarker.class);
        Assert.assertEquals("Text", ((TextBlock)doc.Contents.get(0).Contents.get(0)).Text);
        doc = parser.ParseFromString("\\ipq Text");
        isInstanceOfType(doc.Contents.get(0), IPQMarker.class);
        Assert.assertEquals("Text", ((TextBlock)doc.Contents.get(0).Contents.get(0)).Text);
        doc = parser.ParseFromString("\\imq Text");
        isInstanceOfType(doc.Contents.get(0), IMQMarker.class);
        Assert.assertEquals("Text", ((TextBlock)doc.Contents.get(0).Contents.get(0)).Text);
        doc = parser.ParseFromString("\\ipr Text");
        isInstanceOfType(doc.Contents.get(0), IPRMarker.class);
        Assert.assertEquals("Text", ((TextBlock)doc.Contents.get(0).Contents.get(0)).Text);
    }

    @Test
    public void testSectionParse() throws Exception {
        // Section Headings
        Assert.assertEquals("Silsilah Yesus Kristus ", ((SMarker)parser.ParseFromString("\\s Silsilah Yesus Kristus \\r (Luk. 3:23 - 38)").Contents.get(0)).Text);
        Assert.assertEquals("Kumpulkanlah Harta di Surga ", ((SMarker)parser.ParseFromString("\\s3 Kumpulkanlah Harta di Surga \\r (Luk. 12:33 - 34; 11:34 - 36; 16:13)").Contents.get(0)).Text);
        Assert.assertEquals(1, ((SMarker)parser.ParseFromString("\\s Silsilah Yesus Kristus \\r (Luk. 3:23 - 38)").Contents.get(0)).Weight);
        Assert.assertEquals(2, ((SMarker)parser.ParseFromString("\\s2 Silsilah Yesus Kristus \\r (Luk. 3:23 - 38)").Contents.get(0)).Weight);
        Assert.assertEquals(3, ((SMarker)parser.ParseFromString("\\s3 Silsilah Yesus Kristus \\r (Luk. 3:23 - 38)").Contents.get(0)).Weight);
        // Major Section
        Assert.assertEquals("jilid 1 ", ((MSMarker)parser.ParseFromString("\\ms1 jilid 1 \\mr (Mazmur 1 - 41)").Contents.get(0)).Heading);
        Assert.assertEquals("jilid 1 ", ((MSMarker)parser.ParseFromString("\\ms2 jilid 1 \\mr (Mazmur 1 - 41)").Contents.get(0)).Heading);
        Assert.assertEquals(3, ((MSMarker)parser.ParseFromString("\\ms3 jilid 1 \\mr (Mazmur 1 - 41)").Contents.get(0)).Weight);
        Assert.assertEquals(1, ((MSMarker)parser.ParseFromString("\\ms jilid 1 \\mr (Mazmur 1 - 41)").Contents.get(0)).Weight);
        // References
        Assert.assertEquals("(Mazmur 1 - 41)", ((MRMarker)parser.ParseFromString("\\ms2 jilid 1 \\mr (Mazmur 1 - 41)").Contents.get(0).Contents.get(0)).SectionReference);
        Assert.assertEquals("(Mazmur 41)", ((MRMarker)parser.ParseFromString("\\ms2 jilid 1 \\mr (Mazmur 41)").Contents.get(0).Contents.get(0)).SectionReference);
        Assert.assertEquals("(Mazmur)", ((MRMarker)parser.ParseFromString("\\ms2 jilid 1 \\mr (Mazmur)").Contents.get(0).Contents.get(0)).SectionReference);
    }

    @Test
    public void testTableOfContentsParse() throws Exception {
        // Table of Contents
        Assert.assertEquals("Keluaran", ((TOC1Marker)parser.ParseFromString("\\toc1 Keluaran").Contents.get(0)).LongTableOfContentsText);
        Assert.assertEquals("Keluaran", ((TOC2Marker)parser.ParseFromString("\\toc2 Keluaran").Contents.get(0)).ShortTableOfContentsText);
        Assert.assertEquals("Kel", ((TOC3Marker)parser.ParseFromString("\\toc3 Kel").Contents.get(0)).BookAbbreviation);
        // Alternate Table of Contents
        Assert.assertEquals("Keluaran", ((TOCA1Marker)parser.ParseFromString("\\toca1 Keluaran").Contents.get(0)).AltLongTableOfContentsText);
        Assert.assertEquals("Keluaran", ((TOCA2Marker)parser.ParseFromString("\\toca2 Keluaran").Contents.get(0)).AltShortTableOfContentsText);
        Assert.assertEquals("Kel", ((TOCA3Marker)parser.ParseFromString("\\toca3 Kel").Contents.get(0)).AltBookAbbreviation);
    }

    @Test
    public void testMajorTitleParse() throws Exception {
        Assert.assertEquals("Keluaran", ((MTMarker)parser.ParseFromString("\\mt1 Keluaran").Contents.get(0)).Title);
        Assert.assertEquals("Keluaran", ((MTMarker)parser.ParseFromString("\\mt3 Keluaran").Contents.get(0)).Title);
        Assert.assertEquals(1, ((MTMarker)parser.ParseFromString("\\mt Keluaran").Contents.get(0)).Weight);
        Assert.assertEquals(2, ((MTMarker)parser.ParseFromString("\\mt2 Keluaran").Contents.get(0)).Weight);
    }

    @Test
    public void testHeaderParse() throws Exception {
        Assert.assertEquals("Genesis", ((HMarker)parser.ParseFromString("\\h Genesis").Contents.get(0)).HeaderText);
        Assert.assertEquals("", ((HMarker)parser.ParseFromString("\\h").Contents.get(0)).HeaderText);
        Assert.assertEquals("1 John", ((HMarker)parser.ParseFromString("\\h 1 John").Contents.get(0)).HeaderText);
        Assert.assertEquals("", ((HMarker)parser.ParseFromString("\\h   ").Contents.get(0)).HeaderText);
        USFMDocument doc = parser.ParseFromString("\\sp Job");
        SPMarker sp = (SPMarker)doc.Contents.get(0);
        Assert.assertEquals("Job", sp.Speaker);
    }

    @Test
    public void testChapterParse() throws Exception {
        Assert.assertEquals(1, ((CMarker)parser.ParseFromString("\\c 1").Contents.get(0)).Number);
        Assert.assertEquals(1000, ((CMarker)parser.ParseFromString("\\c 1000").Contents.get(0)).Number);
        Assert.assertEquals(0, ((CMarker)parser.ParseFromString("\\c 0").Contents.get(0)).Number);
        // Chapter Labels
        Assert.assertEquals("Chapter One", ((CLMarker)parser.ParseFromString("\\c 1 \\cl Chapter One").Contents.get(0).Contents.get(0)).Label);
        Assert.assertEquals("Chapter One", ((CLMarker)parser.ParseFromString("\\cl Chapter One \\c 1").Contents.get(0)).Label);
        Assert.assertEquals("Chapter Two", ((CLMarker)parser.ParseFromString("\\c 1 \\cl Chapter One \\c 2 \\cl Chapter Two").Contents.get(1).Contents.get(0)).Label);
        USFMDocument doc = parser.ParseFromString("\\cp Q");
        isInstanceOfType(doc.Contents.get(0), CPMarker.class);
        Assert.assertEquals("Q", ((CPMarker)doc.Contents.get(0)).PublishedChapterMarker);
        doc = parser.ParseFromString("\\ca 53 \\ca*");
        Assert.assertEquals(2, doc.Contents.size());
        CAMarker caBegin = (CAMarker)doc.Contents.get(0);
        CAEndMarker caEnd = (CAEndMarker)doc.Contents.get(1);
        Assert.assertEquals("53", caBegin.AltChapterNumber);
        doc = parser.ParseFromString("\\va 22 \\va*");
        Assert.assertEquals(2, doc.Contents.size());
        VAMarker vaBegin = (VAMarker)doc.Contents.get(0);
        VAEndMarker vaEnd = (VAEndMarker)doc.Contents.get(1);
        Assert.assertEquals("22", vaBegin.AltVerseNumber);
        doc = parser.ParseFromString("\\p In the beginning God created the heavens and the earth.");
        isInstanceOfType(doc.Contents.get(0), PMarker.class);
        Assert.assertEquals("In the beginning God created the heavens and the earth.", ((TextBlock)doc.Contents.get(0).Contents.get(0)).Text);
        doc = parser.ParseFromString("\\pc In the beginning God created the heavens and the earth.");
        isInstanceOfType(doc.Contents.get(0), PCMarker.class);
        Assert.assertEquals("In the beginning God created the heavens and the earth.", ((TextBlock)doc.Contents.get(0).Contents.get(0)).Text);
        doc = parser.ParseFromString("\\p \\v 1 In the beginning God created the heavens and the earth.");
        isInstanceOfType(doc.Contents.get(0), PMarker.class);
        PMarker pm = (PMarker)doc.Contents.get(0);
        VMarker vm = (VMarker)pm.Contents.get(0);
        Assert.assertEquals("In the beginning God created the heavens and the earth.", ((TextBlock)vm.Contents.get(0)).Text);
        doc = parser.ParseFromString("\\mi");
        Assert.assertEquals(1, doc.Contents.size());
        isInstanceOfType(doc.Contents.get(0), MIMarker.class);
        doc = parser.ParseFromString("\\d A Psalm of David");
        Assert.assertEquals("A Psalm of David", ((DMarker)doc.Contents.get(0)).Description);
        doc = parser.ParseFromString("\\nb");
        isInstanceOfType(doc.Contents.get(0), NBMarker.class);
        doc = parser.ParseFromString("\\fq the Son of God");
        isInstanceOfType(doc.Contents.get(0), FQMarker.class);
        Assert.assertEquals("the Son of God", ((TextBlock)doc.Contents.get(0).Contents.get(0)).Text);
        doc = parser.ParseFromString("\\pi The one who scattered");
        isInstanceOfType(doc.Contents.get(0), PIMarker.class);
        Assert.assertEquals(1, doc.Contents.size());
        Assert.assertEquals("The one who scattered", ((TextBlock)doc.Contents.get(0).Contents.get(0)).Text);
        Assert.assertEquals(1, ((PIMarker)parser.ParseFromString("\\pi").Contents.get(0)).Depth);
        Assert.assertEquals(1, ((PIMarker)parser.ParseFromString("\\pi1").Contents.get(0)).Depth);
        Assert.assertEquals(2, ((PIMarker)parser.ParseFromString("\\pi2").Contents.get(0)).Depth);
        Assert.assertEquals(3, ((PIMarker)parser.ParseFromString("\\pi3").Contents.get(0)).Depth);
        doc = parser.ParseFromString("\\m \\v 37 David himself called him 'Lord';");
        Assert.assertEquals(1, doc.Contents.size());
        MMarker mm = (MMarker)doc.Contents.get(0);
        Assert.assertEquals(1, mm.Contents.size());
        vm = (VMarker)mm.Contents.get(0);
        Assert.assertEquals("David himself called him 'Lord';", ((TextBlock)vm.Contents.get(0)).Text);
        doc = parser.ParseFromString("\\b");
        Assert.assertEquals(1, doc.Contents.size());
        isInstanceOfType(doc.Contents.get(0), BMarker.class);
    }

    @Test
    public void testVerseParse() throws Exception {
        Assert.assertEquals("9", ((VMarker)parser.ParseFromString("\\v 9 Yahweh God called to the man and said to him, \"Where are you?\"").Contents.get(0)).VerseNumber);
        Assert.assertEquals("26", ((VMarker)(parser.ParseFromString("\\v 26 God said, \"Let us make man in our image, after our likeness. Let them have dominion over the fish of the sea, over the birds of the sky, over the livestock, over all the earth, and over every creeping thing that creeps on the earth.\" \\f + \\ft Some ancient copies have: \\fqa ... Over the livestock, over all the animals of the earth, and over every creeping thing that creeps on the earth \\fqa*  . \\f*").Contents.get(0))).VerseNumber);
        Assert.assertEquals("0", ((VMarker)parser.ParseFromString("\\v 0 Not in the Bible").Contents.get(0)).VerseNumber);
        Assert.assertEquals("1-2", ((VMarker)parser.ParseFromString("\\v 1-2 Not in the Bible").Contents.get(0)).VerseNumber);
        // References - Quoted book title - Parallel passage reference
        Assert.assertEquals("(Luk. 3:23 - 38)", ((TextBlock)parser.ParseFromString("\\s Silsilah Yesus Kristus \\r (Luk. 3:23 - 38)").Contents.get(0).Contents.get(0).Contents.get(0)).Text);
        Assert.assertEquals("(Luk. 12:33 - 34; 11:34 - 36; 16:13)", ((TextBlock)parser.ParseFromString("\\s Kumpulkanlah Harta di Surga \\r (Luk. 12:33 - 34; 11:34 - 36; 16:13)").Contents.get(0).Contents.get(0).Contents.get(0)).Text);
        Assert.assertEquals("Kitab Peperangan TUHAN,", ((BKMarker)parser.ParseFromString("\\v 14 Itulah sebabnya kata-kata ini ditulis dalam \\bk Kitab Peperangan TUHAN,\\bk*").Contents.get(0).Contents.get(1)).BookTitle);
        Assert.assertEquals("Psa 2.7", ((TextBlock)parser.ParseFromString("\\v 5 For God never said to any of his angels,\\q1 \"You are my Son;\\q2 today I have become your Father.\"\\rq Psa 2.7\\rq* ").Contents.get(0).Contents.get(3).Contents.get(0)).Text);
        // Closing - Selah
        Assert.assertEquals("[[ayt.co/Mat]]", ((TextBlock)parser.ParseFromString("\\cls [[ayt.co/Mat]]").Contents.get(0).Contents.get(0)).Text);
        Assert.assertEquals("Sela", ((TextBlock)parser.ParseFromString("\\v 3 Allah datang dari negeri Teman \\q2 dan Yang Mahakudus datang dari Gunung Paran. \\qs Sela \\qs* ").Contents.get(0).Contents.get(1).Contents.get(1).Contents.get(0)).Text);
        Assert.assertEquals("Sela", ((TextBlock)parser.ParseFromString("\\q2 dan sampai batu yang penghabisan. \\qs Sela \\qs*").Contents.get(0).Contents.get(1).Contents.get(0)).Text);
        // Transliterated
        Assert.assertEquals("Hades", ((TextBlock)parser.ParseFromString("\\f + \\fr 10:15 \\fk dunia orang mati \\ft Dalam bahasa Yunani adalah \\tl Hades\\tl* \\ft , tempat orang setelah meninggal.\\f*").Contents.get(0).Contents.get(2).Contents.get(1).Contents.get(0)).Text);
        Assert.assertEquals("TEKEL", ((TextBlock)parser.ParseFromString("\\v 27 \\tl TEKEL\\tl* :").Contents.get(0).Contents.get(0).Contents.get(0)).Text);
    }

    @Test
    public void testTableParse() throws Exception {
        isInstanceOfType(parser.ParseFromString("\\tr \\tc1 dari suku Ruben \\tcr2 12.000").Contents.get(0), TableBlock.class);
        // Table Rows - Cells
        isInstanceOfType(parser.ParseFromString("\\tr \\tc1 dari suku Ruben \\tcr2 12.000").Contents.get(0).Contents.get(0), TRMarker.class);
        Assert.assertEquals("dari suku Ruben", ((TextBlock)parser.ParseFromString("\\tr \\tc1 dari suku Ruben \\tcr2 12.000").Contents.get(0).Contents.get(0).Contents.get(0).Contents.get(0)).Text);
        Assert.assertEquals("12.000", ((TextBlock)parser.ParseFromString("\\tr \\tc1 dari suku Ruben \\tcr2 12.000").Contents.get(0).Contents.get(0).Contents.get(1).Contents.get(0)).Text);
        Assert.assertEquals(1, ((TCMarker)parser.ParseFromString("\\tr \\tc1 dari suku Ruben \\tcr2 12.000").Contents.get(0).Contents.get(0).Contents.get(0)).ColumnPosition);
        Assert.assertEquals(2, ((TCMarker)parser.ParseFromString("\\tr \\tc2 dari suku Ruben \\tcr2 12.000").Contents.get(0).Contents.get(0).Contents.get(0)).ColumnPosition);
        Assert.assertEquals(3, ((TCMarker)parser.ParseFromString("\\tr \\tc3 dari suku Ruben \\tcr2 12.000").Contents.get(0).Contents.get(0).Contents.get(0)).ColumnPosition);
        Assert.assertEquals(1, ((TCRMarker)parser.ParseFromString("\\tr \\tc1 dari suku Ruben \\tcr1 12.000").Contents.get(0).Contents.get(0).Contents.get(1)).ColumnPosition);
        Assert.assertEquals(2, ((TCRMarker)parser.ParseFromString("\\tr \\tc1 dari suku Ruben \\tcr2 12.000").Contents.get(0).Contents.get(0).Contents.get(1)).ColumnPosition);
        Assert.assertEquals(3, ((TCRMarker)parser.ParseFromString("\\tr \\tc1 dari suku Ruben \\tcr3 12.000").Contents.get(0).Contents.get(0).Contents.get(1)).ColumnPosition);
        // Embedded Verse
        Assert.assertEquals("6", ((VMarker)parser.ParseFromString("\\tc1 \\v 6 dari suku Asyer").Contents.get(0).Contents.get(0)).VerseNumber);
        // Table Headers
        Assert.assertEquals("dari suku Ruben", ((TextBlock)parser.ParseFromString("\\tr \\th1 dari suku Ruben \\thr2 12.000").Contents.get(0).Contents.get(0).Contents.get(0).Contents.get(0)).Text);
        Assert.assertEquals("12.000", ((TextBlock)parser.ParseFromString("\\tr \\th1 dari suku Ruben \\thr2 12.000").Contents.get(0).Contents.get(0).Contents.get(1).Contents.get(0)).Text);
        Assert.assertEquals(1, ((THMarker)parser.ParseFromString("\\tr \\th1 dari suku Ruben \\thr2 12.000").Contents.get(0).Contents.get(0).Contents.get(0)).ColumnPosition);
        Assert.assertEquals(2, ((THMarker)parser.ParseFromString("\\tr \\th2 dari suku Ruben \\thr 12.000").Contents.get(0).Contents.get(0).Contents.get(0)).ColumnPosition);
        Assert.assertEquals(3, ((THMarker)parser.ParseFromString("\\tr \\th3 dari suku Ruben \\thr 12.000").Contents.get(0).Contents.get(0).Contents.get(0)).ColumnPosition);
        Assert.assertEquals(1, ((THRMarker)parser.ParseFromString("\\tr \\th1 dari suku Ruben \\thr1 12.000").Contents.get(0).Contents.get(0).Contents.get(1)).ColumnPosition);
        Assert.assertEquals(2, ((THRMarker)parser.ParseFromString("\\tr \\th1 dari suku Ruben \\thr2 12.000").Contents.get(0).Contents.get(0).Contents.get(1)).ColumnPosition);
        Assert.assertEquals(3, ((THRMarker)parser.ParseFromString("\\tr \\th1 dari suku Ruben \\thr3 12.000").Contents.get(0).Contents.get(0).Contents.get(1)).ColumnPosition);
    }

    @Test
    public void testListParse() throws Exception {
        // List Items
        Assert.assertEquals("Peres ayah Hezron.", ((TextBlock)parser.ParseFromString("\\li Peres ayah Hezron.").Contents.get(0).Contents.get(0)).Text);
        Assert.assertEquals(1, ((LIMarker)parser.ParseFromString("\\li Peres ayah Hezron.").Contents.get(0)).Depth);
        Assert.assertEquals(1, ((LIMarker)parser.ParseFromString("\\li1 Peres ayah Hezron.").Contents.get(0)).Depth);
        Assert.assertEquals(2, ((LIMarker)parser.ParseFromString("\\li2 Peres ayah Hezron.").Contents.get(0)).Depth);
        Assert.assertEquals(3, ((LIMarker)parser.ParseFromString("\\li3 Peres ayah Hezron.").Contents.get(0)).Depth);
        // Verse within List
        Assert.assertEquals("19", ((VMarker)parser.ParseFromString("\\li Peres ayah Hezron. \\li \\v 19 Hezron ayah Ram.").Contents.get(1).Contents.get(0)).VerseNumber);
    }

    @Test
    public void testFootnoteParse() throws Exception {
        // Footnote Text Marker
        Assert.assertEquals("Sample Simple Footnote. ", ((TextBlock)parser.ParseFromString("\\f + \\ft Sample Simple Footnote. \\f*").Contents.get(0).Contents.get(0).Contents.get(0)).Text);
        // Footnote Caller
        Assert.assertEquals("+", ((FMarker)parser.ParseFromString("\\f + \\ft Sample Simple Footnote. \\f*").Contents.get(0)).FootNoteCaller);
        Assert.assertEquals("-", ((FMarker)parser.ParseFromString("\\f - \\ft Sample Simple Footnote. \\f*").Contents.get(0)).FootNoteCaller);
        Assert.assertEquals("abc", ((FMarker)parser.ParseFromString("\\f abc \\ft Sample Simple Footnote. \\f*").Contents.get(0)).FootNoteCaller);
        // Footnote Alternate Translation Marker
        Assert.assertEquals("... Over the livestock, over all the animals of the earth, and over every creeping thing that creeps on the earth ", ((TextBlock)parser.ParseFromString("\\v 26 God said, \"Let us make man in our image, after our likeness. Let them have dominion over the fish of the sea, over the birds of the sky, over the livestock, over all the earth, and over every creeping thing that creeps on the earth.\" \\f + \\ft Some ancient copies have: \\fqa ... Over the livestock, over all the animals of the earth, and over every creeping thing that creeps on the earth \\fqa*  . \\f*").Contents.get(0).Contents.get(1).Contents.get(0).Contents.get(1).Contents.get(0)).Text);
        // Footnote Keyword
        Assert.assertEquals("Tamar", ((FKMarker)parser.ParseFromString("\\f + \\fr 1.3 \\fk Tamar \\ft Menantu Yehuda yang akhirnya menjadi istrinya (bc. Kej. 38:1-30).\\f*").Contents.get(0).Contents.get(1)).FootNoteKeyword);
        //Footnote Reference
        Assert.assertEquals("1.3", ((FRMarker)parser.ParseFromString("\\f + \\fr 1.3 \\fk Tamar \\ft Menantu Yehuda yang akhirnya menjadi istrinya (bc. Kej. 38:1-30).\\f*").Contents.get(0).Contents.get(0)).VerseReference);
        // Footnote Verse Marker - Paragraph
        Assert.assertEquals("56", ((FVMarker)parser.ParseFromString("\\f + \\fr 9:55 \\ft Beberapa salinan Bahasa Yunani menambahkan: Dan ia berkata, Kamu tidak tahu roh apa yang memilikimu. \\fv 56 \\fv* \\ft Anak Manusia tidak datang untuk menghancurkan hidup manusia, tetapi untuk menyelamatkan mereka.\\f*").Contents.get(0).Contents.get(2)).VerseCharacter);
        isInstanceOfType(parser.ParseFromString("\\f + \\fr 17.25 \\ft Kemungkinan maksudnya adalah bebas dari kewajiban pajak seumur hidup. (bdk. NIV. NET) \\fp \\f*").Contents.get(0).Contents.get(2), FPMarker.class);
        // Make sure that a fqa end marker doesn't end up outside of the footnote
        Assert.assertEquals(1, parser.ParseFromString("\\v 1 Words \\f + \\fqa Thing \\fqa* \\f*").Contents.size());
    }

    @Test
    public void testCrossReferenceParse() throws Exception {
        // Cross Reference Caller
        Assert.assertEquals("-", ((XMarker)parser.ParseFromString("\\x - \\xo 11.21 \\xq Tebes \\xt \\x*").Contents.get(0)).CrossRefCaller);
        // Cross Reference Origin
        Assert.assertEquals("11.21", ((XOMarker)parser.ParseFromString("\\x - \\xo 11.21 \\xq Tebes \\xt \\x*").Contents.get(0).Contents.get(0)).OriginRef);
        // Cross Reference Target
        Assert.assertEquals("Mrk 1.24; Luk 2.39; Jhn 1.45.", ((TextBlock)parser.ParseFromString("\\x - \\xo 11.21 \\xq Tebes \\xt Mrk 1.24; Luk 2.39; Jhn 1.45.\\x*").Contents.get(0).Contents.get(2).Contents.get(0)).Text);
        // Cross Reference Quotation
        Assert.assertEquals("Tebes", ((TextBlock)parser.ParseFromString("\\x - \\xo 11.21 \\xq Tebes \\xt \\x*").Contents.get(0).Contents.get(1).Contents.get(0)).Text);
    }

    @Test
    public void testVerseCharacterParse() throws Exception {
        Assert.assertEquals("1a", ((VPMarker)parser.ParseFromString("\\v 1 \\vp 1a \\vp* This is not Scripture").Contents.get(0).Contents.get(0)).VerseCharacter);
        Assert.assertEquals("2b", ((VPMarker)parser.ParseFromString("\\v 2 \\vp 2b \\vp* This is not Scripture").Contents.get(0).Contents.get(0)).VerseCharacter);
        Assert.assertEquals("asdf", ((VPMarker)parser.ParseFromString("\\v 1 \\vp asdf \\vp* This is not Scripture").Contents.get(0).Contents.get(0)).VerseCharacter);
    }

    @Test
    public void testTranslationNotesParse() throws Exception {
        // Translator’s addition
        Assert.assertEquals("dan mencari TUHAN semesta alam!", ((TextBlock)parser.ParseFromString("\\v 21 Penduduk kota yang satu akan pergi kepada penduduk kota yang lain sambil berkata,\\q2 'Mari kita pergi memohon belas kasihan TUHAN\\q1 \\add dan mencari TUHAN semesta alam!\\add * ").Contents.get(0).Contents.get(3).Contents.get(0)).Text);
        Assert.assertEquals("(malaikat)", ((TextBlock)parser.ParseFromString("\\v 1 “Pada tahun pertama pemerintahan Darius, orang Media, aku bangkit untuk menguatkan dan melindungi dia.” \\add (malaikat)\\add* dari Persia.").Contents.get(0).Contents.get(1).Contents.get(0)).Text);
    }

    @Test
    public void testWordEntryParse() throws Exception {
        // Within Footnotes
        Assert.assertEquals("Berhala", ((WMarker)parser.ParseFromString("\\f + \\fr 3:5 \\fk berhala \\ft Lih. \\w Berhala \\w* di Daftar Istilah.\\f*").Contents.get(0).Contents.get(2).Contents.get(1)).Term);
        // Word Entry Attributes
        Assert.assertEquals("Berhala", ((WMarker)parser.ParseFromString("\\f + \\fr 3:5 \\fk berhala \\ft Lih. \\w Berhala|Berhala \\w* di Daftar Istilah.\\f*").Contents.get(0).Contents.get(2).Contents.get(1)).Attributes.get("lemma"));
        Assert.assertEquals("grace", ((WMarker)parser.ParseFromString("\\f + \\fr 3:5 \\fk berhala \\ft Lih. \\w gracious|lemma=\"grace\" \\w* di Daftar Istilah.\\f*").Contents.get(0).Contents.get(2).Contents.get(1)).Attributes.get("lemma"));
        Assert.assertEquals("G5485", ((WMarker)parser.ParseFromString("\\f + \\fr 3:5 \\fk berhala \\ft Lih. \\w gracious|lemma=\"grace\" strong=\"G5485\" \\w* di Daftar Istilah.\\f*").Contents.get(0).Contents.get(2).Contents.get(1)).Attributes.get("strong"));
        Assert.assertEquals("H1234,G5485", ((WMarker)parser.ParseFromString("\\f + \\fr 3:5 \\fk berhala \\ft Lih. \\w gracious|strong=\"H1234,G5485\" \\w* di Daftar Istilah.\\f*").Contents.get(0).Contents.get(2).Contents.get(1)).Attributes.get("strong"));
        Assert.assertEquals("gnt5:51.1.2.1", ((WMarker)parser.ParseFromString("\\f + \\fr 3:5 \\fk berhala \\ft Lih. \\w gracious|lemma=\"grace\" srcloc=\"gnt5:51.1.2.1\" \\w* di Daftar Istilah.\\f*").Contents.get(0).Contents.get(2).Contents.get(1)).Attributes.get("srcloc"));
    }

    @Test
    public void testPoetryParse() throws Exception {
        USFMDocument doc = parser.ParseFromString("\\q Quote");
        isInstanceOfType(doc.Contents.get(0), QMarker.class);
        Assert.assertEquals("Quote", ((TextBlock)doc.Contents.get(0).Contents.get(0)).Text);
        Assert.assertEquals(1, ((QMarker)parser.ParseFromString("\\q Quote").Contents.get(0)).Depth);
        Assert.assertEquals(1, ((QMarker)parser.ParseFromString("\\q1 Quote").Contents.get(0)).Depth);
        Assert.assertEquals(2, ((QMarker)parser.ParseFromString("\\q2 Quote").Contents.get(0)).Depth);
        Assert.assertEquals(3, ((QMarker)parser.ParseFromString("\\q3 Quote").Contents.get(0)).Depth);
        doc = parser.ParseFromString("\\qr God's love never fails.");
        isInstanceOfType(doc.Contents.get(0), QRMarker.class);
        Assert.assertEquals("God's love never fails.", ((TextBlock)doc.Contents.get(0).Contents.get(0)).Text);
        doc = parser.ParseFromString("\\qc Amen! Amen!");
        isInstanceOfType(doc.Contents.get(0), QCMarker.class);
        Assert.assertEquals("Amen! Amen!", ((TextBlock)doc.Contents.get(0).Contents.get(0)).Text);
        doc = parser.ParseFromString("\\qd For the director of music.");
        isInstanceOfType(doc.Contents.get(0), QDMarker.class);
        Assert.assertEquals("For the director of music.", ((TextBlock)doc.Contents.get(0).Contents.get(0)).Text);
        doc = parser.ParseFromString("\\qac P\\qac*");
        Assert.assertEquals(2, doc.Contents.size());
        QACMarker qac = (QACMarker)doc.Contents.get(0);
        QACEndMarker qacEnd = (QACEndMarker)doc.Contents.get(1);
        Assert.assertEquals("P", qac.AcrosticLetter);
        doc = parser.ParseFromString("\\qm God is on your side.");
        Assert.assertEquals(1, doc.Contents.size());
        isInstanceOfType(doc.Contents.get(0), QMMarker.class);
        Assert.assertEquals("God is on your side.", ((TextBlock)doc.Contents.get(0).Contents.get(0)).Text);
        Assert.assertEquals(1, ((QMMarker)parser.ParseFromString("\\qm God is on your side.").Contents.get(0)).Depth);
        Assert.assertEquals(1, ((QMMarker)parser.ParseFromString("\\qm1 God is on your side.").Contents.get(0)).Depth);
        Assert.assertEquals(2, ((QMMarker)parser.ParseFromString("\\qm2 God is on your side.").Contents.get(0)).Depth);
        Assert.assertEquals(3, ((QMMarker)parser.ParseFromString("\\qm3 God is on your side.").Contents.get(0)).Depth);
        doc = parser.ParseFromString("\\qa Aleph");
        isInstanceOfType(doc.Contents.get(0), QAMarker.class);
        QAMarker qa = (QAMarker)doc.Contents.get(0);
        Assert.assertEquals("Aleph", qa.Heading);
    }

    @Test
    public void testCharacterStylingParse() throws Exception {
        isInstanceOfType(parser.ParseFromString("\\v 21 Penduduk kota yang satu akan pergi \\em Emphasis \\em* ").Contents.get(0).Contents.get(1), EMMarker.class);
        isInstanceOfType(parser.ParseFromString("\\v 21 Penduduk kota yang satu akan pergi \\bd Boldness \\bd* ").Contents.get(0).Contents.get(1), BDMarker.class);
        isInstanceOfType(parser.ParseFromString("\\v 21 Penduduk kota yang satu akan pergi \\bdit Boldness and Italics \\bdit* ").Contents.get(0).Contents.get(1), BDITMarker.class);
        isInstanceOfType(parser.ParseFromString("\\v 21 Penduduk kota yang satu akan pergi \\it Italics \\it* ").Contents.get(0).Contents.get(1), ITMarker.class);
        isInstanceOfType(parser.ParseFromString("\\v 21 Penduduk kota yang satu akan pergi \\sup Superscript \\sup* ").Contents.get(0).Contents.get(1), SUPMarker.class);
        isInstanceOfType(parser.ParseFromString("\\v 21 Penduduk kota yang satu akan pergi \\nd Name of Diety \\nd* ").Contents.get(0).Contents.get(1), NDMarker.class);
        isInstanceOfType(parser.ParseFromString("\\v 21 Penduduk kota yang satu akan pergi \\sc Small Caps \\sc* ").Contents.get(0).Contents.get(1), SCMarker.class);
        isInstanceOfType(parser.ParseFromString("\\v 21 Penduduk kota yang satu akan pergi \\no Normal \\no* ").Contents.get(0).Contents.get(1), NOMarker.class);
        // Text Content
        Assert.assertEquals("Emphasis", ((TextBlock)parser.ParseFromString("\\v 21 Penduduk kota yang satu akan pergi \\em Emphasis \\em* ").Contents.get(0).Contents.get(1).Contents.get(0)).Text);
        Assert.assertEquals("Boldness", ((TextBlock)parser.ParseFromString("\\v 21 Penduduk kota yang satu akan pergi \\bd Boldness \\bd* ").Contents.get(0).Contents.get(1).Contents.get(0)).Text);
        Assert.assertEquals("Boldness and Italics", ((TextBlock)parser.ParseFromString("\\v 21 Penduduk kota yang satu akan pergi \\bdit Boldness and Italics \\bdit* ").Contents.get(0).Contents.get(1).Contents.get(0)).Text);
        Assert.assertEquals("Italics", ((TextBlock)parser.ParseFromString("\\v 21 Penduduk kota yang satu akan pergi \\it Italics \\it* ").Contents.get(0).Contents.get(1).Contents.get(0)).Text);
        Assert.assertEquals("Superscript", ((TextBlock)parser.ParseFromString("\\v 21 Penduduk kota yang satu akan pergi \\sup Superscript \\sup* ").Contents.get(0).Contents.get(1).Contents.get(0)).Text);
        Assert.assertEquals("Name of Diety", ((TextBlock)parser.ParseFromString("\\v 21 Penduduk kota yang satu akan pergi \\nd Name of Diety \\nd* ").Contents.get(0).Contents.get(1).Contents.get(0)).Text);
        Assert.assertEquals("Small Caps", ((TextBlock)parser.ParseFromString("\\v 21 Penduduk kota yang satu akan pergi \\sc Small Caps \\sc* ").Contents.get(0).Contents.get(1).Contents.get(0)).Text);
        Assert.assertEquals("Normal", ((TextBlock)parser.ParseFromString("\\v 21 Penduduk kota yang satu akan pergi \\no Normal \\no* ").Contents.get(0).Contents.get(1).Contents.get(0)).Text);
    }

    @Test
    public void testUnknownMarkerParse() throws Exception {
        Assert.assertEquals(" what is yy?", ((UnknownMarker)parser.ParseFromString("\\yy what is yy?").Contents.get(0)).ParsedValue);
        Assert.assertEquals("yy", ((UnknownMarker)parser.ParseFromString("\\yy what is yy?").Contents.get(0)).ParsedIdentifier);
        Assert.assertEquals(" what is z?", ((UnknownMarker)parser.ParseFromString("\\z what is z?").Contents.get(0)).ParsedValue);
        Assert.assertEquals("z", ((UnknownMarker)parser.ParseFromString("\\z what is z?").Contents.get(0)).ParsedIdentifier);
        Assert.assertEquals(" what is 1?", ((UnknownMarker)parser.ParseFromString("\\1 what is 1?").Contents.get(0)).ParsedValue);
        Assert.assertEquals("1", ((UnknownMarker)parser.ParseFromString("\\1  what is 1?").Contents.get(0)).ParsedIdentifier);
    }

    @Test
    public void testWhitespacePreserve() throws Exception {
        String verseText = "This is verse text ";
        String otherVerseText = " after the word";
        USFMDocument output = parser.ParseFromString("\\v 1 {verseText}\\bd Bold \\bd*{otherVerseText}");
        Assert.assertEquals(verseText, ((TextBlock)output.Contents.get(0).Contents.get(0)).Text);
        Assert.assertEquals(otherVerseText, ((TextBlock)output.Contents.get(0).Contents.get(3)).Text);
    }

    @Test
    public void testVersePoetryNesting() throws Exception {
        String verseText = "\\q \\v 1 This is verse one \\q another poetry \\v 2 second verse";
        USFMDocument output = parser.ParseFromString(verseText);
        Assert.assertEquals(2, output.Contents.size());
        Assert.assertTrue(output.Contents.get(0) instanceof QMarker);
        Assert.assertTrue(output.Contents.get(0).Contents.get(0) instanceof VMarker);
        Assert.assertTrue(output.Contents.get(0).Contents.get(0).Contents.get(1) instanceof QMarker);
        Assert.assertTrue(output.Contents.get(1) instanceof VMarker);
    }

    @Test
    public void testBadChapterHandling() throws Exception {
        String verseText = "\\c 1 Bad text here";
        USFMDocument output = parser.ParseFromString(verseText);
        Assert.assertEquals(1, output.Contents.size());
        Assert.assertTrue(output.Contents.get(0).Contents.get(0) instanceof TextBlock);
        Assert.assertEquals(1, ((CMarker)output.Contents.get(0)).Number);
        Assert.assertEquals("Bad text here", ((TextBlock)output.Contents.get(0).Contents.get(0)).Text);
    }

    @Test
    public void testNoChapterNumberingHandling() throws Exception {
        String verseText = "\\c \\v 1 Bad text here";
        USFMDocument output = parser.ParseFromString(verseText);
        Assert.assertEquals(1, output.Contents.size());
        Assert.assertTrue(output.Contents.get(0) instanceof CMarker);
        Assert.assertEquals(0, ((CMarker)output.Contents.get(0)).Number);
    }

    @Test
    public void testNoChapterNumberingAndTextHandling() throws Exception {
        String verseText = "\\c Text Block \\v 1 Bad text here";
        USFMDocument output = parser.ParseFromString(verseText);
        Assert.assertEquals(1, output.Contents.size());
        Assert.assertTrue(output.Contents.get(0) instanceof CMarker);
        Assert.assertEquals(0, ((CMarker)output.Contents.get(0)).Number);
        Assert.assertEquals(2, output.Contents.get(0).Contents.size());
        Assert.assertTrue(output.Contents.get(0).Contents.get(0) instanceof TextBlock);
        Assert.assertEquals("Text Block", ((TextBlock)output.Contents.get(0).Contents.get(0)).Text);
    }

    @Test
    public void testCorrectFQAEndMarkerNesting() throws Exception {
        String verseText = "\\f + \\ft Text \\fqa Other \\fqa* More";
        USFMDocument output = parser.ParseFromString(verseText);
        // Make sure the FMarker has only one child
        Assert.assertEquals(1, output.Contents.get(0).Contents.size());
    }

    private void isInstanceOfType(Object obj, Class clazz){
        Assert.assertThat(obj, instanceOf(clazz));
    }
}
