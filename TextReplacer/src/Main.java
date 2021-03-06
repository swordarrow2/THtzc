import java.io.*;
import java.nio.charset.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {

		String[][] replace={
			{" bool "," boolean "},
			{"string","String"},
			{"Math.Sin","Math.sin"},
			{"Math.Cos","Math.cos"},
			{"Math.Abs","Math.abs"},
			{"Math.Sqrt","Math.sqrt"},
			{"float.Parse","Float.parseFloat"},
			{"double.parse","Double.parseDouble"},
			{"int.Parse","Integer.parseInt"},
			{".Count",".size()"},
			{" List"," ArrayList"},
			{"@event","_event"},
			{"==\"",".equals(\""},
			{"Hashtable","HashMap<String,Integer>"},
			{"new HashMap();","new HashMap<>();"},
			{"Split","split"},
			{"'","\""},
			{"\".ToCharArray()",""},
				{".Clear()",".clear()"},
			{"position.X","position.x"},
			{"position.Y","position.y"},
			{"PointF","Vector2"},
			{"Add","put"},
			{"foreach","for"},
			{" in "," : "},
			{"byte.MaxValue","Byte.MAX_VALUE"},
			{"Contains","contains"},
			{"Rectangle?","Rectangle"},
			{"RemoveAt","remove"},
			{"Atan","atan"},
			{"[this.parentid]",".get(parentid)"}
	
			
			
			
		};

		ArrayList<File> fs=new ArrayList<>();
		try {
			getFiles("/storage/emulated/0/AppProjects/THtzc/TouHouTaiHun/app/src/main/java/runner", fs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (File f:fs) {
			String s=readString(f);
			for (String[] sa:replace) {
				s = s.replace(sa[0], sa[1]);
			}
			saveString(f, s);
		}
		System.out.println("ok");
	}

	public static void getFiles(String path, ArrayList<File> list) throws Exception {
        //目标集合fileArrayList
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileIndex : files) {
                if (fileIndex.isDirectory()) {
                    getFiles(fileIndex.getPath(), list);
                } else {
					if (fileIndex.getName().endsWith(".java")) {
						list.add(fileIndex);
					}
                }
            }
        }
    }

	public static void saveString(File f, String str) {
		try {
			FileOutputStream fos = new FileOutputStream(f);
			OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			writer.write(str);
			writer.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public static String readString(File f) {
		String s = null;
		try {      
			long filelength = f.length();
			byte[] filecontent = new byte[(int) filelength];
			FileInputStream in = new FileInputStream(f);
			in.read(filecontent);
			in.close();
			s = new String(filecontent, StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;	
	}
}
