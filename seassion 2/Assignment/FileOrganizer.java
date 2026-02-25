import java.util.*;

public class FileOrganizer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); sc.nextLine();
        String[] files = new String[n];
        for (int i=0;i<n;i++) files[i] = sc.nextLine();

        int doc=0,img=0,aud=0,vid=0,unk=0;

        for (String f : files) {
            String ext = f.contains(".") ? f.substring(f.lastIndexOf('.')+1).toLowerCase() : "";
            String type;
            if (ext.equals("txt")||ext.equals("doc")||ext.equals("docx")) {type="Document"; doc++;}
            else if (ext.equals("jpg")||ext.equals("png")) {type="Image"; img++;}
            else if (ext.equals("mp3")||ext.equals("wav")) {type="Audio"; aud++;}
            else if (ext.equals("mp4")||ext.equals("mkv")) {type="Video"; vid++;}
            else {type="Unknown"; unk++;}

            System.out.println(f+" -> "+type);
        }

        System.out.println("Documents: "+doc);
        System.out.println("Images: "+img);
        System.out.println("Audio: "+aud);
        System.out.println("Video: "+vid);
        System.out.println("Unknown: "+unk);
    }
}
