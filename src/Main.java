import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
	
	public static String BinaryInstruction(short i)
	{
		String res = Integer.toBinaryString(i);
		String ret = "";
		if(res.length() > 16)
			ret = res.substring(16);
		else {
			while(res.length()<16)
				res = "0"+res;
			ret = res;
		}
		return ret;
		
	}
	
	public static void main(String[]args) throws IOException
	{
		
		InstructionMemory im = new InstructionMemory();
		
		DataMemory dm = new DataMemory();
		
		ALU alu = new ALU();
		
		RegisterFile rf = new RegisterFile();
		
		MicroArcheticture mac = new MicroArcheticture(im,dm,rf,alu);
		
		CodeParser cp = new CodeParser();
		
		cp.parse(new File("C:\\Users\\admin\\Desktop\\P1.txt"), im);
		
		mac.run();
		
		
		
		
		
		
	}

}
