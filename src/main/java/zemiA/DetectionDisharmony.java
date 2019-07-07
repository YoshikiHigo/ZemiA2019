package zemiA;

import java.util.HashSet;

/**
 * Disharmonyの検出で使用するヘルパークラス.
 * @author gruidaer
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
	private static final int SEVERAL = 4;
	private static final int SHORT_MEMORY_CAPACITY = 7;
	private static final int MANY = 10;

	/* ----- Disharmony 検出メソッド ----- */
	/* God Class 検出 */
	public static boolean isGodClass(ClassInfo classInfo)
	{
		if ( classInfo.getATFD() > FEW
				&& classInfo.getWMC() >= METRIC[WMC][VERY_HIGH]
				&& classInfo.getTCC() < ONE_THIRD) {
			return true;
		} else  return false;
	}

	/* Feature Envy 検出 */
	public static boolean isFeatureEnvy(MethodInfo methodInfo)
	{
		if ( methodInfo.getATFD() > FEW
				&& methodInfo.getLAA() < ONE_THIRD
				&& methodInfo.getFDP() <= FEW ) {
			return true;
		} else  return false;
	}

	/* Data Class 検出 */
	public static boolean isDataClass(ClassInfo classInfo)
	{
		if ( classInfo.getWOC() < ONE_THIRD ) {
			if ( classInfo.getNOAP() + classInfo.getNOAM() > FEW
					&& classInfo.getWMC() < METRIC[WMC][HIGH] ) {
				return true;
			} else if ( classInfo.getNOAP() + classInfo.getNOAM() > MANY
					&& classInfo.getWMC() < METRIC[WMC][VERY_HIGH] ) {
				return true;
			} else  return false;
		} else  return false;
	}

	/* Brain Method 検出 */
	public static boolean isBrainMethod(MethodInfo methodInfo)
	{
		if ( (double)methodInfo.getLOC() >  METRIC[LOC_PER_METHOD][HIGH]/2
				&& (double)methodInfo.getCYCLO() / methodInfo.getLOC()
						>= METRIC[CYCLO_PER_LINE_OF_CODE][HIGH]
				&& methodInfo.getMAXNESTING() >= SEVERAL
				&& methodInfo.getNOAV() > MANY) {
			return true;
		} else  return false;
	}

	/* Brain Class 検出 */
	public static boolean isBrainClass(ClassInfo classInfo)
	{
		if ( classInfo.getWMC() >= METRIC[WMC][VERY_HIGH]
				&& classInfo.getTCC() < HALF ) {
			if( classInfo.getBrainMethodNum() >= 1
				&& (double)classInfo.getLOC() >=  METRIC[LOC_PER_CLASS][VERY_HIGH]) {
				return true;
			} else if ( classInfo.getBrainMethodNum() == 1
					&& (double)classInfo.getLOC() >= 2 * METRIC[LOC_PER_CLASS][VERY_HIGH]
					&& (double)classInfo.getWMC() >= 2 * METRIC[WMC][VERY_HIGH]) {
				return true;
			} else  return false;
		} else  return false;
	}

	/* Significant Duplication 検出 */
	public static boolean isSignificantDuplication
	(double sizeOfExactClone, int lineBias, int sizeOfDuplicationChain)
	{
		if ( sizeOfExactClone > METRIC[LOC_PER_OPERATION][AVERAGE] ) {
			return true;
		} else {
			if ( sizeOfDuplicationChain >= 2 * ( FEW + 1 )+1
					&& sizeOfExactClone > (double)FEW
					&& lineBias <= FEW ) {
				return true;
			} else  return false;
		}
	}

	/* Intensive Coupling 検出 */
	public static boolean isIntensiveCoupling(MethodInfo methodInfo)
	{
		if (methodInfo.getMAXNESTING() > SHALLOW) {
			if (methodInfo.getCINT() > SHORT_MEMORY_CAPACITY
					&& methodInfo.getCDISP() < HALF) {
				return true;
			} else if (methodInfo.getCINT() > FEW  && methodInfo.getCDISP() < ONE_QUATER) {
				return true;
			} else return false;
		} else  return false;
	}

	/* Dispersed Coupling 検出 */
	public static boolean isDispersedCoupling(MethodInfo methodInfo)
	{
		if (methodInfo.getMAXNESTING() > SHALLOW) {
			if (methodInfo.getCINT() > SHORT_MEMORY_CAPACITY
					&& methodInfo.getCDISP() > HALF) {
				return true;
			} else return false;
		} else  return false;
	}

	/* ShotGun Surgery 検出 */
	public static boolean isShotgunSurgery(MethodInfo methodInfo)
	{
		if (methodInfo.getCM() > SHORT_MEMORY_CAPACITY
				&& methodInfo.getCC() > HALF) {
			return true;
		} else return false;
	}

	/* Refused Parent Bequest 検出 */
	public static boolean isRefusedParentBequest(ClassInfo classInfo)
	{
		boolean isIgnored = false, isLargeComplex = false;
		if ( classInfo.getBOvR() < ONE_THIRD ) {
			isIgnored = true;
		} else if ( classInfo.getNProtM() > FEW && classInfo.getBUR() < ONE_THIRD ) {
			isIgnored = true;
		} else isIgnored = false;

		if ( (double)classInfo.getNOM() > METRIC[NOM_PER_CLASS][AVERAGE] ) {
			if ( classInfo.getAMW() > METRIC[AMW][AVERAGE] ) {
				isLargeComplex = true;
			} else if ( classInfo.getWMC() > METRIC[WMC][AVERAGE] ) {
				isLargeComplex = true;
			} else  isLargeComplex = false;
		} else isLargeComplex = false;

		if (isIgnored && isLargeComplex)  return true;
		else  return false;
	}

	/* Tradition Breaker 検出 */
	public static boolean isTraditionBreaker(ClassInfo child, ClassInfo parent)
	{
		boolean isExcessiveChild = false, isComplexChild = false, isParentLarge = false;

		if ( (double)child.getNAS() >= METRIC[NOM_PER_CLASS][AVERAGE]
				&& child.getPNAS() >= TWO_THIRDS) {
			isExcessiveChild = true;
		} else  isExcessiveChild = false;

		if ( (double)child.getNOM() >= METRIC[NOM_PER_CLASS][HIGH] ) {
			if ( child.getAMW() > METRIC[AMW][AVERAGE] ) {
				isComplexChild = true;
			} else if ( child.getWMC() >= METRIC[WMC][VERY_HIGH] ) {
				isComplexChild = true;
			} else  isComplexChild = false;
		} else  isComplexChild = false;

		if ( parent.getAMW() > METRIC[WMC][AVERAGE]
				&& (double)parent.getNOM() > METRIC[NOM_PER_CLASS][HIGH]/ 2
				&& parent.getWMC() >= METRIC[WMC][VERY_HIGH]/ 2 ) {
			isParentLarge = true;
		} else  isParentLarge = false;

		if( isExcessiveChild && isComplexChild && isParentLarge )  return true;
		return false;
	}


	public static Disharmony godClass(ClassInfo classInfo) {
		if( isGodClass(classInfo) )  return Disharmony.GOD_CLASS;
		else  return null;
	}

	public static Disharmony featureEnvy(MethodInfo methodInfo) {
		if( isFeatureEnvy(methodInfo) )  return Disharmony.FEATURE_ENVY;
		else  return null;
	}

	public static Disharmony brainMethod(MethodInfo methodInfo) {
		if( isBrainMethod(methodInfo) )  return Disharmony.BRAIN_METHOD;
		else  return null;
	}

	public static Disharmony brainClass(ClassInfo classInfo) {
		if( isBrainClass(classInfo) )  return Disharmony.BRAIN_CLASS;
		else  return null;
	}

	public static Disharmony dataClass(ClassInfo classInfo) {
		if( isDataClass(classInfo) )  return Disharmony.DATA_CLASS;
		else  return null;
	}

	/*
	public static Disharmony significantDuplication(ClassInfo classInfo) {
		if( isSignificantDuplication() )  return Disharmony.SIGNIFICANT_DUPLICATION;
		else  return null;
	}
	*/


	public static Disharmony intensiveCoupling(MethodInfo methodInfo) {
		if( isIntensiveCoupling(methodInfo) )  return Disharmony.INTENSIVE_COUPLING;
		else  return null;
	}

	public static Disharmony dispersedCoupling(MethodInfo methodInfo) {
		if( isDispersedCoupling(methodInfo) )  return Disharmony.DISPERSED_COUPLING;
		else  return null;
	}

	public static Disharmony shotgunSurgery(MethodInfo methodInfo) {
		if( isShotgunSurgery(methodInfo) )  return Disharmony.SHOTGUN_SURGERY;
		else  return null;
	}

	public static Disharmony refusedParentBequest(ClassInfo classInfo) {
		if( isRefusedParentBequest(classInfo) )  return Disharmony.REFUSED_PARENT_BEQUEST;
		else  return null;
	}

	public static Disharmony traditionBreaker(ClassInfo child, HashSet<ClassInfo> classSet) {
		ClassInfo parent = new ClassInfo( child.getSuperClassName() );

		boolean canGetParent = false;
		for ( ClassInfo classInfo: classSet ) {
			if ( classSet.contains(parent) ) {
				parent = classInfo;
				canGetParent = true;  break;
			}
		}

		if ( canGetParent ) {
			if( isTraditionBreaker(child, parent) )  return Disharmony.TRADITION_BREAKER;
			else  return null;
		}  else  return null;
	}


}
