package zemiA;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.ContinueStatement;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.LineComment;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.ModuleDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.SuperMethodReference;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.TryStatement;
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

	/* メンバ定義 */
	@Override
	public boolean visit(MethodDeclaration node) {  // メソッド定義検出
		this.nom++;  // メソッド数のカウント
		String methodStr = node.toString();

		for(char x: methodStr.toCharArray())  // 命令行のカウント
			if(x == '\n')  loc++;

		// System.err.println(node.toString());
		return super.visit(node);
	}


	@Override
	public boolean visit(Block node) {  // {}ブロック検出
		// TODO 自動生成されたメソッド・スタブ
		// System.err.println(node.toString());
		return super.visit(node);
	}

	@Override
	public boolean visit(ConditionalExpression node) {
		// TODO 自動生成されたメソッド・スタブ

		return super.visit(node);
	}


	@Override
	public boolean visit(LineComment node) {
		// TODO 自動生成されたメソッド・スタブ
		System.err.println(node.toString());
		return super.visit(node);
	}

	@Override
	public boolean visit(ModuleDeclaration node) {  //
		// TODO 自動生成されたメソッド・スタブ
		System.out.println(node.toString());
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

	/* visit(LineComment)
	 *  は何もしない.
	 *  */

}
