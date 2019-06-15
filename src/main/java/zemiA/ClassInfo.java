package zemiA;

import java.util.HashSet;




/**
 * @author gruidae
 */
public class ClassInfo implements ElementInfo {

	/* ----- Attribute: 属性 ----- */
	enum ClassAbstraction {
		NORMAL_CLASS, ABSTRACT_CLASS, INTERFACE_CLASS
	}

	private AccessModifier accessModifier;  // アクセス修飾子
	private ClassAbstraction classAbstraction;  // クラスの抽象度
	private String name;  // クラスの名前
	private String superClassName = "java.lang.Object";  // 親クラス名

	private HashSet<AttributeInfo> attributeList = new HashSet<AttributeInfo>();  // 属性の集合
	private HashSet<MethodInfo> methodList = new HashSet<MethodInfo>();  // メソッドの集合

	// private int numInherited;  // 継承された回数

	private HashSet<Disharmony> disharmnonySet;  // Disharmonyの集合


	/* ----- コンストラクタ ----- */
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


	/* ----- Method: メソッド ----- */
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

		if ( !(classInfo.name).equals(this.name) )  return false;
		if ( !(classInfo.accessModifier).equals(this.accessModifier) )  return false;
		if ( !(classInfo.classAbstraction).equals(this.classAbstraction) )  return false;
		if ( !(classInfo.superClassName).equals(this.superClassName) )  return false;
		return true;
	}

	@Override
	public int hashCode() {
		// TODO 自動生成されたメソッド・スタブ
		final int HASH_START = 100;
		int hash = HASH_START;

		hash = ( hash << 5 ) + this.name.hashCode();
		hash = ( hash << 5 ) + this.accessModifier.hashCode();
		hash = ( hash << 5 ) + this.classAbstraction.hashCode();
		hash = ( hash << 5 ) + this.superClassName.hashCode();
		return hash;
	}

}