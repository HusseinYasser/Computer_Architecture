package testing;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Project.CodeParser;

class ParseTesting {

	@Test
	void test() {
		CodeParser cp = new CodeParser();
		
		String arr[] = new String[3];
		
		arr[0] = "ADD"; arr[1] = "R63" ; arr[2] = "R0";
		short output = cp.parseString(arr);
		assertEquals(0B0000111111000000, output );
		
	}
	
	@Test
	void test2()
	{
         CodeParser cp = new CodeParser();
		
		 String arr[] = new String[3];
		
		 arr[0] = "ADD"; arr[1] = "R53" ; arr[2] = "R23";
		 short output = cp.parseString(arr);
		 assertEquals(0B0000110101010111, output);
	}
	
	@Test
	void test3()
	{
         CodeParser cp = new CodeParser();
		
		 String arr[] = new String[3];
		
		 arr[0] = "SUB"; arr[1] = "R11" ; arr[2] = "R34";
		 short output = cp.parseString(arr);
		 assertEquals(0B0001001011100010, output);
		 assertEquals(4834 , output);
	}
	
	@Test
	void test4()
	{
         CodeParser cp = new CodeParser();
		
		 String arr[] = new String[3];
		
		 arr[0] = "SUB"; arr[1] = "R44" ; arr[2] = "R0";
		 short output = cp.parseString(arr);
		 assertEquals(0B0001101100000000, output);
		 assertEquals(6912 , output);
	}
	
	@Test
	void test5()
	{
         CodeParser cp = new CodeParser();
		
		 String arr[] = new String[3];
		
		 arr[0] = "MUL"; arr[1] = "R44" ; arr[2] = "R0";
		 short output = cp.parseString(arr);
		 assertEquals(0B0010101100000000, output);
		 assertEquals(11008 , output);
	}
	
	@Test
	void test6()
	{
         CodeParser cp = new CodeParser();
		
		 String arr[] = new String[3];
		
		 arr[0] = "MOVI"; arr[1] = "R44" ; arr[2] = "5";
		 short output = cp.parseString(arr);
		 assertEquals(0B0011101100000101, output);
		 assertEquals(15109 , output);
	}
	
	@Test
	void test7()
	{
         CodeParser cp = new CodeParser();
		
		 String arr[] = new String[3];
		
		 arr[0] = "MOVI"; arr[1] = "R51" ; arr[2] = "-32";
		 short output = cp.parseString(arr);
		 assertEquals(0B0011110011100000, output);
		 assertEquals(15584 , output);
	}
	
	@Test
	void test8()
	{
         CodeParser cp = new CodeParser();
		
		 String arr[] = new String[3];
		
		 arr[0] = "BEQZ"; arr[1] = "R51" ; arr[2] = "127";
		 short output = cp.parseString(arr);
		 assertEquals(0B0100110011111111, output);
		 assertEquals(19711 , output);
	}
	
	@Test
	void test9()
	{
         CodeParser cp = new CodeParser();
		
		 String arr[] = new String[3];
		
		 arr[0] = "BEQZ"; arr[1] = "R51" ; arr[2] = "127";
		 short output = cp.parseString(arr);
		 assertEquals(0B0100110011111111, output);
		 assertEquals(19711 , output);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
