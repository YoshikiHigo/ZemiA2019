package zemiA;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.ContinueStatement;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.WhileStatement;




public class ZemiAVisitor extends ASTVisitor {

	/* 実装済み:
	 *
	 * 不完全実装:
	 *
	 * 未実装:
	 * LOC (空行, コメント行がカウントできない), NOM (public クラスのみ実装), CYCLO (クラスごと, メソッドごと必要)
	 **/


	/* ----- Attribute: 属性 ----- */
	private int atfd = 0;        // 外部クラスからのアクセス数 (getter, setterメソッド含む)
	private int cc = 0;          // 測定中メソッドが呼び出すクラス数
	private int cint = 0;        // 測定中のメソッドによって呼ばれるメソッド総数.
	private int cm = 0;          // 測定中のメソッドが呼び出すメソッド数 (再帰呼び出しは除く)
	private int cyclo = 0;       // 線形分岐の数 (if, caseなどの条件分岐, forなどの繰り返しなど)
	private int fdp = 0;         // アクセスした属性が定義されているクラスの数
	private int loc = 0;         // 空行, コメント行を含めた行数
	private int maxNesting = 0;  // {} の最大ネスト数
	private int nas = 0;         // 子クラスで定義したpublicメソッド数
	private int noam = 0;        // getter, setterメソッド数
	private int noav = 0;        // アクセスする変数の総数 (引数 (parameter), local, インスタンス, global変数)
	private int nom = 0;         // メソッド数
	private int nopa = 0;        // public属性数
	private int nprotm = 0;      // protectedメンバ数
	private int tcc = 0;         // クラス凝集度 (共通にアクセスする属性とメソッドとの関係数)

	private ClassInfo nowClassInfo;
	private MethodInfo nowMethodInfo;
	private boolean isMethodBlock = false;
	private HashSet<ClassInfo> classSet;

	private int methodCyclo = 0;
	private int methodMaxNesting = 0;
	private int methodNoav = 0;

	/* ----- コンストラクタ ----- */
	public ZemiAVisitor()
	{
		return;
	}

	public ZemiAVisitor(HashSet<ClassInfo> classSet)
	{
		this.classSet = classSet;
		return;
	}


	/* ----- getter メソッド ----- */
	public int getATFD() { return this.atfd; }
	public int getCC() { return this.cc; }
	public int getCINT() { return this.cint; }
	public int getCM() { return this.cm; }
	public int getCYCLO() { return this.cyclo; }
	public int getFDP() { return this.fdp; }
	public int getLOC() { return this.loc; }
	public int getMAXNESTING() { return this.maxNesting; }
	public int getNAS() { return this.nas; }
	public int getNOAM() { return this.noam; }
	public int getNOAV() { return this.noav; }
	public int getNOM() { return this.nom; }
	public int getNOPA() { return this.nopa; }
	public int getNProtM() { return this.nprotm; }
	public int getTCC() { return this.tcc; }

	/* ----- Method: メソッド ----- */


	@Override
	public boolean visit(SimpleName node) {
		// System.out.println(node.getIdentifier());
		return super.visit(node);
	}


	@Override
	public boolean visit(TypeDeclaration node) {
		// TODO 自動生成されたメソッド・スタブ
		if (this.nowClassInfo != null) {
			this.nowClassInfo.setLOC(this.loc);
			this.nowClassInfo.setCYCLO(this.cyclo);
		}

		this.loc=0;  this.cyclo=0;

		ClassInfo classInfo = new ClassInfo(node.getName().toString());
		classInfo.setClassAbstraction(false, node.isInterface());

		Type superClassType = node.getSuperclassType();  // スーパークラス
		if ( superClassType != null ) classInfo.setSuperClassName(superClassType.toString());

		for(Object tmp: node.modifiers()) {  // アクセス修飾子
			String modifier = ((IExtendedModifier)tmp).toString();
			if ( modifier.contains("@") )  continue;
			classInfo.setAccessModifier(modifier);
		}

		String classStr = node.toString();
		for(char x: classStr.toCharArray())  // 命令行のカウント
			if(x == '\n')  loc++;
		classInfo.setLOC(loc);

		this.nowClassInfo = classInfo;  this.classSet.add(classInfo);
		return super.visit(node);
	}


	/* メンバ定義 */
	@Override
	public boolean visit(MethodDeclaration node) {  // メソッド定義検出
		if ( this.nowMethodInfo != null ) {
			this.nowMethodInfo.setCYCLO(this.methodCyclo);
			this.nowMethodInfo.setMAXNESTING(this.methodMaxNesting);
			this.nowMethodInfo.setNOAV(this.methodNoav);
		}

		this.nom++;  // メソッド数のカウント
		this.nowClassInfo.setNOM(this.nom);

		this.methodCyclo = 0;  this.methodMaxNesting = 0; this.methodNoav = 0;

		int loc = 0;  // コード行数

		MethodInfo methodInfo = new MethodInfo(node.getName().toString());
		methodInfo.setDefinedClass(nowClassInfo.getName());  // メソッド名

		Type returnType = node.getReturnType2();  // 返し値の型
		if (returnType != null)  methodInfo.setReturnType(returnType.toString());
		if ( node.isConstructor() )  methodInfo.setReturnType("Constructor");

		ArrayList<String> argumentList = new ArrayList<String>();  // 引数型の列
		for ( Object tmp : node.parameters() ) {
			SingleVariableDeclaration args = (SingleVariableDeclaration)tmp;
			if ( args != null )  argumentList.add( args.getType().toString() );
		}
		methodInfo.setArgumentsList(argumentList);

		for(Object tmp: node.modifiers()) {  // アクセス修飾子
			String modifier = ((IExtendedModifier)tmp).toString();
			if ( modifier.contains("@") )  continue;
			if ( modifier.equals("public") || modifier.equals("protected") || modifier.equals("private") ) {
				methodInfo.setAccessModifier(modifier);
			} else if ( modifier.equals("static") )  methodInfo.setIsStatic(true);
			else if ( modifier.equals("abstract") )  methodInfo.setIsAbstract(true);
		}


		/* loc計測 */
		String methodStr = node.toString();
		for(char x: methodStr.toCharArray())  // 命令行のカウント
			if(x == '\n')  loc++;
		methodInfo.setLOC(loc);

		this.nowMethodInfo = methodInfo;
		this.nowClassInfo.setMethodInfo(methodInfo);

		this.isMethodBlock = true;
		return super.visit(node);
	}

	@Override
	public boolean visit(Block node) {  // {}ブロック検出
		// TODO 自動生成されたメソッド・スタブ
		// System.err.println(node.toString());
		if (this.isMethodBlock) {
			this.isMethodBlock = false;  return  super.visit(node);
		}


		/* MAXESTING 検出 */
		String lineArray[] = node.toString().split("\n");
		int nestCount = 0;
		for(String line : lineArray){
			if(line.contains("{")) {nestCount++;}
			this.maxNesting = this.maxNesting < nestCount? nestCount:this.maxNesting;
			this.methodMaxNesting = this.methodMaxNesting < nestCount? nestCount:this.methodMaxNesting;
			if(line.contains("}")) {nestCount--;}
		}

		return super.visit(node);
	}

	/* 条件分岐 */
	@Override
	public boolean visit(IfStatement node) {  // if文検出 (if の塊. else 単体は検出せず)
		// TODO 自動生成されたメソッド・スタブ
		this.methodCyclo++;  this.cyclo++;
		return super.visit(node);
	}

	@Override
	public boolean visit(BreakStatement node) {  // break文検出
		// TODO 自動生成されたメソッド・スタブ
		this.methodCyclo++;  this.cyclo++;
		return super.visit(node);
	}

	@Override
	public boolean visit(ContinueStatement node) {  // continue文検出
		// TODO 自動生成されたメソッド・スタブ
		this.methodCyclo++;  this.cyclo++;
		return super.visit(node);
	}

	@Override
	public boolean visit(SwitchCase node) {  // case, dafault文検出
		// TODO 自動生成されたメソッド・スタブ
		this.methodCyclo++;  this.cyclo++;
		return super.visit(node);
	}


	/* 条件分岐（時によっては無条件分岐） */
	@Override
	public boolean visit(ForStatement node) {  // for文検出
		// TODO 自動生成されたメソッド・スタブ
		this.methodCyclo++;  this.cyclo++;
		return super.visit(node);
	}

	@Override
	public boolean visit(EnhancedForStatement node) {  // for-each文検出
		// TODO 自動生成されたメソッド・スタブ
		this.methodCyclo++;  this.cyclo++;
		return super.visit(node);
	}

	@Override
	public boolean visit(WhileStatement node) {  // while文検出
		// TODO 自動生成されたメソッド・スタブ
		this.methodCyclo++;  this.cyclo++;
		return super.visit(node);
	}

	@Override
	public boolean visit(DoStatement node) {  // do-while文検出
		// TODO 自動生成されたメソッド・スタブ
		this.methodCyclo++;  this.cyclo++;
		return super.visit(node);
	}
}
