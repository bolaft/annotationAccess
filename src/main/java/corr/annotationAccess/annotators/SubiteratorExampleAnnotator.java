/**
 * 
 */
package corr.annotationAccess.annotators;

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
		// for future use with the subiterator method
		AnnotationIndex<Annotation>	annotationIndex = aJCas.getAnnotationIndex();

		AnnotationIndex<Annotation>	tokenAnnotationIndex = aJCas.getAnnotationIndex(Token.type);
		for (Annotation token : tokenAnnotationIndex) {
			// subiterator call 
			FSIterator<Annotation> subTokenAnnotationIterator = annotationIndex.subiterator(token);
			while(subTokenAnnotationIterator.isValid()) {
				Annotation subTokenAnnotation = subTokenAnnotationIterator.get();
				// filter the desired annotations
				if (subTokenAnnotation instanceof POS)
					MiscUtil.echo(subTokenAnnotation);
				subTokenAnnotationIterator.moveToNext();
			}
		}
	}

	
}
