package zemiA;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.ContinueStatement;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.MethodRef;
import org.eclipse.jdt.core.dom.MethodRefParameter;
import org.eclipse.jdt.core.dom.ModuleDeclaration;
import org.eclipse.jdt.core.dom.NameQualifiedType;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.SuperMethodReference;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeParameter;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

public class ZemiAVisitor extends ASTVisitor {

	//Classinfo定義

//	public ZemiAVisitor() {
//		classInfo()
//	}

	private ClassInfo classInfo;	//クラス情報参照変数

	/* ----- Attribute: 属性 ----- */
	private int atfd = 0;        // 外部クラスからのアクセス数 (getter, setterメソッド含む)
	private int cc = 0;          // 測定中のメソッドから呼び出すクラス
	private int cdisp = 0;       // 測定中のメソッドによって呼ばれるメソッド総数
	private int cint = 0;        // 測定中の処理によって呼ばれるメソッド数.
	private int cm = 0;          // 測定中のメソッドが呼び出すメソッド数 (再帰呼び出しは除く)
	private int cyclo = 0;       // 線形分岐の数
	private int fdp = 0;         // *アクセスした属性が定義されているクラスの数
	private int loc = 0;         // *空行, コメント行を含めた行数
	private int maxNesting = 0;  // {} の最大ネスト数 (keta)
	private int nas = 0;         // 子クラスで定義したpublicメソッド数
	private int noam = 0;        // getter, putterメソッド数
	private int noav = 0;        // アクセスする変数の総数 (引数 (parameter), local, インスタンス, global変数)
	private int nom = 0;         // メソッド数 ()
	private int nopa = 0;        // public属性数 (keta)
	private int nprotm = 0;      // protectedメンバ数 (keta)
	private int tcc = 0;         // クラス凝集度 (共通にアクセスする属性とメソッドとの関係数)

	/* ----- getter メソッド ----- */
	public int getATFD() { return this.atfd; }
	public int getCC() { return this.cc; }
	public int getCDISP() { return this.cdisp; }
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

	/* ----- Method: メソッド ----- */
	@Override
	public boolean visit(SimpleName node) {
		return super.visit(node);
	}



	@Override
	public boolean visit(TypeDeclaration node) {
		// TODO 自動生成されたメソッド・スタブ

		   System.err.println(node.toString());

		String className = node.getName().toString(); //クラス名を取り出す
		classInfo = new ClassInfo(className);	      //インスタンス生成

		return super.visit(node);
	}
	//表示なし
	@Override
	public boolean visit(VariableDeclarationExpression node) {
		// TODO 自動生成されたメソッド・スタブ
		//   System.err.println(node.toString());
		return super.visit(node);
	}


//表示なし
	@Override
	public boolean visit(MethodRef node) {
		// TODO 自動生成されたメソッド・スタブ
//		   System.err.println(node.toString());
		return super.visit(node);
	}

	//表示なし
	@Override
	public boolean visit(MethodRefParameter node) {
		// TODO 自動生成されたメソッド・スタブ
//		   System.err.println(node.toString());
		return super.visit(node);
	}

	//ローカル変数
	@Override
	public void endVisit(VariableDeclarationStatement node) {
		// TODO 自動生成されたメソッド・スタブ
//		   System.err.println(node.toString());
		super.endVisit(node);
	}

	//パラメータ以外の変数
	@Override
	public void endVisit(VariableDeclarationFragment node) {
		// TODO 自動生成されたメソッド・スタブ
		   System.err.println(node.toString());
		super.endVisit(node);
	}

	@Override
	public boolean visit(VariableDeclarationStatement node) {
		// TODO 自動生成されたメソッド・スタブ
//		   System.err.println(node.toString());
		return super.visit(node);
	}
	@Override
	public boolean visit(VariableDeclarationFragment node) {
		// TODO 自動生成されたメソッド・スタブ
//		   System.err.println(node.toString());
		return super.visit(node);
	}

	/* メンバ定義 */
	@Override
	public boolean visit(MethodDeclaration node) {  // メソッド定義検出

        MethodInfo methodInfo = new MethodInfo(node.getName().toString());

    	ArrayList<String> varList = new ArrayList<String>(); //変数リスト(パラメータ+ローカル変数)

		for(Object parameter : node.parameters()) { //パラメータリストを取り出す
			varList.add(parameter.toString());  //加える
		}

		for(String str: varList) {
			System.out.println(str);
		}

		methodInfo.setArgumentsList(varList);

//		for(Object property : node.properties().toString()) { //パラメータリストを取り出す
//			parameterList.add(param.toString());  //加える
//		}





		this.nom++;  // メソッド数のカウント
		String methodStr = node.toString();

		for(char x: methodStr.toCharArray())  // 命令行のカウント
			if(x == '\n')  loc++;

//	   System.err.println(node.toString());
		return super.visit(node);
	}

	@Override
	public boolean visit(QualifiedName node) {
		// TODO 自動生成されたメソッド・スタブ
		return super.visit(node);
	}

	@Override
	public boolean visit(NameQualifiedType node) {
		// TODO 自動生成されたメソッド・スタブ
		return super.visit(node);
	}


	//なし
	@Override
	public void endVisit(ParenthesizedExpression node) {
		// TODO 自動生成されたメソッド・スタブ
//		System.err.println(node.toString());
		super.endVisit(node);
	}

	//なし
	@Override
	public boolean visit(ParenthesizedExpression node) {
		// TODO 自動生成されたメソッド・スタブ
//		System.err.println(node.toString());
		return super.visit(node);
	}
	@Override
	public boolean visit(ArrayAccess node) {
		// TODO 自動生成されたメソッド・スタブ
//		   System.err.println(node.toString());
		return super.visit(node);
	}


	@Override
	public boolean visit(FieldAccess node) {
		// TODO 自動生成されたメソッド・スタブ
//		System.err.println(node.toString());
		return super.visit(node);
	}


	@Override
	public boolean visit(FieldDeclaration node) {
		// TODO 自動生成されたメソッド・スタブ
		if(node.toString().contains("public ")) {nopa++;}
		if(node.toString().contains("protected ")) {nprotm++;}

		return super.visit(node);
	}

//
//	@Override
//	public boolean visit(Modifier node) {
//		// TODO 自動生成されたメソッド・スタブ
////		System.err.println(node.toString());
//		return super.visit(node);
//	}


	@Override
	public boolean visit(Block node) {  // {}ブロック検出
		// TODO 自動生成されたメソッド・スタブ
		String lineArray[] = node.toString().split("\n");
		int nestCount = 0;
		for(String line : lineArray){
			if(line.contains("{")) {nestCount++;}
			maxNesting = maxNesting < nestCount? nestCount:maxNesting;
			if(line.contains("}")) {nestCount--;}
		}

//		System.err.println(node.toString());
		return super.visit(node);
	}


    //なし
	@Override
	public boolean visit(TypeParameter node) {
		// TODO 自動生成されたメソッド・スタブ
//		 System.err.println(node.toString());
		return super.visit(node);
	}


	@Override
	public boolean visit(ConditionalExpression node) {
		// TODO 自動生成されたメソッド・スタブ
		// System.err.println(node.toString());
		this.cyclo++;
		return super.visit(node);
	}


//	@Override
//	public boolean visit(LineComment node) {
//		// TODO 自動生成されたメソッド・スタブ
////		System.err.println(node.toString());
//		return super.visit(node);
//	}

	//なし
	@Override
	public boolean visit(ModuleDeclaration node) {  //
		// TODO 自動生成されたメソッド・スタブ
//		System.out.println(node.toString());
		return super.visit(node);
	}

	/* 条件分岐 */
	@Override
	public boolean visit(IfStatement node) {  // if文検出 (if の塊. else 単体は検出せず)
		// TODO 自動生成されたメソッド・スタブ
		this.cyclo++;
		return super.visit(node);
	}

	@Override
	public boolean visit(BreakStatement node) {  // break文検出
		// TODO 自動生成されたメソッド・スタブ
		this.cyclo++;
		return super.visit(node);
	}

	@Override
	public boolean visit(ContinueStatement node) {  // continue文検出
		// TODO 自動生成されたメソッド・スタブ
		this.cyclo++;
		return super.visit(node);
	}

	@Override
	public boolean visit(SwitchCase node) {  // case, dafault文検出
		// TODO 自動生成されたメソッド・スタブ
		this.cyclo++;
		return super.visit(node);
	}


	/* 条件分岐（時によっては無条件分岐） */
	@Override
	public boolean visit(ForStatement node) {  // for文検出
		// TODO 自動生成されたメソッド・スタブ
		this.cyclo++;
		return super.visit(node);
	}

	@Override
	public boolean visit(EnhancedForStatement node) {  // for-each文検出
		// TODO 自動生成されたメソッド・スタブ
		this.cyclo++;
		return super.visit(node);
	}

	@Override
	public boolean visit(WhileStatement node) {  // while文検出
		// TODO 自動生成されたメソッド・スタブ
		this.cyclo++;
		return super.visit(node);
	}

	@Override
	public boolean visit(DoStatement node) {  // do-while文検出
		// TODO 自動生成されたメソッド・スタブ
		this.cyclo++;
		return super.visit(node);
	}


	/* 例外処理における条件分岐 */
	@Override
	public boolean visit(TryStatement node) {  // try文検出 (try-catch塊につき1回)
		// TODO 自動生成されたメソッド・スタブ
		// System.err.println(node.toString());
		return super.visit(node);
	}

	@Override
	public boolean visit(CatchClause node) {  // catch文検出
		// TODO 自動生成されたメソッド・スタブ
		// System.err.println(node.toString());
		return super.visit(node);
	}


	/* 親クラス関係 */
	@Override
	public boolean visit(SuperConstructorInvocation node) {  // コンストラクタ定義の検出
		// TODO 自動生成されたメソッド・スタブ
		return super.visit(node);
	}

	@Override
	public boolean visit(SuperFieldAccess node) {  // 親クラスで定義されたフィールドへのアクセスを検出
		// TODO 自動生成されたメソッド・スタブ
		return super.visit(node);
	}


	@Override
	public boolean visit(MethodInvocation node) {
		// TODO 自動生成されたメソッド・スタブ
//		   System.err.println(node.toString());
		return super.visit(node);
	}
	@Override
	public boolean visit(SuperMethodInvocation node) {
		// TODO 自動生成されたメソッド・スタブ
		return super.visit(node);
	}

	@Override
	public boolean visit(SuperMethodReference node) {  // 親クラスで定義されたメソッドの参照を検出
		// TODO 自動生成されたメソッド・スタブ
		// System.err.println(node.toString());
		return super.visit(node);
	}

	/* visit(LineComment) は何もしない. */

}
