package zemiA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;




/**
 * @author gruidae
 */
public class MethodInfo implements ElementInfo {

	/* ----- Attribute: 属性 ----- */
	private String definedClass;  // 定義されているクラス
	private AccessModifier accessModifier;  // アクセス修飾子
	private boolean isStatic;  // 静的メソッドか否か
	private boolean isAbstract;  // 抽象メソッドか否か
	private String returnType;  // 返し値の型  (null: コンストラクタ)
	private String name;  // 名前
	private ArrayList<String> argumentsList= new ArrayList<String>();  // 引数名を型名のリスト

	private HashMap<String, String> variableMap = new HashMap<String, String>();  // ローカル変数名と型名のマップ

	private HashSet<Disharmony> disharmnonySet = new HashSet<Disharmony>();  // Disharmonyの集合

	/* メトリクス */
	private int atfd = 0;        // 外部クラスからのアクセス数 (getter, setterメソッド含む)
	private int cc = 0;          // 測定中メソッドが呼び出すクラス数
	private int cint = 0;        // 測定中の処理によって呼ばれるメソッド数.
	private int cm = 0;          // 測定中のメソッドが呼び出すメソッド数 (再帰呼び出しは除く)
	private int cyclo = 0;       // 線形分岐の数
	private int fdp = 0;         // アクセスした属性が定義されているクラスの数
	private int loc = 0;         // 空行, コメント行を含めた行数
	private int maxNesting = 0;  // {} の最大ネスト数
	private int noav = 0;        // アクセスする変数の総数 (引数 (parameter), local, インスタンス, global変数)

	private double cdisp = 0;
	private double laa = 0;


	/* ----- Constructor: コンストラクタ ----- */
	MethodInfo(String name) {
		this.setName(name);  return;
	}

	MethodInfo(String definedClass, String name) {
		this.setDefinedClass(definedClass);
		this.setName(name);
		return;
	}

	MethodInfo(String definedClass, String returnType, String name) {
		this.setDefinedClass(definedClass);
		this.setReturnType(returnType);
		this.setName(name);
		return;
	}

	MethodInfo
	(String definedClass, String accessModifier, boolean isStatic, boolean isAbstract,
		 String returnType, String name)
	{
		this.setDefinedClass(definedClass);
		this.setAccessModifier(accessModifier);
		this.setIsStatic(isStatic);
		this.setIsAbstract(isAbstract);
		this.setReturnType(returnType);
		this.setName(name);

		/* 引数リストを保存 (未実装) */
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

	public int getATFD() { return this.atfd; }
	public int getCC() { return this.cc; }
	public int getCINT() { return this.cint; }
	public int getCM() { return this.cm; }
	public int getCYCLO() { return this.cyclo; }
	public int getFDP() { return this.fdp; }
	public int getLOC() { return this.loc; }
	public int getMAXNESTING() { return this.maxNesting; }
	public int getNOAV() { return this.noav; }

	public double getCDISP() { return this.cdisp; }
	public double getLAA() { return this.laa; }

	/* ----- setter メソッド ----- */
	public void setDefinedClass(String definedClass)
	{
		this.definedClass = new String(definedClass);  return;
	}

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

	public void setIsStatic(boolean isStatic)
	{
		this.isStatic = isStatic;  return;
	}

	public void setIsAbstract(boolean isAbstract)
	{
		this.isAbstract = isAbstract;  return;
	}

	public void setReturnType(String returnType)
	{
		this.returnType = new String(returnType);
		return;
	}

	@Override
	public void setName(String name)
	{
		this.name = new String(name);  return;
	}


	public void setArguments(String argumentType)
	{
		this.argumentsList.add(new String(argumentType));
		return;
	}

	public void setLocalVariable(String VariableType, String VariableName)
	{
		/* マップのコピー */
		this.variableMap.put(VariableName, VariableType);  return;
	}

	public void setATFD(int atfd) { this.atfd = atfd; }
	public void setCC(int cc) { this.cc = cc; }
	public void setCINT(int cint) { this.cint = cint; }
	public void setCM(int cm) { this.cm = cm; }
	public void setCYCLO(int cyclo) { this.cyclo = cyclo; }
	public void setFDP(int fdp) { this.fdp = fdp; }
	public void setLOC(int loc) { this.loc = loc; }
	public void setMAXNESTING(int maxNesting) { this.maxNesting = maxNesting; }
	public void setNOAV(int noav) { this.noav = noav; }


	/* ----- Method: メソッド ----- */
	void calculateMetrics()
	{
		/* LAA の値を計算 */
	}


	/* Disharmony の決定 */
	public void decideDisharmony()
	{
		Disharmony tmp = DetectionDisharmony.brainMethod(this);
		if ( tmp != null )  this.disharmnonySet.add(tmp);
		return;
	}


	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ
		String accessModifier = HelperToString.toString(this.accessModifier);
		String argumentsList = HelperToString.toString(this.argumentsList);
		String staticStr = HelperToString.toString(this.isStatic, "static");
		String abstractStr = HelperToString.toString(this.isAbstract, "abstract");
		String disharmonies = HelperToString.toString(this.disharmnonySet);
		String str
		= String.format("%s.%s(%s) [%s, %s, %s, %s] : %s",
				this.definedClass, this.name, argumentsList,
				this.returnType, accessModifier, staticStr, abstractStr, disharmonies);
		return str;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO 自動生成されたメソッド・スタブ
		if (this == obj)  return true;
		if (obj == null)  return false;
		if ( !(obj instanceof ClassInfo) )  return false;
		MethodInfo methodInfo = (MethodInfo) obj;


		if ( !(methodInfo.definedClass).equals(this.definedClass) )  return false;
		if ( !(methodInfo.name).equals(this.name) )  return false;
		if ( !(methodInfo.argumentsList).equals(this.argumentsList) )  return false;
		return true;
	}

	@Override
	public int hashCode() {
		// TODO 自動生成されたメソッド・スタブ
		final int HASH_START = 100;
		int hash = HASH_START;
		hash = ( hash << 5 ) + this.definedClass.hashCode();
		hash = ( hash << 5 ) + this.name.hashCode();
		hash = ( hash << 5 ) + this.argumentsList.hashCode();
		return hash;
	}
}
