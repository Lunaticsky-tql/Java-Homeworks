package fileFinder;
import java.io.*; 
import java.util.regex.*; 
import java.util.*; 
class SortedDirList {
	public static double totalSize=0;
	public static int totalFileNumber=0;
	public static int totalFolderNumber=0;
	public static int getTotalFileNumber() {
		return totalFileNumber;
	}
	public static int getTotalFolderNumber() {
		return totalFolderNumber;
	}
	public static double getTotalSize() {
		return totalSize;
	}
	public void traverseFolder(String path,int depth) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if ( files==null||files.length==0) {
                return;
            } 
            else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("Folder:" + file2.getName()+" "+"depth="+depth);
                        traverseFolder(file2.getAbsolutePath(),depth+1);
                        totalFolderNumber+=1;
                    } else {
                        System.out.println("File:" + file2.getName()+" "+"depth="+depth+" "+file2.length()+"byte(s)");
                        totalSize+=file2.length();
                        totalFileNumber+=1;
                    }
                }
            }
        } else {
            System.out.println("Path not found");
        }
    }
} 
public class FileFinder { 
 public static void main(String args[]) { 
	 int depth=0;
 SortedDirList dir = new SortedDirList(); 
 dir.traverseFolder("D:\\转专业程序", depth);
 System.out.println("total folder number="+SortedDirList.getTotalFolderNumber());
 System.out.println("total file number="+SortedDirList.getTotalFileNumber());
 System.out.println("total size="+SortedDirList.getTotalSize()/(1024*1024)+"MB");
 } 
}