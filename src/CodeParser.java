import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CodeParser {
	
	public CodeParser()
	{
		
	}
	
	public void parse(File f, InstructionMemory im) throws IOException
	{
		
		//Reading the Text Assembly FIle
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		String st;
		
		while((st = br.readLine())!= null)
		{
			//instruction result;
			short res = 0;
			//splitting the string over space so it is readable and easier
			String arr[] = st.split(" ");
			
			//Parsing the first word --> Opcode;
			String first = arr[0];
			
			switch(first)
			{
			   case "ADD" : res = 0; break;
			   case "SUB" : res = 1; break;
			   case "MUL" : res = 2; break;
			   case "MOVI" : res = 3; break;
			   case "BEQZ" : res = 4; break;
			   case "ANDI" : res = 5; break;
			   case "EOR" : res = 6; break;
			   case "BR" : res = 7; break;
			   case "SAL" : res = 8; break;
			   case "SAR" : res = 9; break;
			   case "LDR" : res = 10; break;
			   default : res = 11;
			}
			res = (short)res;
			
			//Parsing the Second word which will always be a source Register
			String second = arr[1];
			
			//We have to shift the opcode we have got by 6 places so that we can put the next 6 bits in the instruction
			//These next 6 bits are for R1 (src Register)
			res = (short) (res<<6);
			short num = 0;
			for(int i = 1; i < second.length() ; ++i)
			{
				num *= 10;
				num += (second.charAt(i) - '0');
			}
			res += num;
			
			res = (short) (res<<6);
			//Parsing The Third Word
			String third = arr[2];
			num = 0;
			if(third.charAt(0) == 'R')
			{
				for(int i = 1 ; i < third.length() ; ++i)
				{
					num*=10;
					num += (third.charAt(i) - '0');
				}
			}
			else
			{
				for(int i = (third.charAt(0) == '-')? 1:0 ; i < third.length();++i)
				{
					num*=10;
					num+=(third.charAt(i) - '0');
				}
				if(third.charAt(0) == '-')
					num*= -1 ;
			}
			num &= (0B0000000000111111);
			res += num;
			im.instructions[im.lastInstruction++] = res;
			im.count++;
		}
		
	}

}
