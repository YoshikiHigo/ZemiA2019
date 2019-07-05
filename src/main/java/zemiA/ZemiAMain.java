package zemiA;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;




public class ZemiAMain {

	private static final String PACKAGE_PATH = "src/main/java/";
	private static final String JAVA_PACKAGE = "zemiA/";
	private static final String JAVA_SOURCE = "MethodInfo.java";

	private HashSet<ClassInfo> classSet = new HashSet<ClassInfo>();  // クラスの集合


	private static ZemiAVisitor visitSource(String javaSourcePath)
	{
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(javaSourcePath),
					StandardCharsets.ISO_8859_1);
		} catch (final Exception e) {
			System.err.println(e.getMessage());
			return null;
		}

		final ASTParser parser = ASTParser.newParser(AST.JLS11);
		parser.setSource(String.join(System.lineSeparator(), lines).toCharArray());

		CompilationUnit unit = null;
		try {
			unit = (CompilationUnit) parser.createAST(new NullProgressMonitor());
		} catch (final Exception e) {
			System.err.println(e.getMessage());
			return null;
		}

		final ZemiAVisitor visitor = new ZemiAVisitor();
		unit.accept(visitor);

		return visitor;
	}


	public static void main(final String[] args) {
		ZemiAVisitor visitor = visitSource(PACKAGE_PATH + JAVA_PACKAGE + JAVA_SOURCE);

		System.out.println("LOC: " + visitor.getLOC());
		System.out.println("NOM: " +  visitor.getNOM());
		System.out.println("CYCLO: " +  visitor.getCYCLO());

	}

}
