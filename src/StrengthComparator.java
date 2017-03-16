import javax.microedition.rms.RecordComparator;

public class StrengthComparator implements RecordComparator {
	public int compare(byte[] rec1, byte[] rec2) {
		String arg1 = new String(rec1);
		String arg2 = new String(rec2);
		String strength = "strength: ";
		arg1 = arg1.substring(arg1.indexOf(strength) + strength.length(), arg1.length());
		arg2 = arg2.substring(arg2.indexOf(strength) + strength.length(), arg2.length());
		int result = arg1.compareTo(arg2);
		if (result > 0){
			return 1;
		}
		else if (result < 0) {
			return -1;
		}
		else {
			return 0;
		}
	}
}
