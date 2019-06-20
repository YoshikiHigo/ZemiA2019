package zemiA;




/**
 * @author gruidae
 */
public interface ElementInfo {

	/* ----- setter メソッド ----- */
	public void setAccessModifier(String accessModifier);
	public void setName(String name);


	/* ----- getterメソッド ----- */
	public AccessModifier getAccessModifier();
	public String getName();

	/* ----- Method: メソッド ----- */
	@Override
	public String toString();
	@Override
	public boolean equals(Object obj);
	@Override
	int hashCode();

}
