package pcalculation;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class PCCalc {
	double[][] pCorr;
	double[][] pCorrSTDErr;
	double[][] pCorrpVal;
	
	
	PCCalc(double[][] pCorr, double[][] pCorrSTDErr, double[][] pCorrpVal){
		this.pCorr = pCorr;
		this.pCorrSTDErr = pCorrSTDErr;
		this.pCorrpVal = pCorrpVal;
	}
	
	public PCCalc() {
		// TODO Auto-generated constructor stub
	}

	PCCalc calPC(double[][] matrix){
		PearsonsCorrelation pCorr = new PearsonsCorrelation(matrix);
		RealMatrix pCorrMatrix = pCorr.computeCorrelationMatrix(matrix);		
		RealMatrix pCorrSTDE = pCorr.getCorrelationStandardErrors();
		RealMatrix pCorrpVal = pCorr.getCorrelationPValues();
		
		return new PCCalc(pCorrMatrix.getData(), pCorrSTDE.getData(),pCorrpVal.getData());
		//return pCorrMatrix.getData();		
	}

}
