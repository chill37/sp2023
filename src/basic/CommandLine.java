package basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CommandLine {
	public static void main(String[] args) throws IOException {
		
		String ans = printTasklist("ls", "-l", "./TEXT/");
		System.out.println("[ANS]" + ans);
		
		printTasklist2("ls", "-l", "./TEXT/");
		//printTasklist3();
		
	}
	
	public static void printTasklist3() throws IOException {
		String[] cmd = new String[] {"cmd", "/c", "dir"};
		Process process = null;
		String str = null;
		
		try {
			process = new ProcessBuilder(cmd).start();
			BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			while((str = stdOut.readLine()) != null) {
				System.out.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void printTasklist2(String... args) {
		String[] commands = new String[] {
				"ping -n 5 127.0.0.1"
			};

			try {
				ProcessBuilder b = new ProcessBuilder("bash");
				b.redirectErrorStream(true);
				Process p = b.start();

				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

				for (String cmd : commands) {
					writer.write(cmd + "\n");
					writer.flush();
				}

				writer.write("exit" + "\n");
				writer.flush();

				BufferedReader std = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String outputLine = "";
				String outputMessage = "";
				while ((outputLine = std.readLine()) != null) {
					outputMessage += outputLine + "\r\n";
				}

				p.waitFor();
				
				System.out.println(outputMessage);
			} 
			catch (Exception e) { e.printStackTrace(); }
	}
	
	public static String printTasklist(String exeCmd, String param, String folder) {

		List<String> command = new ArrayList<>();
		command.add(exeCmd);
		command.add(param);
		
		ProcessBuilder pb = new ProcessBuilder();
		pb.redirectErrorStream(true);
		pb.command(command);
		
		pb.directory(new File(folder));
		
		int result = 0;
		String ans = null;
		
		try {
			Process proc = pb.start();
			
			try(BufferedReader buffReader = new BufferedReader(new InputStreamReader(proc.getInputStream()))){
				String tmpLine = null;
				
				while((tmpLine = buffReader.readLine())!=null){
					System.out.println(tmpLine);
					if (tmpLine.contains("test") ) {
						ans = tmpLine;
					}
				}
			}
			
			proc.waitFor();
			result = proc.exitValue();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			result = -1;
		}
		System.out.println("[Exit] "+result);
		return ans;
	}
}
