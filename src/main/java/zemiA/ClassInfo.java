package zemiA;

import java.util.LinkedHashSet;




/**
 * @author gruidae
 */
public class ClassInfo implements ElementInfo {

	/* ----- Attribute: 属性 ----- */
	private AccessModifier accessModifier = AccessModifier.PACKAGE_PRIVATE;  // アクセス修飾子
	private ClassAbstraction classAbstraction;  // クラスの抽象度
	private String name;  // クラスの名前
	private String superClassName = "java.lang.Object";  // 親クラス名

	private LinkedHashSet<AttributeInfo> attributeSet = new LinkedHashSet<AttributeInfo>();  // 属性の集合
	private LinkedHashSet<MethodInfo> methodSet = new LinkedHashSet<MethodInfo>();  // メソッドの集合

	private LinkedHashSet<Disharmony> disharmnonySet = new LinkedHashSet<Disharmony>();  // Disharmonyの集合

	private int brainMethodNum = 0;  // Brain Method の数


	/* メトリクス */
	private int atfd = 0;        // 外部クラスからのアクセス数 (getter, setterメソッド含む)
	private int cyclo = 0;
	private int loc = 0;         // 空行, コメント行を含めた行数
	private int nas = 0;         // 子クラスで定義したpublicメソッド数
	private int noam = 0;        // getter, setterメソッド数
	private int nom = 0;         // メソッド数
	private int nopa = 0;        // public属性数
	private int nprotm = 0;      // protectedメンバ数
	private int tcc = 0;         // クラス凝集度 (共通にアクセスする属性とメソッドとの関係数)

	private double amw = 0;
	private double bovr = 0;
	private double bur = 0;
	private double noap = 0;
	private double pnas = 0;
	private double wmc = 0;
	private double woc = 0;

	/* ----- コンストラクタ ----- */
	ClassInfo() {  return; }

	ClassInfo(String name) {
		this.setName(name);  return;
	}

	ClassInfo(String accessModifier, boolean isAbstract, boolean isInterface, String name,
			String superClassName)
	{
		this.setAccessModifier(accessModifier);
		this.setClassAbstraction(isAbstract, isInterface);
		this.setName(name);
		this.setSuperClassName(superClassName);

		return;
	}


	/* ----- getterメソッド ----- */
	@Override
	public AccessModifier getAccessModifier() {
		// TODO 自動生成されたメソッド・スタブ
		return this.accessModifier;
	}

	@Override
	public String getName() {
		// TODO 自動生成されたメソッド・スタブ
		return new String(this.name);
	}

	public String getSuperClassName() {
		// TODO 自動生成されたメソッド・スタブ
		return new String(this.superClassName);
	}

	public int getBrainMethodNum()
	{
		return this.brainMethodNum;
	}

	public LinkedHashSet<MethodInfo> getMethodSet()
	{
		return this.methodSet;
	}

	public LinkedHashSet<AttributeInfo> getAttributeSet()
	{
		return this.attributeSet;
	}

	public int getATFD() { return this.atfd; }
	public int getCYCLO() { return this.cyclo; }
	public int getLOC() { return this.loc; }
	public int getNAS() { return this.nas; }
	public int getNOAM() { return this.noam; }
	public int getNOM() { return this.nom; }
	public int getNOPA() { return this.nopa; }
	public int getNProtM() { return this.nprotm; }
	public int getTCC() { return this.tcc; }

	public double getAMW() { return this.amw; }
	public double getBOvR() { return this.bovr; }
	public double getBUR() { return this.bur; }
	public double getNOAP() { return this.noap; }
	public double getPNAS() { return this.pnas; }
	public double getWMC() { return this.wmc; }
	public double getWOC() { return this.woc; }


	/* ----- setter メソッド ----- */
	@Override
	public void setAccessModifier(String accessModifier)
	{
		switch(accessModifier) {
		case "private":
			this.accessModifier = AccessModifier.PRIVATE;  break;
		case "protected":
			this.accessModifier = AccessModifier.PROTECTED;  break;
		case "public":
			this.accessModifier = AccessModifier.PUBLIC;  break;
		default:
			this.accessModifier = AccessModifier.PACKAGE_PRIVATE;
		}

		return;
	}

	public void setClassAbstraction(boolean isAbstract, boolean isInterface)
	{
		if ( isAbstract )  this.classAbstraction = ClassAbstraction.ABSTRACT_CLASS;
		else if ( isInterface )  this.classAbstraction = ClassAbstraction.INTERFACE_CLASS;
		else  this.classAbstraction = ClassAbstraction.NORMAL_CLASS;
		return;
	}

	@Override
	public void setName(String name)
	{
		this.name = new String(name);  return;
	}

	public void setSuperClassName(String superClassName)
	{
		this.superClassName = new String(superClassName);  return;
	}


	public void setMethodInfo(MethodInfo methodInfo)
	{
		this.methodSet.add(methodInfo);  return;
	}

	public void setAttributeInfo(AttributeInfo attributeInfo)
	{
		this.attributeSet.add(attributeInfo);  return;
	}

	/* setterメソッドを作る. */
	public void setATFD(int atfd) { this.atfd = atfd; }
	public void setCYCLO(int cyclo) { this.cyclo = cyclo; }
	public void setLOC(int loc) { this.loc = loc; }
	public void setNAS(int nas) { this.nas = nas; }
	public void setNOAM(int noam) { this.noam = noam; }
	public void setNOM(int nom) { this.nom = nom; }
	public void setNOPA(int nopa) { this.nopa = nopa; }
	public void setNProtM(int nprotm) { this.nprotm = nprotm; }
	public void setTCC(int tcc) { this.tcc = tcc; }

	/* ----- Method: メソッド ----- */
	void calculateMetrics()
	{
		/* BOvU, BUR, NOAP, PNAS, WMC, WOC の値を計算 */
		return;
	}


	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ
		String accessModifier = HelperToString.toString(this.accessModifier);
		String classAbstraction = HelperToString.toString(this.classAbstraction);
		String disharmonies = HelperToString.toString(this.disharmnonySet);
		String str
		= String.format("%s (%s, %s) extends %s: %s",
				this.name, accessModifier, classAbstraction, this.superClassName, disharmonies);
		return str;
	}


	@Override
	public boolean equals(Object obj) {
		// TODO 自動生成されたメソッド・スタブ
		if (this == obj)  return true;
		if (obj == null)  return false;
		if ( !(obj instanceof ClassInfo) )  return false;
		ClassInfo classInfo = (ClassInfo) obj;

		if ( classInfo.name.equals(this.name) )  return true;
		return false;
	}


	@Override
	public int hashCode() {
		// TODO 自動生成されたメソッド・スタブ
		final int HASH_START = 100;
		int hash = HASH_START;

		hash = ( hash << 5 ) + this.name.hashCode();
		return hash;
	}

}
