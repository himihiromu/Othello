package mainpack;

import ExceptionSummary.NumErrException;
import ExceptionSummary.PutErrException;
import ExceptionSummary.ReturnErrException;

public class BordInfoDriver {

	public static void main(String[] args) throws PutErrException, ReturnErrException, NumErrException {
		BordInfo bi = new BordInfo();
		for(int i = 0 ; i < 10 ; i++) {
			for(int j = 0 ; j < 10 ; j++) {
				System.out.print(bi.partOut(i, j));
			}
			System.out.println();
		}
		BordReturn br = new BordReturn(bi);
		br.output();
		br.returnStone(4, 3, true);
		br.output();
	}

}
