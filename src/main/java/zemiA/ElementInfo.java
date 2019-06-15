package zemiA;




/**
 * @author gruidae
 */
public interface ElementInfo {

	/* ----- Attribute: 属性 ----- */
	public enum AccessModifier {  // アクセス修飾子
		PRIVATE, PACKAGE_PRIVATE, PROTECTED, PUBLIC
	}

	public enum Disharmony {  // Disharomy の種類
		GOD_CLASS, FEATURE_ENVY, DATA_CLASS, BRAIN_METHOD, BRAIN_CLASS, SIGNIFICANT_DUPLICATION,
		INTENSIVE_COUPLING, DISPERSED_COUPLING, SHOTGUN_SURGERY,
		REFUSED_PARENT_BEQUEST, TRADITION_BREAKER
	}

	/* ----- setter メソッド ----- */
	public void setAccessModifier(String accessModifier);
	public void setName(String name);


	/* ----- Method: メソッド ----- */
	@Override
	public String toString();
	@Override
	public boolean equals(Object obj);
	@Override
	int hashCode();

}
