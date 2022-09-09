import java.util.*;

public class testing {
	
	public static void main(String[]args)
	{
		short x = (short) 0B1110000000000000;
		x &= (0B1111000000000000);
		x = (short) (x >>> 12);
		System.out.println(x);
		
	    
	}

}
