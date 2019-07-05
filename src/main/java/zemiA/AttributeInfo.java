package zemiA;

/**
 * @author gruidae
 */
public class AttributeInfo implements ElementInfo {

	/* ----- Attribute: 属性 ----- */
	private String definedClass;  // 定義されているクラス
	private AccessModifier accessModifier;  // アクセス修飾子
	private boolean isStatic;  // 静的メソッドか否か
	private String type;  // 型
	private String name;  // 名前


	/* ----- Constructor: コンストラクタ ----- */
	AttributeInfo(String name) {
		this.setName(name);  return;
	}

	AttributeInfo(String definedClass, String name) {
		this.setDefinedClass(definedClass);
		this.setName(name);
		return;
	}

	AttributeInfo(String definedClass, String type, String name) {
		this.setDefinedClass(definedClass);
		this.setType(type);
		this.setName(name);

		return;
	}

	AttributeInfo(String definedClass, String accessModifier, boolean isStatic, String type, String name) {
		this.setDefinedClass(definedClass);
		this.setAccessModifier(accessModifier);
		this.setIsStatic(isStatic);
		this.setType(type);
		this.setName(name);

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

	public void setIsStatic(boolean isStatic)
	{
		this.isStatic = isStatic;  return;
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

	public void setType(String type)
	{
		this.type = new String(type);  return;
	}

	@Override
	public void setName(String name)
	{
		this.name = new String(name);  return;
	}


	/* ----- Method: メソッド ----- */
	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ
		String accessModifier = HelperToString.toString(this.accessModifier);
		String staticStr = HelperToString.toString(this.isStatic, "static");
		String str
		= String.format("%s.%s [%s, %s, %s]",
				this.definedClass, this.name, this.type, accessModifier, staticStr);
		return str;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO 自動生成されたメソッド・スタブ
		if (this == obj)  return true;
		if (obj == null)  return false;
		if ( !(obj instanceof ClassInfo) )  return false;
		AttributeInfo attributeInfo = (AttributeInfo) obj;

		if ( !(attributeInfo.definedClass).equals(this.definedClass) )  return false;
		if ( !(attributeInfo.name).equals(this.name) )  return false;
		return true;
	}

	@Override
	public int hashCode() {
		// TODO 自動生成されたメソッド・スタブ
		final int HASH_START = 100, HASH_CONSTANT = 10;
		int hash = HASH_START;
		hash = ( hash << 5 ) + this.definedClass.hashCode();
		hash = ( hash << 5 ) + this.name.hashCode();
		return hash;
	}

}
