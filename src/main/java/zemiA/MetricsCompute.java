package zemiA;




/**
 * @author gruidae
 *
 * メトリクスを計算するためのヘルパークラス.
 */
public class MetricsCompute {

	/** AWM の値を計算する. */
	public static double averageMethodWeight(int cyclo, int methodNum)
	{
		double awm = (double)cyclo / methodNum;
		return awm;
	}


	/** BOvR の値を計算する. */
	public static double baseClassOverridingRatio(int overridedMethodNum, int methodNum)
	{
		double bovr = (double)overridedMethodNum/methodNum;
		return bovr;
	}


	/** BUR の値を計算する. */
	public static double baseClassUsageRatio(int usedSpecializedMethodNum, int specializedMethodNum)
	{
		double bur = (double)usedSpecializedMethodNum/specializedMethodNum;
		return bur;
	}


	/** LAA の値を計算する. */
	public static double localityOfAttributeAccesses(int atfd, int accessecVariables)
	{
		double laa = (double)atfd/accessecVariables;
		return laa;
	}


	/** PNAS の値を計算する. */
	public static double percentageOfNewlyAddedServices(int nas, int publicMethodNum)
	{
		double pnas = (double)nas/publicMethodNum;
		return pnas;
	}


	/** WMC の値を計算する. */
	public static double weightedMethodCount(int cyclo, int loc, int methodNum, int nom, int classNum)
	{
		double wmc = ((double)cyclo * loc * nom) / (loc * methodNum * classNum);
		return wmc;
	}


	/** WOC の値を計算する. */
	public static double weightOfClass(int functionalMethodNum, int publicMethodNum)
	{
		double woc = (double)functionalMethodNum/publicMethodNum;
		return woc;
	}

}