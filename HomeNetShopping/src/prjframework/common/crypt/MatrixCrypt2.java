package prjframework.common.crypt;

/**
 * ����/SMS �̺�Ʈ ��� ��ȣȭ ���
 * @author oops
 *
 */
public class MatrixCrypt2 {
	/* ��ȣȭ ������ ���ڿ� eidx[] */	
	static String eidx[] = {"0","1","2","3","4","5","6","7","8","9","0","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","@",".","-","_","(",")","[","]","*","/","+","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

	/* ��ȣȭ �ڵ�ǥ  didx[] */	
  	static String didx[] = {"8613141516171819202122232425262728293031323334353637383940414243444546474849505152535455565758596061626364656667686970717273747576777879808182838485",
  		"2324252627282930313217343536373839404142434445464748495051525354555657585960616263646566676869707172737475767778798081828384858687888990919212131415",
		"3435363738394041424344454647484950515253305556575859606162636465666768697071727374757677787980818283848586878889909192121314151617181920212223242526",
		"4546474849505152535455565758596061626364656667686970717273747576777879808182838485868738899091921213141516171819202122232425262728293031323334353637",
		"5657585960616263646566676869707172737475767778798081828384858687888990919212131415165518192021222324252627282930313233343536373839404142434445464748",
		"6768697071727374757677787980818283848586878889909192121314151617601920212223242526272829303132333435363738394041424344454647484950515253545556575859",
		"7879808182838485868788899091927713141516171819202122232425262728293031323334353637383940414243444546474849505152535455565758596061626364656667686970"};

	public static String Decode(String in){
		String result = "";
		int lo = 0;

		for(int j=0; j<in.length(); j+=2) {
			////System.out.println("j value : " + j);
			for(int i=0; i<74; i++) {
				////System.out.println("i value : " + i);
				////System.out.println("didx[j%7].substring(i*2,(i*2)+2) value : " + didx[j%7].substring(i*2,(i*2)+2));
				////System.out.println("in.substring(j,j+2) value : " + in.substring(j,j+2));
				if((didx[(j/2)%7].substring(i*2,(i*2)+2)).equals(in.substring(j,j+2))) {
					lo = i;
					////System.out.println("lo value : " + lo);
					break;
				} else {
					lo = 100;
				}
			}
			if(lo>99) {
				result="NOTMATCHEDVALUE";
				break;
			} else {
				result += eidx[lo];
			}
			////System.out.println("result value : " + result);
		}	  
		return result;
	}	  	    

	public static String Encode(String in){
		String result = "";
		int lo = 0;

		for(int j=0; j<in.length(); j++) {
			for(int i=0; i<74; i++) {
		 		if(eidx[i].equals(in.substring(j,j+1))) {
		 			lo = i;
		 			break;
		 		} else {
		 			lo = 100;
		 		}
		 	}

		 	if(lo>99) {
		 		result = "UNABLETOENCODE";
		 		break;
		 	} else {
		  	result += didx[j%7].substring(lo*2,(lo*2)+2);
		  }
		}
		return result;
	}

	/** ���� **/
/*
	public static void main(String args[]){
		if (args.length < 1) {
			//System.out.println("��ȭȭ�� ���� ��������");
			return;
		}		
	   	String resno=args[0];
		String result = Encode(resno);
		//System.out.println("resno  : " + resno);
		//System.out.println("encode : " + result);
		//System.out.println("decode : " + Decode(result));
 	}
*/
}
