import java.io.File;

public class fileaccess {
        private static File f = new File("/Users/ajazyeap/Documents/GitHub/buffer.txt");
        private static void printFileContents()
        {

        }
        public static void access()
        {

            System.out.println("fullpath: " + f.getAbsolutePath());
            System.out.println("file exists? :" + f.exists());
            System.out.println("is Directory?:" + f.isDirectory());
        }

}
