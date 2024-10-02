package OverideMethod;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class PDFTectStripperCustome extends PDFTextStripper {


    private static String key_string;
    private static float x;
    private static float y;
    private static int pageFound;

    private static int isFound = 0;
    public PDFTectStripperCustome() throws IOException {
        x = -1;
        y = -1;
    }

    public static float[] getCoordiantes(PDDocument document, String phrase, int page) throws IOException {
        key_string = phrase;
        PDFTextStripper stripper = new PDFTectStripperCustome();
        stripper.setSortByPosition(true);
        stripper.setStartPage(page);
        stripper.setEndPage(page);
        stripper.writeText(document, new OutputStreamWriter(new ByteArrayOutputStream()));
        y = document.getPage(page-1).getMediaBox().getHeight()-y;
        if (x != -1){
            pageFound = page;
        }


        return new float[]{x,y,pageFound,isFound};
    }

    @Override
    protected void writeString(String string, List<TextPosition> textPositions) throws IOException
    {
        System.out.println(string);

        if(string.contains(key_string)) {
            TextPosition text = textPositions.get(0);
            if(x == -1) {
                x = text.getXDirAdj();
                y = text.getYDirAdj();
                isFound = 1;
            }
        }

//        System.out.println("Farras page ");
//        float docHeight = +document.getPage(this.getCurrentPageNo()).getMediaBox().getHeight();
//        for (TextPosition text : textPositions)
//        {
//            System.out.println(text.getUnicode());
//            if (text.getUnicode().equalsIgnoreCase("~")){
//                System.out.println("ketemu di eta "+this.getCurrentPageNo());
//                pageFound = this.getCurrentPageNo();
//                System.out.println( "String[" + text.getXDirAdj() + "," +
//                        (docHeight - text.getYDirAdj()) + " fs=" + text.getFontSize() + " xscale=" +
//                        text.getXScale() + " height=" + text.getHeightDir() + " space=" +
//                        text.getWidthOfSpace() + " widt12321321h=" +
//                        text.getWidthDirAdj() + "]" + text.getUnicode() );
//            }
//
//        }
    }
}
