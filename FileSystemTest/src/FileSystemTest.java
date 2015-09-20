import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSystemTest {
	public static void main(String[] args) {
		FileSystemTest fileSystemTest = new FileSystemTest();
		File cdir = null;
		for (;;) {
			fileSystemTest.printPrompt();
			String s = fileSystemTest.selectOption();
			if (s.equals("0")) {
				System.out.println("Exit!");
				break;
			} else if (s.equals("1")) {
				cdir = fileSystemTest.selectDir();
				System.out.println("");
			} else if (s.equals("2")) {
				fileSystemTest.listDirCnt(cdir);
				System.out.println("");
			} else if (s.equals("3")) {
				fileSystemTest.listDirAllCnt(cdir);
				System.out.println("");
			} else if (s.equals("4")) {
				fileSystemTest.deleteFile();
				System.out.println("");
			} else if (s.equals("5")) {
				fileSystemTest.displayFile();
				System.out.println("");
			} else {
				System.out.println("Please enter a number between 0 and 5.");
				System.out.println("");
			}
		}
	}
	// called this method when prompt is displayed.
	private void printPrompt() {
		System.out.println("0 - Exit");
		System.out.println("1 - Select directory");
		System.out.println("2 - List directory content (first level)");
		System.out.println("3 - List directory content (all levels)");
		System.out.println("4 - Delete file");
		System.out.println("5 - Display file");
//		System.out.println("6 - Encrypt file (XOR with password)");
//		System.out.println("7 - Decrypt file (XOR with password)");
		System.out.print("Select option: ");
	}
	// called this method when prompt is displayed.
	private String selectOption() {
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		return str;
	}
	// called this method when option 1 is selected.
	private File selectDir() {
		System.out.print("Enter a directory name: ");
		Scanner scan = new Scanner(System.in);
		String str1 = scan.next();
		String str2 = "root/" + str1;
		File dir = new File(str2);
		if (dir.exists()) {
			if (dir.isDirectory()) {
				return dir;				
			} else if (dir.isFile()) {
				System.out.println(str1 + " is not a directory.");
				return null;
			}
		} else {
			System.out.println("The directory is not fuond. Please enter a valid directory name.");
		}
		return null;
	}
	// called this method when option 2 is selected.
	private void listDirCnt(File cdir) {
		if (cdir == null) {
			System.out.println("Plsase select a directory.");
		} else {
			File filelist[] = cdir.listFiles();
		    for (int i = 0 ; i < filelist.length ; i++) {
		        if (filelist[i].isFile()){
		          System.out.println("[F]" + filelist[i].getName());
		        }else if (filelist[i].isDirectory()){
		          System.out.println("[D]" + filelist[i].getName());
		        }else{
		          System.out.println("[?]" + filelist[i].getName());
		        }
		      }			
		}
	}
	// called this method when option 3 is selected.
	private void listDirAllCnt(File cdir) {
		String d_str = "[D]" + cdir.getPath();
		System.out.println(d_str.replaceFirst("root.", ""));
        File[] dir = cdir.listFiles();
        if(dir == null) System.exit(9);
        for(File dirs : dir){
            if(dirs.isFile()){
            	String f_str = "[F]" + dirs.getPath();
            	System.out.println(f_str.replaceFirst("root.", ""));
            } else {
                listDirAllCnt(dirs);
            }
        }
	}
	// called this method when option 4 is selected.
	private void deleteFile() {
		System.out.print("Enter a filename to delete: ");
		Scanner scan = new Scanner(System.in);
		String str1 = scan.next();
		String str2 = "root/" + str1;
		File file = new File(str2);
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
				System.out.println(str1 + " was successfully deleted.");
			} else if (file.isDirectory()) {
				System.out.println(str1 + " is a directory.");
			}
		} else {
			System.out.println("The file is not fuond. Please enter a valid filename.");		
		}
	}
	// called this method when option 5 is selected.
	private void displayFile() {
		System.out.print("Enter a filename to display: ");
		Scanner scan = new Scanner(System.in);
		String str1 = scan.next();
		String str2 = "root/" + str1;
		File file = new File(str2);
		try {
			if (file.exists()) {
				if (file.isFile()) {
					Scanner scanf = new Scanner(file);
					scanf.useDelimiter("\\r\\n");
				      while(scanf.hasNext()) {
				          String str3 = scanf.next();
				          System.out.println(str3);
				          }
				} else if (file.isDirectory()) {
					System.out.println(str1 + " is a directory.");
				}
			} else {
				System.out.println("The file is not fuond. Please enter a valid filename.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}