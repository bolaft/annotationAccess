/**
 * 
 */
package corr.annotationAccess.annotators;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import annotationAccess.util.MiscUtil;

/**
 * Annotator which iterates over the whole annotation index and 
 * echoes both the type and the covered text of each annotation 
 * using low level methods
 */
public class AnnotationIndexIteratorEchoAnnotator extends JCasAnnotator_ImplBase {
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		
		// TODO
		// Get a FSIterator<Annotation> from the index, 
		// then while it is valid 
		// 			get 
		//			and echo the current annotation 
		//			and move to the next one.
		FSIterator<Annotation> annotationIterator = aJCas.getAnnotationIndex().iterator();
		while (annotationIterator.isValid()) {
			Annotation annotation = annotationIterator.get();
			MiscUtil.echo(annotation);
			annotationIterator.moveToNext();
		}
		
	}
	
}
