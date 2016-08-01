package pcalculation;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class PearsonCorr {

	public static void main(String[] args) throws IOException {
		DataPrep prep = new DataPrep();
		String alzADFile = "C:/NaHyun/Research/AD/Data/mmsePats_ADGenes.csv";
		String alzRandomFile = "C:/NaHyun/Research/AD/Data/mmsePats_allGenes.csv";

		System.out.println("Loading AD data...");
		double[][] alzData = prep.load(alzADFile);
		PearsonsCorrelation alzPearsonPC = new PearsonsCorrelation(alzData);
		RealMatrix alzPearsonRM = alzPearsonPC.computeCorrelationMatrix(alzData);
		double[][] alzPearson = alzPearsonRM.getData();		
		prep.printDoubleArr(alzPearson,"Pearson Correlation for AD ");
		
		//Pick 51 random rows and get Pearson correlation				
		System.out.println("Loading random gene data...");
		double[][] randomData = prep.randomLoad(alzRandomFile,51);
		
		PearsonsCorrelation randomPearsonPC = new PearsonsCorrelation(randomData);
		RealMatrix randomPearsonRM = randomPearsonPC.computeCorrelationMatrix(randomData);
		double[][] randomPearson = randomPearsonRM.getData();		
		prep.printDoubleArr(randomPearson,"Pearson Correlation for random genes ");
		

	}

}
