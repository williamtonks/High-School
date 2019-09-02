import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.net.URL;
import javax.swing.*;
import java.util.*;
public class Driver {
    public static void main(String args[])throws IOException
    {
        int count = 1, counter = 1, truecounter =1;
        char spacer = 'a';
        String downloadURL = "";
        File destinationFile = new File("MyPics");
        try {//reads in the webpage you enter
            URL website = new URL(JOptionPane.showInputDialog("Enter the page address.", "https://www.nytimes.com"));
            java.nio.channels.ReadableByteChannel rbc;
            rbc = java.nio.channels.Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(destinationFile);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //creates a destination file for pictures or whatever files you want.
        File dir = new File("thePictures"); 
        dir.mkdir();
        JOptionPane.showInputDialog("Made By Sam", "Made By Sam");
        Scanner fr = new Scanner(destinationFile);
        while (fr.hasNext())
        {
            String s = fr.next();
            if (s.contains(".jpg"))//finding .png files, a common web image format
            {
                s = s.substring(s.indexOf("htt"), s.length()-1);
                //System.out.println(" Found a PING! "+s);
                downloadURL = s;
                URL earl = new URL(downloadURL);
                try {
                    BufferedImage bi = ImageIO.read(earl);
                    File outputfile = new File("DJT"+count+".jpg");
                    ImageIO.write(bi, "jpg", outputfile);
                    count++;
                } 
                catch (Exception e) {

                    System.out.println("Error: DJT"+count+".jpg");
                }
            }
            count++;
        }
        JOptionPane.showMessageDialog(null, "Done");
        System.exit(0);
    }
}