package zemiA;




/**
 * @author gruidaer
 * Disharmonyの検出で使用するヘルパークラス.
 */
public class DetectionDisharmony {

	/* ----- 定数フィールド ----- */
	private static final int CYCLO_PER_LINE_OF_CODE = 0;
	private static final int LOC_PER_METHOD = 1;
	private static final int NOM_PER_CLASS = 2;
	private static final int WMC = 3;
	private static final int AMW = 4;
	private static final int LOC_PER_CLASS = 5;
	private static final int LOC_PER_OPERATION = 6;
	private static final int NOC_PER_PACKAGE = 7;
	private static final int CALLS_PER_OPERATION = 8;
	private static final int FANOUT_PER_CALL = 9;
	private static final int ANDC = 10;
	private static final int AHH = 11;

	private static final int LOW = 0;
	private static final int AVERAGE = 1;
	private static final int HIGH = 2;
	private static final int VERY_HIGH = 3;

	/* メトリックの基準値 */
	private static final double METRIC[][] =
		{
				{0.16, 0.20, 0.24, 0.36},               // CYCLO/Line of Code
				{7.0, 10.0, 13.0, 19.5},                // LOC/METHOD
				{4.0, 7.0, 10.0, 15.0},                 // NOM/Class
				{5.0, 14.0, 31.0, 47.0},                // WMC
				{1.1, 2.0, 3.1, 4.7},                   // AMW
				{28.0, 70.0, 130.0, 195.0},             // LOC/Class
				{7.0, 10.0, 13.0, Double.MAX_VALUE},   // LOC/Operation
				{6.0, 17.0, 26.0, Double.MAX_VALUE},   // NOC/Package
				{2.01, 2.62, 3.2, Double.MAX_VALUE},   // CALLS/Opration
				{0.56, 0.62, 0.68, Double.MAX_VALUE},  // FANOUT/Call
				{0.25, 0.41, 0.57, Double.MAX_VALUE},  // ANDC
				{0.09, 0.21, 0.32, Double.MAX_VALUE}   // AHH
		};

	private static final double  ONE_QUATER = 0.25;
	private static final double  ONE_THIRD = 0.33;
	private static final double  HALF = 0.5;
	private static final double  TWO_THIRDS = 0.25;
	private static final double  THREE_QUARTERS = 0.25;

	private static final int NONE = 0;
	private static final int ONE = 1;
	private static final int SHALLOW = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FEW = 3;
	private static final int SEVERAL = 5;
	private static final int SHORT_MEMORY_CAPACiTY = 7;
	private static final int MANY = 20;

	/* ----- Disharmony 検出メソッド ----- */
//	/* God Class 検出 */
//	private static boolean isGodClass(ClassInfo classInfo)
//	{
//		if ( classInfo.getATFD() > FEW
//				&& classInfo.getWMC() >= METRIC[WMC][VERY_HIGH]
//				&& classInfo.getTCC() < ONE_THIRD) {
//			return true;
//		} else  return false;
//	}
//
//	/* Feature Envy 検出 */
//	private static boolean isFeatureEnvy(MethodInfo methodInfo)
//	{
//		if ( methodInfo.getATFD() > FEW
//				&& methodInfo.getLAA() < ONE_THIRD
//				&& methodInfo.getFDP() <= FEW ) {
//			return true;
//		} else  return false;
//	}
//
//	/* Data Class 検出 */
//	private static boolean isDataClass(ClassInfo classInfo)
//	{
//		if ( classInfo.getWOC() < ONE_THIRD ) {
//			if ( classInfo.getNOAP() + classInfo.getNOAM() > FEW
//					&& classInfo.getWMC() < METRIC[WMC][HIGH] ) {
//				return true;
//			} else if ( classInfo.getNOAP() + classInfo.getNOAM() > MANY
//					&& (double)classInfo.getWMC() < METRIC[WMC][VERY_HIGH] ) {
//				return true;
//			} else  return false;
//		} else  return false;
//	}
//
//	/* Brain Method 検出 */
//	private static boolean isBrainMethod(MethodInfo methodInfo)
//	{
//		if ( (double)methodInfo.getLOC() >  METRIC[LOC_PER_CLASS][HIGH]/2
//				&& (double)methodInfo.getCYCLO() >= METRIC[CYCLO_PER_LINE_OF_CODE][HIGH]
//				&& methodInfo.getMAXNESTING() >= SEVERAL
//				&& methodInfo.getNOAV() > MANY) {
//			return true;
//		} else  return false;
//	}
//
//	/* Brain Class 検出 */
//	private static boolean isBrainClass(ClassInfo classInfo)
//	{
//		if ( classInfo.getWMC() >= METRIC[WMC][VERY_HIGH]
//				&& classInfo.getTCC() < HALF ) {
//			if( classInfo.getBrainMethodNum() >= 1
//				&& (double)classInfo.getLOC() >=  METRIC[LOC_PER_CLASS][VERY_HIGH]) {
//				return true;
//			} else if ( classInfo.getBrainMethodNum() == 1
//					&& (double)classInfo.getLOC() >= 2 * METRIC[LOC_PER_CLASS][VERY_HIGH]
//					&& (double)classInfo.getWMC() >= 2 * METRIC[WMC][VERY_HIGH]) {
//				return true;
//			} else  return false;
//		} else  return false;
//	}
//
//	/* Significant Duplication 検出 */
//	private static boolean isSignificantDuplication
//	(double sizeOfExactClone, int lineBias, int sizeOfDuplicationChain)
//	{
//		if ( sizeOfExactClone > METRIC[LOC_PER_OPERATION][AVERAGE] ) {
//			return true;
//		} else {
//			if ( sizeOfDuplicationChain >= 2 * ( FEW + 1 )+1
//					&& sizeOfExactClone > (double)FEW
//					&& lineBias <= FEW ) {
//				return true;
//			} else  return false;
//		}
//	}

}
