package basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TextFileReadWrite {
	public static void main(String[] args) throws IOException {
		readList("test.txt");
		writePrintWriter("textwrite.txt");
		writeFileWriter("textwrite1.txt");
		copyFile("test.txt","test2.txt");
	}
	
	static void readList(String txt) {
		List<String> list = readFileAsList(txt);
		list.stream().forEach(System.out::println);
	}
	
	static List<String> readFileAsList(String fileName) {
		String line= null;
		List<String> list = new ArrayList<>();
		try {
			File file = new File("./TEXT/", fileName);
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			
			while((line=br.readLine()) != null) {
//				System.out.println(line);
				list.add(line);
			}
			br.close();
			
		} catch (Exception e ) {e.printStackTrace();}
		return list;
	}
	
	static void writeListToFile(List<String> list, String outputFileName) throws IOException {
		File file = new File(".", outputFileName);
		
		FileWriter fw = new FileWriter(file);
		
		for(String s: list) {
			String data = s+"\r\n";
			fw.write(data);
		}
		fw.close();
		
	}

	static void writeFileWriter(String outputFileName) throws IOException {
		File file = new File("./TEXT/", outputFileName);
		
		FileWriter fw = new FileWriter(file);
        for(int i=1; i<11; i++) {
            String data = i+" 번째 줄입니다.\r\n";
            fw.write(data);
        }
        fw.close();

        FileWriter fw2 = new FileWriter(file, true);
        for(int i=11; i<21; i++) {
            String data = i+" 번째 줄입니다.\r\n";
            fw2.write(data);
        }
        fw2.close();
		
	}

	static void writePrintWriter(String outputFileName) throws IOException {
		File file = new File("./TEXT/", outputFileName);
		
		PrintWriter pw = new PrintWriter(file);
        for(int i=1; i<11; i++) {
            String data = i+" 번째 줄입니다.";
            pw.println(data);
        }
        pw.close();

        PrintWriter pw2 = new PrintWriter(new FileWriter(file, true));
        for(int i=11; i<21; i++) {
            String data = i+" 번째 줄입니다2";
            pw2.println(data);
        }
        pw2.close();
		
	}
	
	static void copyFile(String inputFileName, String outputFileName) {
		final int BUFFER_SIZE = 4096;
		int readLen;
		
		try {
			File inputFile = new File("./TEXT", inputFileName);
			File outputFile = new File("./TEXT", outputFileName);
			InputStream inputStream = new FileInputStream(inputFile);
			OutputStream outputStream = new FileOutputStream(outputFile);
			
			byte[] buffer = new byte[BUFFER_SIZE];
			
			while((readLen=inputStream.read(buffer))!=-1) {
				outputStream.write(buffer, 0, readLen);
				
			}
			inputStream.close();
			outputStream.close();
			System.out.println("Done");
		}catch(Exception e) {e.printStackTrace();}
	}
	

}
