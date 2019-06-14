package zemiA;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class ZemiAMain {

  public static void main(final String[] args) {
    List<String> lines = null;
    try {
      lines = Files.readAllLines(Paths.get("src/main/java/zemiA/ZemiAMain.java"),
          StandardCharsets.ISO_8859_1); //全ての行を読み込む
    } catch (final Exception e) {
      System.err.println(e.getMessage());
      return;
    }

   final ASTParser parser = ASTParser.newParser(AST.JLS11); //ASTパーサ（構文解析木）の作成

   //各行の要素をjoinしてパーサにセット
    parser.setSource(String.join(System.lineSeparator(), lines).toCharArray());

    CompilationUnit unit = null;
    try {
    	//パーサを使ってこのソースコードの構文木を作る
      unit = (CompilationUnit) parser.createAST(new NullProgressMonitor());

    } catch (final Exception e) {
      System.err.println(e.getMessage());
      return;
    }

    //ビジター... 木構造を辿っていくクラスのインスタンスの定義
    final ZemiAVisitor visitor = new ZemiAVisitor();

    //引数のビジターのインスタンスで構文抽象木をたどる
    unit.accept(visitor);
  }
}
