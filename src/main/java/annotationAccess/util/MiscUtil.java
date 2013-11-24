/**
 * 
 */
package annotationAccess.util;

import org.apache.uima.jcas.tcas.Annotation;

/**
 * 
 */
public class MiscUtil  {
	
	/**
	 * Echo in the standard output (the console) the type 
	 * and the covered text of the given annotation
	 * @param anAnnotation
	 */
	public static void echo(Annotation anAnnotation) {
		System.out.printf("type>%s<\t\tcoveredText>%s<\n", 
				anAnnotation.getClass().getSimpleName(), 
				anAnnotation.getCoveredText());
	}
}
