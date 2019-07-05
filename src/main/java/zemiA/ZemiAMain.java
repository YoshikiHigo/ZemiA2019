package zemiA;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;




public class ZemiAMain {



	private static final String PACKAGE_PATH = "src/main/java/";
	private static final String JAVA_PACKAGE = "zemiA/";

	/*
	private static final String PACKAGE_PATH
		= "/WISSENHERBSTGRUIDAE/VM/VMCommon/eclipse_workspace_Windows/lifegame09B17050/src/";
	private static final String JAVA_PACKAGE = "lifegame/";
	 */

	private static LinkedHashSet<ClassInfo> classSet = new LinkedHashSet<ClassInfo>();  // クラスの集合


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

		final ZemiAVisitor visitor = new ZemiAVisitor(ZemiAMain.classSet);
		unit.accept(visitor);

		return visitor;
	}

	private static void output()
	{
		for( ClassInfo classInfo : classSet) {
			System.out.println(classInfo.toString());
			for( MethodInfo methodInfo : classInfo.getMethodSet() ) {
				methodInfo.decideDisharmony();
				System.out.println(methodInfo.toString());

				// System.out.println("LOC: "+methodInfo.getLOC());
				// System.out.println("CYCLO: "+methodInfo.getCYCLO());
				// System.out.println("MAXNESTING: "+methodInfo.getMAXNESTING());
				// System.out.println("NOAV: "+methodInfo.getNOAV());

			}
			System.out.print('\n');
		}
	}


	public static void main(final String[] args) {

		//Fileクラスのオブジェクトを生成する
        File dir = new File(PACKAGE_PATH + JAVA_PACKAGE);

        //listFilesメソッドを使用して一覧を取得する
        File[] list = dir.listFiles();

        if ( list == null )  System.out.println("No Such Package: " + PACKAGE_PATH + JAVA_PACKAGE);
        else {
        	for ( File tmp : list ) {
        		String fileName = tmp.toString();
        		ZemiAVisitor visitor = ZemiAMain.visitSource(fileName);
        	}
        }

        ZemiAMain.output();
		return;
	}

}
