package zemiA;

import java.util.ArrayList;
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
	private ArrayList<String> argumentsList= new ArrayList<String>();  // 引数の型リスト

	private HashSet<Disharmony> disharmnonySet;  // Disharmonyの集合


	/* メトリクス */


	/* ----- Constructor: コンストラクタ ----- */
	MethodInfo(String name) {
		this.setName(name);  return;
	}

	MethodInfo
	(String definedClass, String accessModifier, boolean isStatic, boolean isAbstract,
			boolean returnType, String name)
	{
		this.setDefinedClass(definedClass);
		this.setAccessModifier(accessModifier);
		this.setIsStatic(isStatic);
		this.setIsAbstract(isAbstract);
		this.setName(name);

		/* 引数リストを保存 */
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


	public void setArgumentsList(ArrayList<String> argumentsList)
	{
		for ( String type: argumentsList) {
			this.argumentsList.add(new String(type));
		}
	}


	/* ----- Method: メソッド ----- */
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
		// if ( !(methodInfo.returnType).equals(this.returnType) )  return false;
		if ( !(methodInfo.argumentsList).equals(this.argumentsList) )  return false;  // リストの比較はequalsでいいのか？
		// if ( !(methodInfo.accessModifier).equals(this.accessModifier) )  return false;
		// if ( methodInfo.isStatic != this.isStatic )  return false;
		// if ( methodInfo.isAbstract != this.isAbstract )  return false;
		return true;
	}

	@Override
	public int hashCode() {
		// TODO 自動生成されたメソッド・スタブ
		final int HASH_START = 100, HASH_CONSTANT = 10;
		int hash = HASH_START;
		hash = ( hash << 5 ) + this.definedClass.hashCode();
		hash = ( hash << 5 ) + this.name.hashCode();
		hash = ( hash << 5 ) + this.returnType.hashCode();
		hash = ( hash << 5 ) + this.argumentsList.hashCode();
		hash = ( hash << 5 ) + this.accessModifier.hashCode();
		hash = this.isStatic?( (hash << 5) + HASH_CONSTANT):(hash << 5);
		hash = this.isAbstract?( (hash << 5) + HASH_CONSTANT * 2):(hash << 5);
		return hash;
	}

}
