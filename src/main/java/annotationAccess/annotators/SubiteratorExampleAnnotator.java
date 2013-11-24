/**
 * 
 */
package annotationAccess.annotators;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import annotationAccess.util.MiscUtil;

import common.types.POS;
import common.types.Token;

/**
 * Annotator which subiterates with the native UIMA method 'subiterator'
 * The method requires both to specify a type priority and 
 * to filter the returned sub-annotations.
 * We assume token and pos annotations occurring at the same spans 
 * the annotator subiterates the token annotations to get the POS annotations
 */
public class SubiteratorExampleAnnotator extends JCasAnnotator_ImplBase {
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		AnnotationIndex<Annotation>	index = aJCas.getAnnotationIndex();

		AnnotationIndex<Annotation>	indexToken = aJCas.getAnnotationIndex(Token.type);
		
		for (Annotation token : indexToken) {
			FSIterator<Annotation> indexSubiteratorToken = index.subiterator(token);
			
			while(indexSubiteratorToken.isValid()) {
				Annotation annotation = indexSubiteratorToken.get();
				
				if (annotation instanceof POS) {
					MiscUtil.echo(annotation);
				}
				
				indexSubiteratorToken.moveToNext();
			}
		}
	}
}
