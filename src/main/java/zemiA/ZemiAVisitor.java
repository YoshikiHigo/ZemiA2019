package zemiA;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;

public class ZemiAVisitor extends ASTVisitor {

  @Override
  //引数がノードの種類(SimpleName)
  public boolean visit(SimpleName node) {
    System.out.println(node.getIdentifier()); //ノードの名前を標準出力に出す
    return super.visit(node);
  }


@Override
  public boolean visit(MethodDeclaration node) {
    // System.out.println(node.toString());
    return super.visit(node);
  }
}
